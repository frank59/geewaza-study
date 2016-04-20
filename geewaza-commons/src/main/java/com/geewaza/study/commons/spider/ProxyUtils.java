package com.geewaza.study.commons.spider;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProxyUtils {
	public static final Log logger = LogFactory.getLog(ProxyUtils.class);

	private static String config_file = "proxy.hosts";
	private static List<Host> hosts;
	private static Random random = new Random();
	private static final Lock lock = new ReentrantLock(false);
	private static ProxyCheckThread checkThread = new ProxyCheckThread(hosts);
	private static ScheduledExecutorService pool;
	
	static{
		logger.info("初始化代理配置");
		try {
			InputStream input = ProxyUtils.class.getClassLoader().getResourceAsStream(config_file);
			try {
				init(input);
			} finally {
				input.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//启动验证代理可用的线程
		startCheckIps();
	}

	private static void startCheckIps(){
		logger.info("启动代理服务器监控线程");
		pool = Executors.newSingleThreadScheduledExecutor(new MyThreadFactory("checkip"));
		pool.scheduleWithFixedDelay(checkThread, 0, 10, TimeUnit.MINUTES);
	}

	public static void init(File configFile) {
		try {
			FileInputStream input = new FileInputStream(configFile);
			try {
				init(input);
			} finally {
				input.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void init(InputStream input) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		hosts = new ArrayList<Host>();
		String line = null;
		while ((line = reader.readLine()) != null) {
			String[] hostInfo = line.trim().split(":");
			if (hostInfo.length == 2) {
				try {
					hosts.add(new Host(hostInfo[0], Integer.parseInt(hostInfo[1])));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		checkThread.setHosts(hosts);
	}
	

	public static Host getProxyHost(){
		final Lock mainLock = lock;
    	try {
    		mainLock.lock();
		    if (hosts == null || hosts.size() == 0) {
			    return null;
		    }
		    return hosts.get(random.nextInt(hosts.size()));
    	} finally{
			mainLock.unlock();
		}
	}
	
	/**校验IP是否可以ping通*/
	public static class ProxyCheckThread implements Runnable {
		private List<Host> hosts;
		private final Runtime runtime;

		public ProxyCheckThread(List<Host> hosts) {
			super();
			this.hosts = hosts;
			this.runtime = Runtime.getRuntime();
		}

		public void setHosts(List<Host> hosts) {
			this.hosts = hosts;
		}

		@Override
		public void run() {
			if (hosts == null || hosts.size() == 0) {
				return;
			}
			List<Host> list = new ArrayList<Host>();
			List<Host> badlist = new ArrayList<Host>();
			
			for (Host host : hosts) {
				Socket connect = new Socket();
				boolean status = false;

				try {
					try {
						connect.connect(new InetSocketAddress(host.getIp(), host.getPort()), 500);
						status = connect.isConnected();
					} catch (UnknownHostException e) {
						status = false;
					} catch (IOException e) {
						status = false;
					}
	
					if (status){
						//TODO:可以随机等待一个时间(50-100毫秒)再check一次
						list.add(host);
					}else {
						badlist.add(host);
					}
				} catch (Exception e){
					runtime.exit(1);
					e.printStackTrace();
				}
			}
								
			//如果通的ip存在4个以上，才复写IPS
			if(list.size() >= 4){
				List<Host> okHosts = new ArrayList<Host>(list);
				ProxyUtils.hosts = okHosts;
			}
			
			//存储不能ping通的ip
			if(badlist.size() >= 1){
				//// TODO: 2016/4/13 记录失效的代理
//				List<InetSocketAddress> badHosts = new ArrayList<InetSocketAddress>(badlist);
			}
			
			Thread.yield();
		}
	}
	
	public static class MyThreadFactory implements ThreadFactory {
		private final AtomicInteger poolNumber = new AtomicInteger(1);
		private final ThreadGroup group;
		private final AtomicInteger threadNumber = new AtomicInteger(1);
		private final String prefix;

		public MyThreadFactory(String namePrefix) {
			SecurityManager s = System.getSecurityManager();
			group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
			prefix = namePrefix + "-" + poolNumber.getAndIncrement() + "-thread-";
		}

		@Override
		public Thread newThread(Runnable r) {
			Thread t = new Thread(group, r,prefix + threadNumber.getAndIncrement(), 0);

			t.setDaemon(true);

			if (t.getPriority() != Thread.NORM_PRIORITY - 3)
				t.setPriority(Thread.NORM_PRIORITY - 3);
			return t;
		}
	}

	public static class Host {
		private String ip;
		private int port;

		public String getIp() {
			return ip;
		}

		public void setIp(String ip) {
			this.ip = ip;
		}

		public int getPort() {
			return port;
		}

		public void setPort(int port) {
			this.port = port;
		}

		public Host(String ip, int port) {
			this.ip = ip;
			this.port = port;
		}

		@Override
		public String toString() {
			return "{" +
					"ip=" + ip +
					", port=" + port +
					"}";
		}
	}
}
