package com.geewaza.web.test;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wangh on 2016/8/10.
 */
public class Top1 {

	private static final String FTP_DIR = "/opt/ftp/";

	private static ExecutorService readerExecutor = Executors.newFixedThreadPool(3); //3个生产线程

	private static ConcurrentLinkedQueue<Record> queue = new ConcurrentLinkedQueue<Record>();//用ConcurrentLinkedQueue会好一些

	private static ExecutorService consumerExecutor = Executors.newFixedThreadPool(5);//5个消费线程

	private static ConcurrentSkipListMap<Integer, ConcurrentSkipListMap<Record, Object>> groupMap = new ConcurrentSkipListMap<Integer, ConcurrentSkipListMap<Record, Object>>();


	public static void main(String[] args) {
		startReader();//启动生产者
		startConsumer();//启动消费者
		try {
			TimeUnit.SECONDS.sleep(10);//等待完成?
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Iterator<Integer> it = groupMap.keySet().iterator();
		System.out.println("分组TOP1");
		while (it.hasNext()) {
			int key = it.next();
			ConcurrentSkipListMap<Record, Object> map = groupMap.get(key);
			Record first = map.firstKey();
			System.out.println("分组 " + key + "," + first.getId() + "," + first.getKpi1());
		}
		System.out.println("OK!");
	}

	private static void startReader() {
		File dir = new File(FTP_DIR);
		File[] fileList = dir.listFiles();
		for (File file : fileList) {
			readerExecutor.execute(new RecordReader(queue, file));
		}
		readerExecutor.shutdown();//任务投递结束 不再接受任务
		System.out.println("reader任务投递结束");

	}

	private static void startConsumer() {
		for (int i = 0; i < 5; i++) {
			consumerExecutor.execute(new RecordConsumer(queue, groupMap));
		}
	}



	/**
	 * 生产者  读取文件并将record放入到队列中供消费者消费
	 */
	public static class RecordReader implements Runnable {

		final private ConcurrentLinkedQueue<Record> queue;

		final private File file;

		public RecordReader(ConcurrentLinkedQueue<Record> queue, File file) {
			this.queue = queue;
			this.file = file;
		}

		@Override
		public void run() {
			try {
				List<Record> records = loadRecord(file);
				for (Record record : records) {
					queue.offer(record);
				}
				System.out.println("file:" + file.getName() + "读取完成");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * 读取文件中的记录
		 * @param file
		 * @return
		 * @throws IOException
		 */
		public List<Record> loadRecord(File file) throws IOException {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			List<Record> result = new ArrayList<Record>();
			try {
				String line = null;
				while ((line = reader.readLine()) != null) {
					if (!line.trim().equals("")) {
						String[] lineItems = line.split(",");
						Record record = new Record();
						record.setId(Long.parseLong(lineItems[0]));
						record.setGroupId(Integer.parseInt(lineItems[1]));
						record.setKpi1(Double.parseDouble(lineItems[2]));
						record.setKpi2(Double.parseDouble(lineItems[3]));
						record.setKpi3(Double.parseDouble(lineItems[4]));
						result.add(record);
					}
				}

			} finally {
				reader.close();
			}
			return result;
		}
	}


	/**
	 * 消费者，将queue中的数据映射到map中
	 */
	public static class RecordConsumer implements Runnable {

		private static ReentrantLock lock = new ReentrantLock();  //同步锁

		final private ConcurrentLinkedQueue<Record> queue;

		final private ConcurrentSkipListMap<Integer, ConcurrentSkipListMap<Record, Object>> groupMap;

		public RecordConsumer(ConcurrentLinkedQueue<Record> queue, ConcurrentSkipListMap<Integer, ConcurrentSkipListMap<Record, Object>> groupMap) {
			this.queue = queue;
			this.groupMap = groupMap;
		}

		@Override
		public void run() {
			System.out.println("启动consumer");
			try {
				while(true) {//无限消费
					Record record = queue.poll();//获取并移除元素
					if(record == null) {
						TimeUnit.MILLISECONDS.sleep(100);
						continue;
					}
					int groupId = record.getGroupId();
					ConcurrentSkipListMap<Record, Object> sortedMap = groupMap.get(groupId);
					if (sortedMap == null) {
						lock.lock();
						sortedMap = groupMap.get(groupId);//再次确认是否为空
						try {
							if (sortedMap == null) {
								sortedMap = new ConcurrentSkipListMap<Record, Object>(new Comparator<Record>() {
									@Override
									public int compare(Record o1, Record o2) {
										return Double.compare(o2.getKpi1(), o1.getKpi1());//倒叙排列
									}
								});
								System.out.println("创建新的sortedMap:groupId=" + groupId);
								groupMap.put(groupId, sortedMap);//同步模块中 直接Put
							}
						} finally {
							lock.unlock();
						}
						sortedMap.put(record, 1);//value常量可以节省内存
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static class Record {
		private long id;
		private int groupId;
		private double kpi1;
		private double kpi2;
		private double kpi3;

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public int getGroupId() {
			return groupId;
		}

		public void setGroupId(int groupId) {
			this.groupId = groupId;
		}

		public double getKpi1() {
			return kpi1;
		}

		public void setKpi1(double kpi1) {
			this.kpi1 = kpi1;
		}

		public double getKpi2() {
			return kpi2;
		}

		public void setKpi2(double kpi2) {
			this.kpi2 = kpi2;
		}

		public double getKpi3() {
			return kpi3;
		}

		public void setKpi3(double kpi3) {
			this.kpi3 = kpi3;
		}

		@Override
		public String toString() {
			return "{" +
					"id=" + id
			        + ", groupId=" + groupId
					+ ", kpi1=" + kpi1
					+ ", kpi2=" + kpi2
					+ ", kpi3=" + kpi3
					+ "}";
		}
	}


}
