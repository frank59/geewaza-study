package com.geewaza.study.test.web.leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by WangHeng on 2016/4/7.
 * 217	Contains Duplicate
 */
public class ContainsDuplicate {

    @Test
    public void test() {
        int[][] tests = {
                {1},
                {1,2,3,4},
                {1,2,3,4,1},
                {1,0,1,2,3},
        };
        for (int[] nums : tests) {
            System.out.println(containsDuplicate2(nums));
        }
    }

    public boolean containsDuplicate2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }

    public boolean containsDuplicate1(int[] nums) {
        Set<Integer> numSet = new HashSet<Integer>();
        for (int num : nums) {
            if (!numSet.add(num)) {
                return true;
            }
        }
        return false;
    }
}
