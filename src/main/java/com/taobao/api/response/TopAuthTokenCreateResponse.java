package com.taobao.api.response;

import com.taobao.api.internal.mapping.ApiField;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: taobao.top.auth.token.create response.
 * 
 * @author top auto create
 * @since 1.0, null
 */
public class TopAuthTokenCreateResponse extends TaobaoResponse {

	private static final long serialVersionUID = 5852576377384268287L;

	/** 
	 * 返回的是json信息，和之前调用https://oauth.taobao.com/tac/token https://oauth.alibaba.com/token 换token返回的字段信息一致
	 */
	@ApiField("token_result")
	private String tokenResult;

	public void setTokenResult(String tokenResult) {
		this.tokenResult = tokenResult;
	}
	public String getTokenResult( ) {
		return this.tokenResult;
	}
	


}
