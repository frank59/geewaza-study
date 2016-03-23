package com.geewaza.study.test.web.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by WangHeng on 2016/3/23.
 * 238. Product of Array Except Self
 *
 * 错位计算可以节约步骤
 * 错位的数组是正向累计，而tmp是逆向累计的，所以正好可以错开
 *
 * nums     2  ,  3  ,  4  ,  5
 * result   1  ,  2  ,  6  , 24     临时
 *                           ↑
 *                         这个可以不用动
 *                      ↑
 *                6已经是2*3 正好错开4  还差5  tmp反过来累乘
 *              ↑
 *           到3时正好tmp累乘4*5  缺2正好对应
 */
public class ProductofArrayExceptSelf {

    @Test
    public void test01() {
        int[][] tests = {
                {2,3,4,5},
                {7,4,7,1},
                {9,1,2,3},
                {1,2,3,4},
                {0,0},
                {1,0},
        };

        for (int[] test : tests) {
            System.out.println(Arrays.toString(productExceptSelf(test)));
        }
    }

    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        result[0] = 1;
        for (int i = 1; i < result.length; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }
        int tmp = 1;
        for (int j = result.length - 2; j >= 0; j--) {
            tmp *= nums[j + 1];
            result[j] *= tmp;
        }
        return result;
    }
}
