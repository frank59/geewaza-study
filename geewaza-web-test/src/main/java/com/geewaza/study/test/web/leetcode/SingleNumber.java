package com.geewaza.study.test.web.leetcode;

import org.junit.Test;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by wangh on 2016/3/23.
 * 136. Single Number
 * 利用XOR运算
 * (2^1^4^5^2^4^1) => ((2^2)^(1^1)^(4^4)^(5)) => (0^0^0^5) => 5
 */
public class SingleNumber {

	@Test
	public void test01() {
		System.out.println(singleNumber(new int[]{2,1,4,5,2,4,1}));
	}

	public int singleNumber(int[] nums) {
		int result = 0;
		for (int num : nums) {
			result ^= num;
		}
		return result;
	}
}
