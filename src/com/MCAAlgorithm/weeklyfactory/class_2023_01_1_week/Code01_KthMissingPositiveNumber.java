package com.MCAAlgorithm.weeklyfactory.class_2023_01_1_week;

// 给你一个 严格升序排列 的正整数数组 arr 和一个整数 k 。
// 请你找到这个数组里第 k 个缺失的正整数。
// 测试链接 : https://leetcode.cn/problems/kth-missing-positive-number/
public class Code01_KthMissingPositiveNumber {
	//数组50个数，中点位置是24，假设[24]=36，0-24总共25个数，从1到36里面缺少了36-25，11个数，
	// 所以缺失的哪个第20个数肯定在24位置的右侧，这样就逐渐二分来查找
	//[1,3,4,7,13,29,50],k=25，数组中点是7，缺少7 - 4 = 3，1到7缺少了3个数，1到29缺了23个，1到50缺少了43个数
	// 说明30是缺少的24个，31是缺少的25个
	public int findKthPositive(int[] arr, int k) {
		int l = 0;
		int r = arr.length - 1;
		int m = 0;
		int find = arr.length;
		while (l <= r) {//二分找断点
			m = (l + r) / 2;
			if (arr[m] - (m + 1) >= k) {
				find = m;
				r = m - 1;
			} else {
				l = m + 1;
			}
		}
		int preValue = find == 0 ? 0 : arr[find - 1];//距离最近的不够的哪个位置，上面例子就是29
		int under = preValue - find;//搞定了多少个
		return preValue + (k - under); //对应数字 29
	}
}
