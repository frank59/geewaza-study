package com.geewaza.study.test.web.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * Created by wangh on 2016/8/3.
 */
public class MainTester {
	private String groupNode = "sgroup";
	private String subNode = "sub";

	private static final String HOST_PROT = "zookeeper.geewaza.com:2181";

	public static void main(String[] args) throws Exception {
		test03();
	}

	private static void test03() throws IOException, KeeperException, InterruptedException {

		ZooKeeper zk = new ZooKeeper(HOST_PROT, 5000, new Watcher() {
			public void process(WatchedEvent event) {
				System.out.println(event.getType());
			}
		});

		zk.setData("/sgroup/sub", "321".getBytes(), -1);

		System.out.println(new String(zk.getData("/sgroup/sub",false,null)));
	}

	private static void test02() throws IOException, KeeperException, InterruptedException {

		ZooKeeper zk = new ZooKeeper(HOST_PROT, 5000, new Watcher() {
			public void process(WatchedEvent event) {
				System.out.println(event.getType());
			}
		});
		System.out.println(new String(zk.getData("/sgroup/sub",false,null)));
	}

	private static void test01() throws IOException, KeeperException, InterruptedException {

		ZooKeeper zk = new ZooKeeper(HOST_PROT, 5000, new Watcher() {
			public void process(WatchedEvent event) {
				System.out.println(event.getPath());
			}
		});

		zk.create(
				"/sgroup",
				"1".getBytes(),
				ZooDefs.Ids.OPEN_ACL_UNSAFE,
				CreateMode.PERSISTENT
		);

		zk.create(
				"/sgroup/sub",
				"123".getBytes(),
				ZooDefs.Ids.OPEN_ACL_UNSAFE,
				CreateMode.PERSISTENT
		);
	}
}
