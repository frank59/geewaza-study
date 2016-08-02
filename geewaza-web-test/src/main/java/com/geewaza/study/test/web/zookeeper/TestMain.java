package com.geewaza.study.test.web.zookeeper;

/**
 * Created by wangh on 2016/8/3.
 */
public class TestMain {

	private static final String HOST_PROT = "192.168.11.209:2181";

	public static void main(String[] args) {
		if (args.length < 4) {
			System.out.println("USAGE: Executor hostPort znode filename program [args ...]");
			System.exit(2);
		}
		String hostPort = HOST_PROT;
		String znode = args[1];
		String filename = args[2];
		String exec[] = new String[args.length - 3];
		System.arraycopy(args, 3, exec, 0, exec.length);
		try {
			new Executor(hostPort, znode, filename, exec).run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
