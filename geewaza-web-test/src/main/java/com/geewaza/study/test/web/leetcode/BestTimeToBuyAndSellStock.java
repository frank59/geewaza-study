package com.geewaza.study.test.web.leetcode;

/**
 * Created by WangHeng on 2016/3/31.
 * 122. Best Time to Buy and Sell Stock II
 */
public class BestTimeToBuyAndSellStock {


    public int maxProfit(int[] prices) {
        int total = 0;
        for(int i = 1; i < prices.length; i++) {
            if (prices[i - 1] < prices[i]) {
                total += prices[i] - prices[i - 1];
            }
        }
        return total;
    }
}
