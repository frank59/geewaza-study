package com.geewaza.web.test;

import com.geewaza.study.commons.Request;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.nio.IOControl;
import org.apache.http.nio.client.methods.AsyncCharConsumer;
import org.apache.http.nio.client.methods.HttpAsyncMethods;
import org.apache.http.nio.protocol.HttpAsyncRequestProducer;
import org.apache.http.protocol.HttpContext;

import java.io.*;
import java.nio.CharBuffer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.zip.GZIPOutputStream;

public class MainTester {
	public static void main(String[] args) throws Exception {
		test03();
	}

	private static void test03() throws Exception {
		String inputFile = "/tmp/file/inputFile.dat";
		String outPutFile1 = "/tmp/file/gzipOutPutFile1.dat";
		String outPutFile2 = "/tmp/file/gzipOutPutFile2.dat";
		String response = Request.requestGet("http://10.103.88.189/searches/soku/direct/v4/by_keywords.json?keyword=%E5%A4%9A%E5%95%A6a%E6%A2%A6&searchType=2");
		OutputStream ops = new GZIPOutputStream(new FileOutputStream(outPutFile2));
		ops.write(response.getBytes());
		ops.close();
		FileUtils.writeByteArrayToFile(new File(outPutFile1), response.getBytes());


	}

	private static void test02() throws ParseException {
		String dateStr = "2012-05-07 07-58-28";
		int second = 5000;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sdf.parse(dateStr));
		calendar.add(Calendar.SECOND, second);
		String targetDate = sdf.format(calendar.getTime());
		System.out.println(dateStr + " ---> " + targetDate);
		calendar.add(Calendar.SECOND, second * -1);
		System.out.println(targetDate + " ---> " + sdf.format(calendar.getTime()));

	}

	private static void test01() throws ExecutionException, InterruptedException, IOException {

		CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
		try {
			// Start the client
			httpclient.start();

			// Execute request
			final HttpGet request1 = new HttpGet("http://www.apache.org/");
			Future<HttpResponse> future = httpclient.execute(request1, null);
			// and wait until a response is received
			HttpResponse response1 = future.get();
			System.out.println(request1.getRequestLine() + "->" + response1.getStatusLine());

			// One most likely would want to use a callback for operation result
			final CountDownLatch latch1 = new CountDownLatch(1);
			final HttpGet request2 = new HttpGet("http://www.apache.org/");
			httpclient.execute(request2, new FutureCallback<HttpResponse>() {

				public void completed(final HttpResponse response2) {
					latch1.countDown();
					System.out.println(request2.getRequestLine() + "->" + response2.getStatusLine());
				}

				public void failed(final Exception ex) {
					latch1.countDown();
					System.out.println(request2.getRequestLine() + "->" + ex);
				}

				public void cancelled() {
					latch1.countDown();
					System.out.println(request2.getRequestLine() + " cancelled");
				}

			});
			latch1.await();

			// In real world one most likely would also want to stream
			// request and response body content
			final CountDownLatch latch2 = new CountDownLatch(1);
			final HttpGet request3 = new HttpGet("http://www.apache.org/");
			HttpAsyncRequestProducer producer3 = HttpAsyncMethods.create(request3);
			AsyncCharConsumer<HttpResponse> consumer3 = new AsyncCharConsumer<HttpResponse>() {

				HttpResponse response;

				@Override
				protected void onResponseReceived(final HttpResponse response) {
					this.response = response;
				}

				@Override
				protected void onCharReceived(final CharBuffer buf, final IOControl ioctrl) throws IOException {
					// Do something useful
				}

				@Override
				protected void releaseResources() {
				}

				@Override
				protected HttpResponse buildResult(final HttpContext context) {
					return this.response;
				}

			};
			httpclient.execute(producer3, consumer3, new FutureCallback<HttpResponse>() {

				public void completed(final HttpResponse response3) {
					latch2.countDown();
					System.out.println(request2.getRequestLine() + "->" + response3.getStatusLine());
				}

				public void failed(final Exception ex) {
					latch2.countDown();
					System.out.println(request2.getRequestLine() + "->" + ex);
				}

				public void cancelled() {
					latch2.countDown();
					System.out.println(request2.getRequestLine() + " cancelled");
				}

			});
			latch2.await();

		} finally {
			httpclient.close();
		}
	}
}
