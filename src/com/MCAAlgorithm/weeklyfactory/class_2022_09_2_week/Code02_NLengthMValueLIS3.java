package com.MCAAlgorithm.weeklyfactory.class_2022_09_2_week;

// 来自微众银行
// 给定一个数字n，代表数组的长度
// 给定一个数字m，代表数组每个位置都可以在1~m之间选择数字
// 所有长度为n的数组中，最长递增子序列长度为3的数组，叫做达标数组
// 返回达标数组的数量
// 1 <= n <= 500
// 1 <= m <= 10
// 500 * 10 * 10 * 10
// 结果对998244353取模
// 实现的时候没有取模的逻辑，因为非重点
public class Code02_NLengthMValueLIS3 {

	// 暴力方法
	// 为了验证
	public static int number1(int n, int m) {
		return process1(0, n, m, new int[n]);
	}

	public static int process1(int i, int n, int m, int[] path) {
		if (i == n) {
			return lengthOfLIS(path) == 3 ? 1 : 0;
		} else {
			int ans = 0;
			for (int cur = 1; cur <= m; cur++) {
				path[i] = cur;
				ans += process1(i + 1, n, m, path);
			}
			return ans;
		}
	}

	//最长递增子序列（顺序一样可以不连续），ends数组，以x为结尾最长递增子序列多长
	public static int lengthOfLIS(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int[] ends = new int[arr.length];
		ends[0] = arr[0];
		int right = 0;
		int max = 1;
		for (int i = 1; i < arr.length; i++) {
			int l = 0;
			int r = right;
			while (l <= r) {
				int m = (l + r) / 2;
				if (arr[i] > ends[m]) {
					l = m + 1;
				} else {
					r = m - 1;
				}
			}
			right = Math.max(right, l);
			ends[l] = arr[i];
			max = Math.max(max, l + 1);
		}
		return max;
	}

	// i : 当前来到的下标
	// f、s、t : ends数组中放置的数字！只有三位，不能超过3位，超过三位是无效的
	// ? == 0，没放！
	// n : 一共的长度！
	// m : 每一位，都可以在1~m中随意选择数字
	// 返回值：i..... 有几个合法的数组！
	public static int zuo(int i, int f, int s, int t, int n, int m) {
		if (i == n) {
			//来到终止位置n，保证ends数组有三个数，这个才到了长度位3的子序列，fst都不为0才合理，否则不合理
			return f != 0 && s != 0 && t != 0 ? 1 : 0;
		}
		// i < n
		int ans = 0;
		for (int cur = 1; cur <= m; cur++) { //题目给出在1到m种选择数字，并且合理串必须从1开始
			if (f == 0 || f >= cur) { //第一个数为0（空缺）或者第一个位置大于等于cur，来到f位置看后面能不能冲
				ans += zuo(i + 1, cur, s, t, n, m);
			} else if (s == 0 || s >= cur) { //s拦住了
				ans += zuo(i + 1, f, cur, t, n, m); //f不动，s变成cur后续递归
			} else if (t == 0 || t >= cur) {
				ans += zuo(i + 1, f, s, cur, n, m);
			}
			//else{}前面都拦不住了，超过了递增子序列的上限大于3个了，不要了
		}
		return ans;
	}

	// 正式方法
	// 需要看最长递增子序列！
	// 尤其是理解ends数组的意义！
	public static int number2(int n, int m) {
		int[][][][] dp = new int[n][m + 1][m + 1][m + 1];
		for (int i = 0; i < n; i++) {
			for (int f = 0; f <= m; f++) {
				for (int s = 0; s <= m; s++) {
					for (int t = 0; t <= m; t++) {
						dp[i][f][s][t] = -1;
					}
				}
			}
		}
		return process2(0, 0, 0, 0, n, m, dp);
	}

	public static int process2(int i, int f, int s, int t, int n, int m, int[][][][] dp) {
		if (i == n) {
			return f != 0 && s != 0 && t != 0 ? 1 : 0;
		}
		if (dp[i][f][s][t] != -1) {
			return dp[i][f][s][t];
		}
		int ans = 0;
		for (int cur = 1; cur <= m; cur++) {
			if (f == 0 || cur <= f) {
				ans += process2(i + 1, cur, s, t, n, m, dp);
			} else if (s == 0 || cur <= s) {
				ans += process2(i + 1, f, cur, t, n, m, dp);
			} else if (t == 0 || cur <= t) {
				ans += process2(i + 1, f, s, cur, n, m, dp);
			}
		}
		dp[i][f][s][t] = ans;
		return ans;
	}

	public static void main(String[] args) {
		System.out.println("功能测试开始");
		for (int n = 4; n <= 8; n++) {
			for (int m = 1; m <= 5; m++) {
				int ans1 = number1(n, m);
				int ans2 = number2(n, m);
				if (ans1 != ans2) {
					System.out.println(ans1);
					System.out.println(ans2);
					System.out.println("出错了!");
				}
			}
		}
		System.out.println("功能测试结束");
		System.out.println("性能测试开始");
		int n = 2000;
		int m = 20;
		System.out.println("序列长度 : " + n);
		System.out.println("数字范围 : " + m);
		long start = System.currentTimeMillis();
		number2(n, m);
		long end = System.currentTimeMillis();
		System.out.println("运行时间 : " + (end - start) + " 毫秒");
		System.out.println("性能测试结束");
	}

}
