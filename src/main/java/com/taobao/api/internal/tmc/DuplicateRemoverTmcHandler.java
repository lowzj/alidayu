package com.taobao.api.internal.tmc;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.taobao.api.internal.util.json.JSONValidatingReader;
import com.taobao.top.link.endpoint.EndpointContext;

/**
 * 消息服务-内部去重处理器
 */
public class DuplicateRemoverTmcHandler extends TmcHandler {

	private static final Log log = LogFactory.getLog(DuplicateRemoverTmcHandler.class);
	private static final Log statlog = LogFactory.getLog(DuplicateRemoverTmcHandler.class.getSimpleName());

	private static final long TIMER_DELAY = 10L; // 启动延时
	private static final long TIMER_PERIOD = 500L; // 缓冲间隔

	private AtomicLong totalMessageNum = new AtomicLong(0L);
	private AtomicLong curProduceNum = new AtomicLong(0L);
	private AtomicLong hasConsumeNum = new AtomicLong(0L);

	private ConcurrentHashMap<String, Message> msgMap;
	private ArrayBlockingQueue<String> msgKeyQueue;
	private KeySelector keySelector;
	private Timer timer;

	public DuplicateRemoverTmcHandler(TmcClient tmcClient) {
		super(tmcClient);
		if (tmcClient.getKeySelector() == null) {
			this.keySelector = new MsgKeySelector();
		} else {
			this.keySelector = tmcClient.getKeySelector();
		}
		this.msgMap = new ConcurrentHashMap<String, Message>();
		this.msgKeyQueue = new ArrayBlockingQueue<String>(tmcClient.getQueueSize());
		this.timer = new Timer("tmc-duplicate-remover", true);
		this.timer.schedule(new MsgScheduleTask(), TIMER_DELAY, TIMER_PERIOD);
	}

	public void onMessage(EndpointContext context) throws Exception {
		final Map<String, Object> rawMsg = context.getMessage();
		if (log.isDebugEnabled()) {
			log.debug(String.format("onMessage from %s: %s", context.getMessageFrom(), rawMsg));
		}

		final Message msg = parse(rawMsg);
		final String key = this.keySelector.selectKey(msg);
		if (key == null) { // 走正常流程
			super.handleMessage(msg, false);
		} else { // 走去重流程
			if (!put(key, msg)) { // 去重成功
				super.handleMessage(msg, true);
				log(key, msg.getId());
			}
		}
	}

	private void log(String key, Long msgId) {
		StringBuilder buf = new StringBuilder();
		buf.append(System.currentTimeMillis()).append(","); // 时间
		buf.append(tmcClient.getAppKey()).append(","); // AppKey
		buf.append(tmcClient.getGroupName()).append(","); // GroupName
		buf.append(msgId).append(","); // 消息ID
		buf.append(key); // 业务ID
		statlog.fatal(buf.toString());
	}

	public void close() {
		super.close();
		if (this.timer != null) {
			this.timer.cancel();
			this.timer = null;
		}
	}

	private boolean put(String key, Message message) throws InterruptedException {
		Object obj = msgMap.putIfAbsent(key, message);
		if (obj == null) {
			msgKeyQueue.put(key);
			totalMessageNum.incrementAndGet();
			return true;
		} else {
			return false;
		}
	}

	private class MsgKeySelector implements KeySelector {
		public String selectKey(Message message) {
			String topic = message.getTopic();
			String key = null;
			if (topic.startsWith("taobao_trade") || topic.equals("taobao_datapush_SynTrade")) {
				key = "trade_" + this.getId(message, "tid");
			} else if (topic.startsWith("taobao_item") || topic.equals("taobao_datapush_SynItem")) {
				key = "item_" + this.getId(message, "num_iid");
			} else if (topic.startsWith("taobao_refund")) {
				key = "refund_" + this.getId(message, "refund_id");
			} else {
				key = null;
			}
			return key;
		}

		private String getId(Message message, String field) {
			JSONValidatingReader reader = new JSONValidatingReader();
			message.setContentMap((Map<?, ?>) reader.read(message.getContent()));
			Object id = message.getContentMap().get(field);
			return String.valueOf(id);
		}
	}

	private class MsgScheduleTask extends TimerTask {
		public void run() {
			long beginTime = System.currentTimeMillis();
			long leftNum = curProduceNum.get();
			while (hasConsumeNum.get() < leftNum) {
				if ((System.currentTimeMillis() - beginTime) > TIMER_PERIOD) {
					break; // 中断提前，避免任务执行超时
				}
				String key = null;
				try {
					key = msgKeyQueue.take();
					handleMessage(msgMap.remove(key), false);
					hasConsumeNum.incrementAndGet();
				} catch (Exception e) {
					log.error("handle message fail: %s" + key, e);
				}
			}
			curProduceNum.set(totalMessageNum.get());
		}
	}

}
