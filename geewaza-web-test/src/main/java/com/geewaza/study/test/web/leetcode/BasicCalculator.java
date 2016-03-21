package com.geewaza.study.test.web.leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by wangh on 2016/3/21.
 * 224. Basic Calculator
 * https://leetcode.com/problems/basic-calculator/
 */
public class BasicCalculator {

	@Test
	public void test01() {
		String[][] tests = {
				{"1 + 1","2"},
				{" 2-1 + 2 ","3"},
				{"(1+(4+5+2)-3)+(6+8)","23"},
		};
		for (String[] test : tests) {
			System.out.println(calculate(test[0]) + "," + test[1]);
			assertEquals(Integer.parseInt(test[1]),calculate(test[0]));
		}
	}

	public int calculate(String s) {
		return eval(s, new int[]{0});
	}

	public int eval(String s, int[] p) {
		int result = 0;
		int i = p[0];
		int pre = 1;
		int sum = 0;
		do {
			char c = s.charAt(i);
			switch (c) {
				case ')': result += sum * pre; sum =0;pre = 1;i++;p[0] = i; return result;
				case '(': result += sum * pre; sum =0;i++;p[0] = i; result += eval(s, p) * pre; pre = 1;i = p[0];break;
				case '+': result += sum * pre; sum =0;pre = 1;i++;break;
				case '-': result += sum * pre; sum =0;pre = -1;i++;break;
				case ' ': result += sum * pre; sum =0; i++;continue;
				default: sum = sum * 10 + (c - '0');i++;break;
			}
		} while (i < s.length());
		return result + sum * pre;
	}
}
