package com.taobao.api;

/**
 * 淘宝客户端。
 * 
 * @author carver.gu
 * @since 1.0, Sep 12, 2009
 */
public interface TaobaoClient {

	/**
	 * 执行TOP公开API请求。
	 * @param <T>
	 * @param request 具体的TOP请求
	 * @return 具体的TOP响应
	 */
	public <T extends TaobaoResponse> T execute(TaobaoRequest<T> request) throws ApiException;

	/**
	 * 执行TOP隐私API请求。
	 * @param <T>
	 * @param request 具体的TOP请求
	 * @param session 用户授权码
	 * @return 具体的TOP响应
	 */
	public <T extends TaobaoResponse> T execute(TaobaoRequest<T> request, String session) throws ApiException;
}
