package com.geewaza.study.test.web.zookeeper;

/**
 * Created by wangh on 2016/8/3.
 */
public class TestMain {

	private static final String HOST_PROT = "test.geewaza.com:2181";

	public static void main(String[] args) {
		String hostPort = HOST_PROT;
		String znode = "/sgroup/sub";
		String filename = "/opt/file/zookeeper.log";
		String exec[] = {"touch D:/opt/file/123.log"};
		try {
			new Executor(hostPort, znode, filename, exec).run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
