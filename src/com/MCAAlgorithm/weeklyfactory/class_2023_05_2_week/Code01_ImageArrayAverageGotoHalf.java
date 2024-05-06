package com.MCAAlgorithm.weeklyfactory.class_2023_05_2_week;

import java.util.Arrays;

// 来自华为OD，学员问题
// 一个图像有n个像素点，存储在一个长度为n的数组arr里，
// 每个像素点的取值范围[0,s]的整数
// 请你给图像每个像素点值加上一个整数k（可以是负数）
// 像素值会自动截取到[0,s]范围，
// 当像素值<0，会更改为0，当新像素值>s，会更改为s
// 这样就可以得到新的arr，想让所有像素点的平均值最接近中位值s/2, 向下取整
// 请输出这个整数k, 如有多个整数k都满足, 输出小的那个
// 1 <= n <= 10^6
// 1 <= s <= 10^18
public class Code01_ImageArrayAverageGotoHalf {

	// 如果目标平均值是128，如果原始数组平均值是100，肯定在0到255选某个k；如果是200，那就在-255到0尝试；如果当前平均值等于目标值，那么还
	// 往左侧搜索，大过程，f(arr,k)，数组有了，k有了，求平均值
	// 暴力方法
	// 为了测试
	public static int best1(int[] arr, int s) {
		int half = s / 2;
		int average = -100000;
		int ans = -s;
		for (int k = -s; k <= s; k++) {
			int curAverage = average1(arr, k, s);
			if (Math.abs(half - curAverage) < Math.abs(half - average)) {
				average = curAverage;
				ans = k;
			}
		}
		return ans;
	}

	// 暴力方法
	// 为了测试
	// arr[100,   k = 200 , s，arr[i] +/-k >/< s处理结果
	//     s
	public static int average1(int[] arr, int k, int s) {
		int sum = 0;
		for (int num : arr) {
			int value = num + k;
			if (value < 0) {
				sum += 0;
			} else if (value > s) {
				sum += s;
			} else {
				sum += value;
			}
		}
		return sum / arr.length;
	}

	// 优化了一下，但不是最优解
	public static int best2(int[] arr, int s) {
		//从-s到s是二分的范围
		int l = -s;
		int r = s;
		int m = 0;
		int half = s / 2;//中点
		int average = -s;
		int ans = 0;
		while (l <= r) {
			m = (l + r) / 2;
			int curAverage = average1(arr, m, s);//取中点
			//让k+/- 上m看看符合不符合，更新条件两个：如果平均值差距更小，或者差距没有变小，但是m更小
			if ((Math.abs(half - curAverage) < Math.abs(half - average))
					|| ((Math.abs(half - curAverage) == Math.abs(half - average)) && m < ans)) {
				average = curAverage;
				ans = m;
			}
			if (curAverage >= half) {//
				r = m - 1;
			} else {
				l = m + 1;
			}
		}
		return ans;
	}

	// 正式方法
	// 最优解
	// O(N * logN) + O(logS *  logN)
	//
	//思路：【避免遍历，借助前缀和数组，求每部分的累加和】
	// 1、原始数组排序;[3,6,100,200,220], 0-255,如果k=-6，3和6加上k肯定都是0，如果k是负数，找到小于-k的部分，这部分变成0，
	// 剩下部分假设有n个，就是这部分大于-k的累加和+n*k 上面部分就是100 + 200 + 220 - 3 * 6；如果k=150，找到哪个范围加上k会超过255
	// 220，这部分累加和就是255 * 3，左侧部分就是3 + 6 + 100 + 3*150
	public static int best3(int[] arr, int s) {
		Arrays.sort(arr);
		int[] sum = new int[arr.length];
		sum[0] = arr[0];
		for (int i = 1; i < arr.length; i++) {
			sum[i] = sum[i - 1] + arr[i];
		}
		int l = -s;
		int r = s;
		int m = 0;
		int half = s / 2;
		int average = -s;
		int ans = 0;
		while (l <= r) {
			m = (l + r) / 2;
			int curAverage = average3(arr, sum, m, s); //求平局值
			if ((Math.abs(half - curAverage) < Math.abs(half - average))
					|| ((Math.abs(half - curAverage) == Math.abs(half - average)) && m < ans)) {
				average = curAverage;
				ans = m;
			}
			if (curAverage >= half) {
				r = m - 1;
			} else {
				l = m + 1;
			}
		}
		return ans;
	}

	//最终解法，原始数组排序，给出k的值，然后找到小于等于k的部分，左侧部分全变0
	// pre是前缀和，k是操作值
	public static int average3(int[] arr, int[] pre, int k, int s) {
		int n = arr.length;
		if (k < 0) {
			int l = bsZero(arr, k);
			int sum = rangeSum(pre, l + 1, n - 1);
			return (sum + (k * (n - l - 1))) / n;
		} else {
			int r = bsS(arr, k, s);
			int sum = rangeSum(pre, 0, r - 1);
			return (sum + (k * r) + (s * (n - r))) / n;
		}
	}

	//思路：二分找到小于0的部分
	public static int bsZero(int[] arr, int k) {
		int ans = -1;
		int l = 0;
		int r = arr.length - 1;
		int m;
		while (l <= r) {
			m = (l + r) / 2;
			if (arr[m] + k <= 0) {
				ans = m;
				l = m + 1;
			} else {
				r = m - 1;
			}
		}
		return ans;
	}

	//思路：二分找到大于s部分
	public static int bsS(int[] arr, int k, int s) {
		int ans = arr.length;
		int l = 0;
		int r = arr.length - 1;
		int m;
		while (l <= r) {
			m = (l + r) / 2;
			if (arr[m] + k >= s) {
				ans = m;
				r = m - 1;
			} else {
				l = m + 1;
			}
		}
		return ans;
	}

	public static int rangeSum(int[] pre, int l, int r) {
		if (l > r) {
			return 0;
		}
		return l == 0 ? pre[r] : (pre[r] - pre[l - 1]);
	}

	// 为了测试
	public static int[] randomArray(int n, int s) {
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = (int) (Math.random() * (s + 1));
		}
		return arr;
	}

	// 为了测试
	public static void main(String[] args) {
		int N = 50;
		int S = 500;
		int testTimes = 10000;
		System.out.println("测试开始");
		for (int i = 0; i < testTimes; i++) {
			int n = (int) (Math.random() * N) + 1;
			int s = (int) (Math.random() * S) + 1;
			int[] arr = randomArray(n, s);
			int ans1 = best1(arr, s);
			int ans2 = best2(arr, s);
			int ans3 = best3(arr, s);
			if (ans1 != ans2 || ans1 != ans3) {
				System.out.println("出错了!");
			}
		}
		System.out.println("测试结束");
	}

}
