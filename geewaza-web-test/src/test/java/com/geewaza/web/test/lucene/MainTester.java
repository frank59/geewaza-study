package com.geewaza.web.test.lucene;

import com.geewaza.study.test.web.lucene.HelloLucene;

/**
 * Created by WangHeng on 2016/1/18.
 */
public class MainTester {

	public static void main(String[] args) {
		test02();
	}

	private static void test02() {
		HelloLucene hl1 = new HelloLucene();
		hl1.searcher();
	}

	private static void test01() {
		HelloLucene hl1 = new HelloLucene();
		hl1.index();
	}
}
