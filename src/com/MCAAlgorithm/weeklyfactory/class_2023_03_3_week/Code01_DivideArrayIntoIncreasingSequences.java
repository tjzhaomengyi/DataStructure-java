package com.MCAAlgorithm.weeklyfactory.class_2023_03_3_week;

// 给你一个 非递减 的正整数数组 nums 和整数 K
// 判断该数组是否可以被分成一个或几个 长度至少 为 K 的 不相交的递增子序列
// 测试链接 : https://leetcode.cn/problems/divide-array-into-increasing-sequences/
public class Code01_DivideArrayIntoIncreasingSequences {

	//例子： 1 5 5 7 9， 不相交就是几个子序列不用重复的元素，
	// 思路：如果有重复的数重复4次，找到出现最多的次数的数有4个，那么肯定是4组，最终n/4组大于等于k就肯定能分出来，鸡吧题
	public static boolean canDivideIntoSubsequences(int[] nums, int k) {
		int cnt = 1;
		int maxCnt = 1;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i - 1] != nums[i]) {
				maxCnt = Math.max(maxCnt, cnt);
				cnt = 1;
			} else {
				cnt++;
			}
		}
		maxCnt = Math.max(maxCnt, cnt);
		return nums.length / maxCnt >= k;
	}

}
