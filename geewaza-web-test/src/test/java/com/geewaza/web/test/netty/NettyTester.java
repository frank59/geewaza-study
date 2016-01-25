package com.geewaza.web.test.netty;

import com.geewaza.study.test.web.lucene.IndexUtil;
import com.geewaza.study.test.web.netty.NettyUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by WangHeng on 2016/1/21.
 */
public class NettyTester {

	public static void main(String[] args) throws InterruptedException, IOException {
		test03();
	}

	private static void test03() throws IOException {

		IndexUtil indexUtil = new IndexUtil();
		indexUtil.query();
	}

	private static void test02() throws IOException {

		IndexUtil indexUtil = new IndexUtil();
		indexUtil.index();
	}

	private static void test01() throws InterruptedException {
		System.out.println("start");
		NettyUtils.Server server = NettyUtils.startServer(8800);
//		System.out.println("sleep 10s");
//		TimeUnit.SECONDS.sleep(10);
//		server.shutdown();
		System.out.println("OK");

	}
}
