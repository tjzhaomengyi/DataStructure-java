package com.MCAAlgorithm.weeklyfactory.class_2023_04_4_week;

// 来自微众银行
// 两个魔法卷轴问题
// 给定一个数组arr，其中可能有正、负、0，
// 一个魔法卷轴可以把arr中连续的一段全变成0，你希望数组整体的累加和尽可能大
// 你有两个魔法卷轴，请返回数组尽可能大的累加和
// 1 <= arr长度 <= 100000
// -100000 <= arr里的值 <= 100000
public class Code04_MagicScrollProbelm {

	// 暴力方法
	// 为了测试
	public static int maxSum1(int[] arr) {
		int p1 = 0;
		for (int num : arr) {
			p1 += num;
		}
		int n = arr.length;
		int p2 = mustOneScroll(arr, 0, n - 1);
		int p3 = Integer.MIN_VALUE;
		for (int i = 1; i < n; i++) {
			p3 = Math.max(p3, mustOneScroll(arr, 0, i - 1) + mustOneScroll(arr, i, n - 1));
		}
		return Math.max(p1, Math.max(p2, p3));
	}

	// 为了测试
	public static int mustOneScroll(int[] arr, int L, int R) {
		int ans = Integer.MIN_VALUE;
		for (int a = L; a <= R; a++) {
			for (int b = a; b <= R; b++) {
				int curAns = 0;
				for (int i = L; i < a; i++) {
					curAns += arr[i];
				}
				for (int i = b + 1; i <= R; i++) {
					curAns += arr[i];
				}
				ans = Math.max(ans, curAns);
			}
		}
		return ans;
	}

	//思路：1、不用魔法卷轴；2、使用1次魔法卷轴；3、使用两次魔法卷轴
	// 2、使用1次魔法卷轴，0-1，0-2...0-n用魔法卷轴,最大累加和是多少，dp[0]到dp[n-1]
	//  （1）就不灭i位置的数，dp[i-1] + arr[i] （2）灭掉i位置，max（0,dp[0]...dp[i-1]),剩下部分全灭掉
	// 3、用两个卷轴 dp'[i],i到n-1，将数组分割，0-0 ｜ 1-7 ，0-1｜2-7
	// 正式方法
	// 时间复杂度O(N)
	public static int maxSum2(int[] arr) {
		if (arr.length == 0) {
			return 0;
		}
		// 一个卷轴也不用
		int p1 = 0;
		for (int num : arr) {
			p1 += num;
		}
		int n = arr.length;
		// left[i] : 0 ~ i范围上，一定要用一次卷轴的情况下，最大累加和多少
		int[] left = new int[n];
		// left[0] = 0 : 0 ~ 0，一定要用一次卷轴的情况下，最大累加和多少
		// 每一步的前缀和
		// 0~0 前缀和
		int sum = arr[0];
		// 之前所有前缀和的，最大值
		int maxSum = Math.max(0, sum);
		for (int i = 1; i < n; i++) {
			// left[i - 1] + arr[i]
			// maxSum : 之前所有前缀和的，最大值
			left[i] = Math.max(left[i - 1] + arr[i], maxSum);//从左往右正好推过去，dp[i]
			sum += arr[i];
			maxSum = Math.max(maxSum, sum);
		}
		// 只用一次卷轴，必须用，0~n-1范围上的解，第二种可能性
		int p2 = left[n - 1];

		// 第三种 ：一定要用两次卷轴
		int[] right = new int[n];
		// right[i] : i ~ n-1范围上，一定要用一次卷轴的情况下，最大累加和多少
		sum = arr[n - 1]; //从右往左
		maxSum = Math.max(0, sum);
		for (int i = n - 2; i >= 0; i--) { //后缀和
			right[i] = Math.max(arr[i] + right[i + 1], maxSum);
			sum += arr[i];
			maxSum = Math.max(maxSum, sum);
		}
		int p3 = Integer.MIN_VALUE;
		for (int i = 1; i < n; i++) {//使用两次卷轴每次执行划分可能性比较
			// 0..0 1...n-1
			// 0..1 2...n-1
			// 0..2 3...n-1
			p3 = Math.max(p3, left[i - 1] + right[i]);
		}
		return Math.max(p1, Math.max(p2, p3));
	}

	// 为了测试
	public static int[] randomArray(int n, int v) {
		int[] ans = new int[n];
		for (int i = 0; i < n; i++) {
			ans[i] = (int) (Math.random() * (v * 2 + 1)) - v;
		}
		return ans;
	}

	// 为了测试
	public static void main(String[] args) {
		int N = 50;
		int V = 100;
		int testTimes = 10000;
		System.out.println("测试开始");
		for (int i = 0; i < testTimes; i++) {
			int n = (int) (Math.random() * N);
			int[] arr = randomArray(n, V);
			int ans1 = maxSum1(arr);
			int ans2 = maxSum2(arr);
			if (ans1 != ans2) {
				System.out.println("出错了!");
			}
		}
		System.out.println("测试结束");
	}

}
