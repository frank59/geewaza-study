package com.geewaza.study.test.web.leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by wangh on 2016/3/22.'
 * 292. Nim Game
 * 这题往小说可以追溯到小学奥数或者脑筋急转弯的书中，往大说可以深究到博弈论。
 * 然而编程在这里并没有卵用，策略在于，因为每个人都取不到4个，
 * 假设自己后走，要保证每轮自己和对方取得数量的和是4，这样就能确保每轮完后都有4的倍数个石头被取走。
 * 这样，如果我们先走的话，先把n除4的余数个石头拿走，
 * 这样不管怎样，到最后都会留4个下来，对方取1个你就取3个，对方取2个你就取2个，就必赢了。
 */
public class NimGame {
	@Test
	public void test01() {
		assertEquals(true,canWinNim(14));
		assertEquals(false,canWinNim(12));
	}

	public boolean canWinNim(int n) {
		return n % 4 !=0;
	}
}
