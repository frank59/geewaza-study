package com.geewaza.web.test.netty;

import com.geewaza.study.test.web.netty.NettyUtils;

/**
 * Created by WangHeng on 2016/1/22.
 */
public class NettyClientTester {

	public static void main(String[] args) throws Exception {
		test01();
	}

	private static void test01() throws Exception {
		String host = "localhost";
		int port = 8800;
		NettyUtils.CommandMessage commandMessage = new NettyUtils.CommandMessage();
		commandMessage.setKey("Kubox");
		commandMessage.setCommand("reload");

		NettyUtils.sendCommandMessage(host, port, commandMessage);

	}
}
