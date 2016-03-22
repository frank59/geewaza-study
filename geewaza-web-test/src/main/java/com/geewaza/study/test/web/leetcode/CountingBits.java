package com.geewaza.study.test.web.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by wangh on 2016/3/22.
 * 338. Counting Bits
 */
public class CountingBits {

	@Test
	public void test01() {
		System.out.println(Arrays.toString(countBits1(5)));
	}
	@Test
	public void test02() {
		System.out.println(Arrays.toString(countBits2(5)));
	}
	public int[] countBits2(int num) {

		int[] result = new int[num+1];
		for (int i = 0; i < num+1; i++) {
			count2(i, result);
		}
		return result;
	}

	public int[] countBits1(int num) {

		int[] result = new int[num+1];
		for (int i = 0; i < num+1; i++) {
			result[i] = count(i);
		}
		return result;
	}

	private int count(int i) {
		int result = 0;
		while (i != 0){
			result += i & 1;
			i >>= 1;
		}
		return result;
	}

	private void count2(int i, int[] array) {
		array[i] = array[i / 2] + i % 2;
	}
}
