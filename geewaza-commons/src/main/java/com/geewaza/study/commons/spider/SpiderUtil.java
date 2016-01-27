package com.geewaza.study.commons.spider;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpiderUtil {
	private boolean needProxy;
	private boolean haveInet;

	private CloseableHttpClient httpClient;

	public void setHttpClient(CloseableHttpClient httpClient) {
		this.httpClient = httpClient;
	}

	public void setNeedProxy(boolean needProxy) {
		this.needProxy = needProxy;
	}

	public void setHaveInet(boolean haveInet) {
		this.haveInet = haveInet;
	}

	/** get 方式获取源码 */
	public String getSource(String url) throws Exception {
		HttpRes ret = getRes(url, needProxy, null);

		if (ret != null && ret.getDatas() != null && ret.getDatas().length > 0) {
			return ret.getSource();
		}

		return "";
	}

	/** get 方式获取源码 */
	public String getSource(String url, boolean needProxy) throws Exception {
		HttpRes ret = getRes(url, needProxy, null);

		if (ret != null && ret.getDatas() != null && ret.getDatas().length > 0) {
			return ret.getSource();
		}

		return "";
	}

	/** get 方式获取源码 */
	public String getSource(String url, String charset) throws Exception {
		HttpRes ret = getRes(url, needProxy, null, charset);

		if (ret != null && ret.getDatas() != null && ret.getDatas().length > 0) {
			return ret.getSource();
		}

		return "";
	}

	/** post 方式获取源码 */
	public String getSource(String url, List<NameValuePair> postDict)
			throws Exception {
		HttpRes ret = getRes(url, needProxy, postDict);

		if (ret != null && ret.getDatas() != null && ret.getDatas().length > 0) {
			return ret.getSource();
		}

		return "";
	}

	public byte[] get(String url) throws Exception {
		return get(url, needProxy);
	}

	private byte[] get(String url, boolean needProxy) throws Exception {
		HttpRes ret = getRes(url, needProxy, null);

		if (ret != null && ret.getDatas() != null && ret.getDatas().length > 0) {
			return ret.getDatas();
		}
		return new byte[0];
	}
	
	public HttpRes getRes(String url) throws Exception {
		return getRes(url, needProxy, null);
	}
	
	public HttpRes getRes(String url,boolean needProxy) throws Exception {
		return getRes(url, needProxy, null);
	}

	public HttpRes getRes(String url, boolean needProxy,
			List<NameValuePair> postDict) throws Exception {
		return getRes(url, needProxy, postDict, null);
	}

	public HttpRes getRes(String url, boolean needProxy,
	                      List<NameValuePair> postDict, String charset) throws Exception {
		HttpRes ret = null;
		Exception tmp = null;

		for (int i = 0; i < 3; i++) {
			try {
				if (i == 2 && haveInet) {
					ret = get_(url, false, postDict, charset);
				} else {
					ret = get_(url, needProxy, postDict, charset);
				}
			} catch (Exception e) {
				if (i == 2) {
					if (tmp != null) {
						tmp = new Exception(url + tmp.getMessage() + " " + i + ":" + e.getMessage(), e);
					} else {
						tmp = new Exception(i + ":" + e.getMessage(), e);
					}
				} else {
					ret = null;
				}
			}

			if (ret != null) {
				return ret;
			}
		}
		if (tmp != null) {
			throw tmp;
		}
		return ret;
	}

	private HttpRes get_(String pageUrl, boolean needProxy,
	                     List<NameValuePair> postDict, String charset) throws Exception {
		URL url = null;
		URI uri = null;
		InputStream inputStream = null;
		CloseableHttpResponse response = null;
		HttpUriRequest request = null;
		String proxyIp = "";

		try {
			Builder builder = getBuilder();

			if (needProxy) {
				setRequestProxy(builder);
			}

			RequestConfig requestConfig = builder.build();

			url = new URL(pageUrl);
			uri = new URI(url.getProtocol(), null, url.getHost(),
					url.getPort(), url.getPath(), url.getQuery(), null);

			if (null != postDict) {
				HttpEntity postBodyEnt = new UrlEncodedFormEntity(postDict,"UTF-8");
				request = RequestBuilder.post().setUri(uri)
						.setConfig(requestConfig).setEntity(postBodyEnt)
						.build();
			} else {
				request = RequestBuilder.get().setUri(uri)
						.setConfig(requestConfig).build();
			}
			String ua = getUserAgent();
			request.addHeader("User-Agent", ua);
//			request.addHeader("Accept","*/*");
//			request.addHeader("Connection","keep-alive");
//			request.addHeader("Accept-Language","zh-cn");
//			request.addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.93 Safari/537.36");
//			request.addHeader("Cache-Control","no-cache");
//			request.addHeader("host","no-cache");

			CookieStore cookieStore = new BasicCookieStore();
			HttpClientContext localContext = HttpClientContext.create();
			localContext.setCookieStore(cookieStore);
			response = httpClient.execute(request, localContext);

			int statusCode = response.getStatusLine().getStatusCode();

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];

			inputStream = response.getEntity().getContent();

			while (true) {
				int length = inputStream.read(buffer);
				if (length < 1) {
					break;
				}

				outputStream.write(buffer, 0, length);
			}

			byte[] data = outputStream.toByteArray();

			EntityUtils.consume(response.getEntity());

			return new HttpRes(pageUrl, pageUrl, statusCode, charset, data);
		} catch (Exception e) {
			if (needProxy) {
				throw new Exception(proxyIp + "|||" + e.getMessage(), e);
			} else {
				throw new Exception(proxyIp + "|||" + e.getMessage(), e);
			}
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (response != null) {
				response.close();
			}
			request.abort();
		}
	}

	/** 设置请求代理IP、端口 */
	private void setRequestProxy(Builder builder) {
		String ip = ProxyUtils.getProxyIp();
		int port = 8181;
		HttpHost proxy = new HttpHost(ip, port);
		builder.setProxy(proxy);
	}

	/** 获取 Builder 对象 */
	private Builder getBuilder() {
		Builder builder = RequestConfig.custom();
		builder.setConnectionRequestTimeout(3000)
				.setConnectTimeout(1000).setSocketTimeout(10 * 1000)
				.setCircularRedirectsAllowed(true).setCookieSpec("easy")
				.setCookieSpec(CookieSpecs.DEFAULT);
		return builder;
	}

	private final Random random = new Random();
	private final String[] UA = new String[] {
			"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:42.0) Gecko/20100101 Firefox/42.0",
			"baidu.com",
			"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 Safari/537.36 SE 2.X MetaSr 1.0",
			"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36",
			"soku.com" };

	/** 获取User-Agent  */
	private String getUserAgent() {
		String ua = UA[random.nextInt(UA.length)];
		return ua;
	}

	public class HttpRes {
		private final int statusCode;
		private String charset;
		private final byte[] datas;
		private final String url;
		private final String requestUrl;

		public HttpRes(String requestUrl, String url, int statusCode,
				String charset, byte[] datas) {
			this.statusCode = statusCode;
			if (charset != null && charset.trim().length() > 0) {
				this.charset = charset;
			} else {
				this.charset = getHtmlcode(datas);

			}

			if (this.charset != null) {
				if (this.charset.equalsIgnoreCase("gb2312")) {
					this.charset = "GBK";
				}

			}
			this.datas = datas;
			this.url = url;
			if (this.url.endsWith(".xml")) {
				this.charset = getXmlcode(datas);
			}
			this.requestUrl = requestUrl;
		}

		public int getStatusCode() {
			return statusCode;
		}

		public String getCharset() {
			return charset;
		}

		public byte[] getDatas() {
			return datas;
		}

		public String getUrl() {
			return url;
		}

		public String getRequestUrl() {
			return requestUrl;
		}

		public String getSource() {
			String source = "";
			if (datas != null && datas.length > 0) {
				try {
					return new String(datas, charset);
				} catch (Exception e) {
					try {
						source = new String(datas, charset);
					} catch (UnsupportedEncodingException e1) {
					}
				}
			}
			return source;
		}

		private String getHtmlcode(byte[] out) {
			String charSet = "UTF-8";
			try {
				String regEx = "(<meta)\\s+(charset=[\\'|\\\"]?)([\\w|-]*)";
				Pattern p = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
				Matcher m = p.matcher(new String(out)); // 默认编码转成字符串，因为我们的匹配中无中文，所以串中可能的乱码对我们没有影响
				if (m.find() && m.groupCount() == 3) {
					charSet = m.group(3);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return charSet;
		}

		private String getXmlcode(byte[] out) {
			try {
				new String(out, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return "utf-8";
		}
	}
}
