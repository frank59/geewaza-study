package com.geewaza.web.test.httpasync;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by WangHeng on 2016/2/1.
 * 部署这个工程，使用web容器将该工程以web服务的方式启动，监听8080端口
 * 再执行该测试程序
 */
public class MainTester {

	public static void main(String[] args) throws Exception {
		test02();
	}

	private static void test02() throws IOException {
		//异步请求Http接口
		String URL = "http://localhost:8080/geewaza/costTimeService.do?key=%s";
		String keywords = "锄禾日当午,汗滴禾下土,谁知盘中餐,粒粒皆幸苦";
		CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
		httpclient.start();
		String[] keys = keywords.split(",");
		final CountDownLatch countDownLatch = new CountDownLatch(keys.length);
		List<HttpGet> httpGetList = new ArrayList<HttpGet>();
		for (String key : keys) {
			final String url = String.format(URL, key);
			httpGetList.add(new HttpGet(url));
		}
		long start = System.currentTimeMillis();
		try {
			final List<String> resultList = new ArrayList<String>();
			for (final HttpGet request : httpGetList) {
				httpclient.execute(request, new FutureCallback<HttpResponse>() {
					@Override
					public void completed(HttpResponse httpResponse) {
						System.out.println("请求完成：" + request.getRequestLine());
						int BUFFER_SIZE = 8 * 1024;
						InputStream in = null;
						try {
							in = httpResponse.getEntity().getContent();
							ByteArrayOutputStream outStream = new ByteArrayOutputStream();
							byte[] data = new byte[BUFFER_SIZE];
							int count = -1;
							while((count = in.read(data,0,BUFFER_SIZE)) != -1) {
								outStream.write(data, 0, count);
							}
							JSONObject resultJSON = new JSONObject(new String(outStream.toByteArray(),"UTF-8"));
							System.out.println(resultJSON);
							String dataStr = resultJSON.optString("data");
							resultList.add(dataStr);
						} catch (IOException e) {
							e.printStackTrace();
						}
						countDownLatch.countDown();
					}

					@Override
					public void failed(Exception e) {
						System.out.println("请求失败：" + request.getRequestLine());
						countDownLatch.countDown();
					}

					@Override
					public void cancelled() {
						System.out.println("请求取消：" + request.getRequestLine());
						countDownLatch.countDown();
					}
				});
			}
			countDownLatch.await();
			System.out.println("耗时:" + (System.currentTimeMillis() - start) + "ms");
			System.out.println(resultList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.close();
		}
		System.exit(0);
	}

	private static void test01() throws IOException {
		//异步请求Http接口
		String URL = "http://localhost:8080/geewaza/costTimeService.do?key=%s";
		int requestCount = 10;
		CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
		httpclient.start();
		final CountDownLatch countDownLatch = new CountDownLatch(requestCount);
		List<HttpGet> httpGetList = new ArrayList<HttpGet>();
		for (int i = 0; i < requestCount; i++) {
			final String url = String.format(URL, i+ "");
			httpGetList.add(new HttpGet(url));
		}
		long start = System.currentTimeMillis();
		try {
			for (final HttpGet request : httpGetList) {
				httpclient.execute(request, new FutureCallback<HttpResponse>() {
					@Override
					public void completed(HttpResponse httpResponse) {
						System.out.println("请求完成：" + request.getRequestLine());
						int BUFFER_SIZE = 8 * 1024;
						InputStream in = null;
						try {
							in = httpResponse.getEntity().getContent();
							ByteArrayOutputStream outStream = new ByteArrayOutputStream();
							byte[] data = new byte[BUFFER_SIZE];
							int count = -1;
							while((count = in.read(data,0,BUFFER_SIZE)) != -1) {
								outStream.write(data, 0, count);
							}
							System.out.println(new String(outStream.toByteArray(),"UTF-8"));
						} catch (IOException e) {
							e.printStackTrace();
						}
						countDownLatch.countDown();
					}

					@Override
					public void failed(Exception e) {
						System.out.println("请求失败：" + request.getRequestLine());
						countDownLatch.countDown();
					}

					@Override
					public void cancelled() {
						System.out.println("请求取消：" + request.getRequestLine());
						countDownLatch.countDown();
					}
				});
			}
			countDownLatch.await();
			System.out.println("耗时:" + (System.currentTimeMillis() - start) + "ms");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.close();
		}
		System.exit(0);
	}

}
