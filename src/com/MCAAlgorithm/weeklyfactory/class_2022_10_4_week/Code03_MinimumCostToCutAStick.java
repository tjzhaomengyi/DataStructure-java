package com.MCAAlgorithm.weeklyfactory.class_2022_10_4_week;

import java.util.Arrays;

// 有一根长度为 n 个单位的木棍，棍上从 0 到 n 标记了若干位置
// 给你一个整数数组 cuts ，其中 cuts[i] 表示你需要将棍子切开的位置
// 你可以按顺序完成切割，也可以根据需要更改切割的顺序
// 每次切割的成本都是当前要切割的棍子的长度，切棍子的总成本是历次切割成本的总和
// 对棍子进行切割将会把一根木棍分成两根较小的木棍
// 这两根木棍的长度和就是切割前木棍的长度
// 返回切棍子的最小总成本
// 测试链接 : https://leetcode.cn/problems/minimum-cost-to-cut-a-stick/
public class Code03_MinimumCostToCutAStick {

	//n=6，【1，3，5】 6 + 5 + 3 = 14的代价，如果改变顺序【3，1，5】，6 + 3 + 3 = 12
	// 思路：类似打气球问题，这个是枚举在哪个位置来切
	public static int zuo(int[] cuts, int n) {
		return f(cuts, 0, cuts.length - 1);
	}

	// cuts : 3 9 13 19 21
	//        0 1  2  3  4
	// 0,1       3,4
	// cuts[l....r]
	// 0...4 都切完！最小花费是多少
	// 1) 第一刀切在cuts[0]  3
	// 2) 第一刀切在cuts[1]  9
	// 3) 第一刀切在cuts[2]  9
	//思路：如果剩最后一个点成本是多少，是它左边的刀位置和它右边刀位置的差，例子【1，15，17，23，29】，如果让15、17、23这哥三确定最后的长度是多少
	// 它们这三个要割的整个长度是29-1 = 28
	// 根据这个思路，如果n=20我们在数组中添加0和20，两个位置【0，1，5，9，20】
	public static int f(int[] cuts, int l, int r) {
		return 0;
	}

	public static int minCost(int n, int[] cuts) {
		int m = cuts.length;
		Arrays.sort(cuts);
		int[] arr = new int[m + 2];
		arr[0] = 0;  //补开头
		for (int i = 1; i <= m; ++i) {
			arr[i] = cuts[i - 1];
		}
		arr[m + 1] = n;//补结尾
		int[][] dp = new int[m + 2][m + 2];
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= m; j++) {
				dp[i][j] = -1;
			}
		}
		return process(arr, 1, m, dp);
	}

	public static int process(int[] arr, int l, int r, int[][] dp) {
		if (l > r) {
			return 0;
		}
		if (l == r) {
			return arr[r + 1] - arr[l - 1];
		}
		if (dp[l][r] != -1) {
			return dp[l][r];
		}
		int ans = Integer.MAX_VALUE;
		for (int k = l; k <= r; k++) { //枚举第一刀的位置
			//    左  |   右 的成本
			ans = Math.min(ans, process(arr, l, k - 1, dp) + process(arr, k + 1, r, dp));
		}
		ans += arr[r + 1] - arr[l - 1]; // 加上自己的成本
		dp[l][r] = ans; //挂缓存
		return ans;
	}
}
