package sgg.qin.util;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;

import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
public class HttpClientUtil {

	@Autowired
	public CloseableHttpClient httpclient;
	@Autowired
	public RequestConfig requestConfig;

	public static CloseableHttpClient name1(@Autowired CloseableHttpClient httpclient) {
		return httpclient;
	}

	/**
	 * 构建uri
	 * 
	 * @param scheme
	 *            请求协议
	 * @param host
	 *            主机地址
	 * @param port
	 *            端口号
	 * @param path
	 *            请求路径
	 * @param Parameter
	 *            请求参数
	 * 
	 */
	public static URI buildUrl(String scheme, String host, Integer port, String path, Map<String, String> parameters)
			throws URISyntaxException {
		URI uri = new URIBuilder().setScheme("http").setHost("jisutqybmf.market.alicloudapi.com").setPort(80)
				.setPath("/weather/query").setParameter("city", "深圳").build();

		URIBuilder uriBuilder = new URIBuilder();
		if (StringUtils.isNotEmpty(scheme))
			uriBuilder.setScheme(scheme);
		if (StringUtils.isNotEmpty(host))
			uriBuilder.setHost(host);
		if (port != null)
			uriBuilder.setPort(port);
		if (StringUtils.isNotEmpty(path))
			uriBuilder.setPath(path);
		if (parameters != null) {
			for (Entry<String, String> entry : parameters.entrySet()) {
				uriBuilder.setParameter(entry.getKey(), entry.getValue());
			}

		}
		return uriBuilder.build();
	}

	/**
	 * 这里使用泛型方法 传入响应处理器 使用响应处理器是官方最推荐的方式
	 * 
	 * @param mehtod
	 *            请求方式
	 * @param scheme
	 *            请求协议
	 * @param host
	 *            主机地址
	 * @param port
	 *            端口号
	 * @param path
	 *            请求路径
	 * @param parameter
	 *            请求参数
	 * @param headers
	 *            请求头
	 * @param responseHandler
	 *            响应处理器 响应留给用户处理
	 */
	public <T> T doHttp(RequestMethod mehtod, String scheme, String host, Integer port, String path,
			Map<String, String> parameters, Map<String, String> headers, ResponseHandler<T> responseHandler)
			throws URISyntaxException, ClientProtocolException, IOException {
		URI uri = null;
		HttpUriRequest httpUriRequest = null;
		switch (mehtod) {
		case GET:
			uri = HttpClientUtil.buildUrl(scheme, host, port, path, parameters);
			HttpGet httpget = new HttpGet(uri);
			httpget.setConfig(requestConfig);
			httpUriRequest = httpget;
			break;

		case POST:
			uri = HttpClientUtil.buildUrl(scheme, host, port, path, null);
			HttpPost httpPost = new HttpPost(uri);
			httpPost.setConfig(requestConfig);
			// 设置请求体
			if (parameters != null) {
				List<NameValuePair> formparams = new ArrayList<NameValuePair>();
				if (headers != null) {
					for (Entry<String, String> entry : parameters.entrySet()) {
						formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
					}
				}
				UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
				httpPost.setEntity(urlEncodedFormEntity);
			}
			httpUriRequest = httpPost;

			break;

		default:
			break;
		}

		// 设置请求头
		if (headers != null) {
			for (Entry<String, String> entry : headers.entrySet()) {
				httpUriRequest.addHeader(entry.getKey(), entry.getValue());
			}
		}

		return httpclient.execute(httpUriRequest, responseHandler);
	}

}
