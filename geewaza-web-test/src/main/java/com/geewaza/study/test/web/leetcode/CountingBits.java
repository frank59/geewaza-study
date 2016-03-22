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
		System.out.println(Arrays.toString(countBits(5)));
	}

	public int[] countBits(int num) {

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
}
