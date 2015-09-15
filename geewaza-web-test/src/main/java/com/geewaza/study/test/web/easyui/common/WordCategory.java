package com.geewaza.study.test.web.easyui.common;

public enum WordCategory {
	
	推广词(1);
	private int cate;
	
	WordCategory(int cate) {
		this.cate = cate;
	}
	
	public WordCategory getWordCategory(int cate) {
		switch (cate) {
		case 1:return 推广词;
		default:return 推广词;
		}
	}
	
	public int getCate() {
		return cate;
	}
}
