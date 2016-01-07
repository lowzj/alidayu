package com.taobao.api.request;

import com.taobao.api.internal.util.RequestCheckUtils;
import java.util.Map;

import com.taobao.api.ApiRuleException;
import com.taobao.api.BaseTaobaoRequest;
import com.taobao.api.internal.util.TaobaoHashMap;

import com.taobao.api.response.TopAuthTokenCreateResponse;

/**
 * TOP API: taobao.top.auth.token.create request
 * 
 * @author top auto create
 * @since 1.0, 2015.08.20
 */
public class TopAuthTokenCreateRequest extends BaseTaobaoRequest<TopAuthTokenCreateResponse> {
	
	

	/** 
	* 授权code，grantType==authorization_code 时需要
	 */
	private String code;

	public void setCode(String code) {
		this.code = code;
	}
	public String getCode() {
		return this.code;
	}

	public String getApiMethodName() {
		return "taobao.top.auth.token.create";
	}

	public Map<String, String> getTextParams() {		
		TaobaoHashMap txtParams = new TaobaoHashMap();
		txtParams.put("code", this.code);
		if(this.udfParams != null) {
			txtParams.putAll(this.udfParams);
		}
		return txtParams;
	}

	public Class<TopAuthTokenCreateResponse> getResponseClass() {
		return TopAuthTokenCreateResponse.class;
	}

	public void check() throws ApiRuleException {
		RequestCheckUtils.checkNotEmpty(code, "code");
	}
	

}