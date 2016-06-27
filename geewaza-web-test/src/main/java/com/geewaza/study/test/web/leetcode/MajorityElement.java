package com.geewaza.study.test.web.leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by WangHeng on 2016/4/7.
 * 169	Majority Element
 */
public class MajorityElement {

    @Test
    public void test() {
        int[][] tests = {
                {1},
                {2,2},
                {0,0,0,0,1,2,3,4},
                {2,3,2,3,2},
        };
        for (int[] nums : tests) {
            System.out.println(majorityElement2(nums));
        }
    }


    public int majorityElement2(int[] nums) {
        int flag = 1;
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (flag == 0) {
                result = nums[i];
            }
            if (result == nums[i]) {
                flag++;
            } else {
                flag--;
            }
        }
        return result;
    }

    public int majorityElement(int[] nums) {
        if (nums.length / 2 == 0) {
            return nums[0];
        }
        int max = 0;
        Integer result = null;
        Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
        for (int num : nums) {
            Integer count = countMap.get(num);
            if (count == null) {
                count = 0;
            }
            count +=1;
            if (count > max) {
                result = num;
                max = count;
            }
            countMap.put(num, count);
        }
        return result;
    }
}
