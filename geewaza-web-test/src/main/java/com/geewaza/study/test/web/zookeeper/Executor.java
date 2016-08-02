package com.geewaza.study.test.web.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by wangh on 2016/8/3.
 */
public class Executor implements DataMonitorListener, Watcher, Runnable {

	private String znode;
	private String filename;
	private String[] exec;
	private ZooKeeper zk;
	private DataMonitor dm;
	private Process child;

	public Executor(String hostPort, String znode, String filename,
	                String exec[]) throws KeeperException, IOException {
		this.filename = filename;
		this.exec = exec;
		zk = new ZooKeeper(hostPort, 3000, this);
		dm = new DataMonitor(zk, znode, null, this);
	}

	@Override
	public void run() {
		try {
			synchronized (this) {
				while (!dm.isDead()) {
					wait();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void exists(byte[] data) {
		if (data == null) {
			if (child != null) {
				System.out.println("Killing process");
				child.destroy();
				try {
					child.waitFor();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			child = null;
		} else {
			if (child != null) {
				System.out.println("Stopping child");
				child.destroy();
				try {
					child.waitFor();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				FileOutputStream fos = new FileOutputStream(filename);
				fos.write(data);
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				System.out.println("Starting child");
				child = Runtime.getRuntime().exec(exec);
				new StreamWriter(child.getInputStream(), System.out);
				new StreamWriter(child.getErrorStream(), System.err);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void closing(int rc) {
		synchronized (this) {
			notifyAll();
		}
	}

	@Override
	public void process(WatchedEvent watchedEvent) {
		dm.process(watchedEvent);
	}

	static class StreamWriter extends Thread {
		OutputStream os;

		InputStream is;

		StreamWriter(InputStream is, OutputStream os) {
			this.is = is;
			this.os = os;
			start();
		}

		public void run() {
			byte b[] = new byte[80];
			int rc;
			try {
				while ((rc = is.read(b)) > 0) {
					os.write(b, 0, rc);
				}
			} catch (IOException e) {
			}

		}
	}
}
