package com.taobao.api.response;

import com.taobao.api.internal.mapping.ApiField;
import com.taobao.api.domain.KfcSearchResult;

import com.taobao.api.TaobaoResponse;

/**
 * TOP API: taobao.kfc.keyword.search response.
 * 
 * @author top auto create
 * @since 1.0, null
 */
public class KfcKeywordSearchResponse extends TaobaoResponse {

	private static final long serialVersionUID = 4248294737256456325L;

	/** 
	 * KFC关键词匹配返回的结果信息
	 */
	@ApiField("kfc_search_result")
	private KfcSearchResult kfcSearchResult;

	public void setKfcSearchResult(KfcSearchResult kfcSearchResult) {
		this.kfcSearchResult = kfcSearchResult;
	}
	public KfcSearchResult getKfcSearchResult( ) {
		return this.kfcSearchResult;
	}
	


}
