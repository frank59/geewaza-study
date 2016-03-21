package com.geewaza.study.test.web.leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.

 You may assume that each input would have exactly one solution.

 Example:
 Given nums = [2, 7, 11, 15], target = 9,

 Because nums[0] + nums[1] = 2 + 7 = 9,
 return [0, 1].
 UPDATE (2016/2/13):
 The return format had been changed to zero-based indices. Please read the above updated description carefully.

 Subscribe to see which companies asked this question
 * Created by wangh on 2016/3/21.
 */
public class TwoSum {


	@Test
	public void test01() {
		int[][][] tests = {
				{{2, 7, 11, 15},{9}},
				{{3,2,4},{6}}
		};
		for (int[][] item : tests) {
			int[] nums = item[0];
			int target = item[1][0];
			System.out.println(Arrays.toString(twoSum02(nums, target)));
		}
	}

	public int[] twoSum02(int[] nums, int target) {
		for (int i = 0; i < nums.length - 1; i++) {
			int expact = target - nums[i];
			for (int j = i + 1; j < nums.length; j++) {
				if (expact == nums[j]) {
					return new int[]{i, j};
				}
			}
		}
		return null;
	}

	/**
	 * wrong!
	 * @param nums
	 * @param target
	 * @return
	 */
	public int[] twoSum01(int[] nums, int target) {
		int[] result = new int[2];
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			int num = nums[i];
			map.put(num, i);
		}
		for (int i = 0; i < nums.length; i++) {
			Integer index = map.get(target - nums[i]);
			if (index != null) {
				result[0]= i;
				result[1] = index;
				return result;
			}
		}
		return result;
	}
}
