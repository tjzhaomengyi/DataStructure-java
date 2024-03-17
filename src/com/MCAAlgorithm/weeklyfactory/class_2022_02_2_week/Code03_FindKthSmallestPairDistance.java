package com.MCAAlgorithm.weeklyfactory.class_2022_02_2_week;

import java.util.Arrays;

// 测试链接 : https://leetcode.com/problems/find-k-th-smallest-pair-distance/
public class Code03_FindKthSmallestPairDistance {

//	public static int smallestDistancePair(int[] nums, int k) {
//		int n = nums.length;
//		Arrays.sort(nums);
//		int l = 0;
//		int r = nums[n - 1] - nums[0];
//		int ans = 0;
//		while (l <= r) {
//			int cnt = 0;
//			int m = l + ((r - l) >> 1);
//			for (int i = 0, j = 0; i < n; i++) {
//				while (j < n && nums[j] <= nums[i] + m) {
//					j++;
//				}
//				cnt += j - i - 1;
//			}
//			if (cnt >= k) {
//				ans = m;
//				r = m - 1;
//			} else {
//				l = m + 1;
//			}
//		}
//		return ans;
//	}

	//[2,.....,12]最大距离是10，整体的距离就是0到10，距离小于等于5的数字队的个数 ≤ 距离小于等于8的数字对的个数
	// 虽然可以把数字距离都列出来，但是其中某些可能不会出现，上面的个数对的差肯定都是小于等于
	// 0 1 2 3 4 5 6 7
	//小于等于0的有3对，小于等于1的有6对，小于等于2到小于等于4有10对【说明小于等于3和4的距离没有】，小于等于5有26个，突然从10激增到26，说明等于5的个数有16个
	public static int smallestDistancePair(int[] nums, int k) {
		int n = nums.length;
		Arrays.sort(nums);
		int l = 0;
		int r = nums[n - 1] - nums[0]; //距离范围从l到r
		int ans = 0;
		while (l <= r) {
			int dis = l + ((r - l) >> 1); //每次二分，看看有几个
			int cnt = f(nums, dis); //看看有几个
			if (cnt >= k) { //如果大于k个
				ans = dis; //先把结果存一下，一定要找到大于等于某个数等于10个的最左距离！！上面的例子【小于等于0的有3对，小于等于1的有6对，小于等于2到小于等于4有10对【说明小于等于3和4的距离没有】】
				r = dis - 1;
			} else { //如果小于k个
				l = dis + 1;
			}
		}
		return ans;
	}

	// <= dis的数字对，有几个，返回
	public static int f(int[] arr, int dis) {
		int cnt = 0;
		//不回退窗口
		for (int l = 0, r = 0; l < arr.length; l++) {
			while (r < arr.length && arr[r] <= arr[l] + dis) {
				r++;
			}
			cnt += r - l - 1;
		}
		return cnt;
	}

}
