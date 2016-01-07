import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import org.junit.Test;

/**
 * Copyright 2015, Easemob.
 * All rights reserved.
 * Author: zhangjin@easemob.com
 */
public class SendVerifyCodeTest {
    private static final String url = "http://gw.api.taobao.com/router/rest";
    private static final String appKey = "";
    private static final String appSecret = "";
    private static final String phone = "";

    @Test
    public void testSendSms() throws Exception {
        TaobaoClient client = new DefaultTaobaoClient(url, appKey, appSecret);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend("a");
        req.setSmsType("normal");
        req.setSmsFreeSignName("注册验证");
        req.setSmsParam("{'code': '123456', 'product': 'test'}");
        req.setRecNum(phone);
        req.setSmsTemplateCode("SMS_3985637");
        AlibabaAliqinFcSmsNumSendResponse response = client.execute(req);
    }
}
