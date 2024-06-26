package com.MCAAlgorithm.weeklyfactory.class_2022_11_4_week;

import java.util.Arrays;

// 给你两个正整数数组 nums 和 target ，两个数组长度相等。
// 在一次操作中，你可以选择两个 不同 的下标 i 和 j ，
// 其中 0 <= i, j < nums.length ，并且：这个两个位置只能+2和-2
// 令 nums[i] = nums[i] + 2 且
// 令 nums[j] = nums[j] - 2 。
// 如果两个数组中每个元素出现的频率相等，我们称两个数组是 相似 的。（顺序可以不一样）
// 请你返回将 nums 变得与 target 相似的最少操作次数。
// 测试数据保证 nums 一定能变得与 target 相似。
// 测试链接 : https://leetcode.cn/problems/minimum-number-of-operations-to-make-arrays-similar/
public class Code03_MinOperationsMakeSimilar {

	//思路：（1）假设nums中奇数有10个，偶数有6个，那么targets奇数和偶数个数和nums一样，否则变不成（因为只能+2或者-2）
	// （2）把nums里面的奇数和偶数从小到大排序，target也这么做 ，每次两个数所有差值，然后/4就是操作的次数，一个操作可以改变4的差值【直接用这个结论就行了】
	public static long makeSimilar(int[] nums, int[] target) {
		int n = nums.length;
		int oddSize = split(nums); //奇数大小
		split(target);
		Arrays.sort(nums, 0, oddSize);
		Arrays.sort(nums, oddSize, n);
		Arrays.sort(target, 0, oddSize);
		Arrays.sort(target, oddSize, n);
		long ans = 0;
		for (int i = 0; i < n; i++) {
			ans += Math.abs((long) nums[i] - target[i]);
		}
		return ans >> 2; //向右移动两位除以4，就是操作数
	}

	public static int split(int[] arr) {
		int line = 0;
		for (int i = 0; i < arr.length; i++) {
			if ((arr[i] & 1) != 0) {
				swap(arr, i, line++);
			}
		}
		return line;
	}

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

}
