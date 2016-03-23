package com.geewaza.study.test.web.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by WangHeng on 2016/3/23.
 * 283. Move Zeroes
 */
public class MoveZeroes {

    @Test
    public void test01() {
        int[][] tests = {
                {1,2,0,3,5,1,0,1},
                {1,0,0,3,0,1,0,5,7,3},
                {1,2,0,0,},
                {0,0,1,2,0,0},
                {0,0,0,0,4,5},
        };
        for (int[] test : tests) {
            moveZeroes(test);
            System.out.println(Arrays.toString(test));
        }
    }


    public void moveZeroes(int[] nums) {
        if (nums.length < 2) {
            return ;
        }
        int i = 0;
        int j = 1;
        do {
           if (nums[i] != 0 && nums[j] != 0) {
               i++;j++;
           } else if (nums[i] == 0 && nums[j] != 0) {
               nums[i++] = nums[j];
               nums[j++] = 0;
           } else if (nums[i] == 0 && nums[j] == 0) {
               j++;
           } else {
               i++;
           }
        } while (j < nums.length);
    }
}
