package com.taobao.api;

import java.util.Map;

import com.taobao.api.internal.util.TaobaoHashMap;

/**
 * 基础TOP请求类，存放一些通用的请求参数。
 * 
 * @author fengsheng
 * @since Sep 9, 2015
 */
public abstract class BaseTaobaoRequest<T extends TaobaoResponse> implements TaobaoRequest<T> {

	protected Map<String, String> headerMap; // HTTP请求头参数
	protected TaobaoHashMap udfParams; // 自定义表单参数
	protected Long timestamp; // 请求时间戳
	protected String targetAppKey; // 请求目标AppKey
	protected String topMixParams; // 指定哪个入参是混淆参数

	public void putOtherTextParam(String key, String value) {
		if (this.udfParams == null) {
			this.udfParams = new TaobaoHashMap();
		}
		this.udfParams.put(key, value);
	}

	public Map<String, String> getHeaderMap() {
		if (this.headerMap == null) {
			this.headerMap = new TaobaoHashMap();
		}
		return this.headerMap;
	}

	public void setHeaderMap(Map<String, String> headerMap) {
		this.headerMap = headerMap;
	}

	public void addHeaderMap(String key, String value) {
		getHeaderMap().put(key, value);
	}

	public Long getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getTargetAppKey() {
		return this.targetAppKey;
	}

	public void setTargetAppKey(String targetAppKey) {
		this.targetAppKey = targetAppKey;
	}

	public String getTopMixParams() {
		return this.topMixParams;
	}

	public void setTopMixParams(String topMixParams) {
		this.topMixParams = topMixParams;
	}

}
