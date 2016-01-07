package com.taobao.api.request;

import com.taobao.api.internal.util.RequestCheckUtils;
import java.util.Map;

import com.taobao.api.ApiRuleException;
import com.taobao.api.BaseTaobaoRequest;
import com.taobao.api.internal.util.TaobaoHashMap;

import com.taobao.api.response.AlibabaGeoipGetResponse;

/**
 * TOP API: alibaba.geoip.get request
 * 
 * @author top auto create
 * @since 1.0, 2015.08.20
 */
public class AlibabaGeoipGetRequest extends BaseTaobaoRequest<AlibabaGeoipGetResponse> {
	
	

	/** 
	* 要查询的IP地址,与language一起使用，与iplist二选一使用，提供单个IP查询
	 */
	private String ip;

	/** 
	* 返回结果的文字语言，cn中文；en英文
	 */
	private String language;

	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getIp() {
		return this.ip;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	public String getLanguage() {
		return this.language;
	}

	public String getApiMethodName() {
		return "alibaba.geoip.get";
	}

	public Map<String, String> getTextParams() {		
		TaobaoHashMap txtParams = new TaobaoHashMap();
		txtParams.put("ip", this.ip);
		txtParams.put("language", this.language);
		if(this.udfParams != null) {
			txtParams.putAll(this.udfParams);
		}
		return txtParams;
	}

	public Class<AlibabaGeoipGetResponse> getResponseClass() {
		return AlibabaGeoipGetResponse.class;
	}

	public void check() throws ApiRuleException {
		RequestCheckUtils.checkNotEmpty(ip, "ip");
		RequestCheckUtils.checkNotEmpty(language, "language");
	}
	

}