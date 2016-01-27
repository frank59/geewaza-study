package com.geewaza.study.commons.spider;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
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
	
	//ip池
	private static String[] ipPool = new String[] {
			"115.231.179.145", "118.228.16.130", "182.131.6.11" };
	
	private static String[] ips = ipPool;
	private static Random random = new Random();
	private static final Lock lock = new ReentrantLock(false);
	
	static{
		startCheckIps(ipPool);
	}
	
	public static void startCheckIps(String[] ips){
		ScheduledExecutorService pool = Executors.newSingleThreadScheduledExecutor(new MyThreadFactory("checkip"));
		pool.scheduleWithFixedDelay(new ProxyCheckThread(ips), 0, 10, TimeUnit.MINUTES);
	}
	
	public static String getProxyIp(){	
		final Lock mainLock = lock;
    	try {
    		mainLock.lock();
			
    		return ips[random.nextInt(ips.length)];
    	} finally{
			mainLock.unlock();
		}
	}
	
	/**校验IP是否可以ping通*/
	public static class ProxyCheckThread implements Runnable {
		private String[] poolIps;
		private final Runtime runtime;
		
		public ProxyCheckThread(String[] ips) {
			super();
			
			this.poolIps = ips;
			this.runtime = Runtime.getRuntime();
		}

		@Override
		public void run() {
			List<String> list = new ArrayList<String>();
			List<String> badlist = new ArrayList<String>();
			
			for (String ip : poolIps) {
				Socket connect = new Socket();
				boolean status = false;

				try {
					try {
						connect.connect(new InetSocketAddress(ip, 8181), 500);
						status = connect.isConnected();
					} catch (UnknownHostException e) {
						status = false;
					} catch (IOException e) {
						status = false;
					}
	
					if (status){
						//TODO:可以随机等待一个时间(50-100毫秒)再check一次
						list.add(ip);
					}else {
						badlist.add(ip);
					}
				} catch (Exception e){
					runtime.exit(1);
					e.printStackTrace();
				}
			}
								
			//如果通的ip存在4个以上，才复写IPS
			if(list.size() >= 4){
				String[] array = new String[list.size()];
				ProxyUtils.ips = list.toArray(array);
			}
			
			//存储不能ping通的ip
			if(badlist.size() >= 1){
				String[] array = new String[badlist.size()];
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
}
