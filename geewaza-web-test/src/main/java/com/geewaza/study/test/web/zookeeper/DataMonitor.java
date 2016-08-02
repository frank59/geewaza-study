package com.geewaza.study.test.web.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.Arrays;

/**
 * Created by wangh on 2016/8/3.
 */
public class DataMonitor  implements Watcher, AsyncCallback.StatCallback {
	private ZooKeeper zk;
	private String znode;
	private Watcher chainedWatcher;
	private DataMonitorListener listener;
	private boolean dead;
	private byte prevData[];

	public boolean isDead() {
		return dead;
	}

	public DataMonitor(ZooKeeper zk, String znode, Watcher chainedWatcher,
	                   DataMonitorListener listener) {
		this.zk = zk;
		this.znode = znode;
		this.chainedWatcher = chainedWatcher;
		this.listener = listener;

		// Get things started by checking if the node exists. We are going
		// to be completely event driven
		zk.exists(znode, true, this, null);
	}

	@Override
	public void processResult(int i, String s, Object o, Stat stat) {
		boolean exists;
		switch (i) {
			case KeeperException.Code.Ok:
				exists = true;
				break;
			case KeeperException.Code.NoNode:
				exists = false;
				break;
			case KeeperException.Code.SessionExpired:
			case KeeperException.Code.NoAuth:
				dead = true;
				listener.closing(i);
				return;
			default:
				// Retry errors
				zk.exists(znode, true, this, null);
				return;
		}

		byte b[] = null;
		if (exists) {
			try {
				b = zk.getData(znode, false, null);
			} catch (KeeperException e) {
				// We don't need to worry about recovering now. The watch
				// callbacks will kick off any exception handling
				e.printStackTrace();
			} catch (InterruptedException e) {
				return;
			}
		}
		if ((b == null && b != prevData)
				|| (b != null && !Arrays.equals(prevData, b))) {
			listener.exists(b);
			prevData = b;
		}
	}

	@Override
	public void process(WatchedEvent watchedEvent) {
		String path = watchedEvent.getPath();
		if (watchedEvent.getType() == Event.EventType.None) {
			// We are are being told that the state of the
			// connection has changed
			switch (watchedEvent.getState()) {
				case SyncConnected:
					// In this particular example we don't need to do anything
					// here - watches are automatically re-registered with
					// server and any watches triggered while the client was
					// disconnected will be delivered (in order of course)
					break;
				case Expired:
					// It's all over
					dead = true;
					listener.closing(KeeperException.Code.SessionExpired);
					break;
			}
		} else {
			if (path != null && path.equals(znode)) {
				// Something has changed on the node, let's find out
				zk.exists(znode, true, this, null);
			}
		}
		if (chainedWatcher != null) {
			chainedWatcher.process(watchedEvent);
		}
	}
}
