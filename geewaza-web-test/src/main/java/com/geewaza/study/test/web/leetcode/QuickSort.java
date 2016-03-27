package com.geewaza.study.test.web.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by xiaoxing3 on 16/3/27.
 */
public class QuickSort {

	@Test
	public void test01() {
		int[][] tests = {
				{2,4,9,3,6,7,1,5},
				{1,2,3,4,5},
				{5,4,3,3,3,3,2,1},
		};

		for (int[] test : tests) {
			quickSort2(test, 0, test.length - 1);
			System.out.println(Arrays.toString(test));
		}
	}

	public void quickSort2(int[] nums, int start, int end) {
		if (start >= end) {
			return;
		}
		int i = start, j = end;
		int tmp = nums[i];
		while (i < j) {
			while (nums[j] >= tmp && i < j) {
				j--;
			}
			nums[i] = nums[j];
			while (nums[i] <= tmp && i < j) {
				i++;
			}
			nums[j] = nums[i];
		}
		nums[i] = tmp;
		quickSort2(nums, start, i-1);
		quickSort2(nums, i + 1, end);
	}

	public void quickSort1(int[] nums, int start, int end) {

		int i = start;
		int j = end;
		int tmp = nums[start + (end - start) / 2];
		while (i <= j) {
			while (nums[i] < tmp) {
				i++;
			}

			while (nums[j] > tmp) {
				j--;
			}
			if (i <= j) {
				exchange(nums, i, j);
				i++;
				j--;
			}
		}
		if (start < j) {
			quickSort1(nums, start, j);
		}
		if (i < end) {
			quickSort1(nums, i, end);
		}

	}

	public void exchange(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
}
