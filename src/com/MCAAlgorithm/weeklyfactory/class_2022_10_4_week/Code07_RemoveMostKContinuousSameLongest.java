package com.MCAAlgorithm.weeklyfactory.class_2022_10_4_week;

import java.util.HashMap;
import java.util.LinkedList;

// 来自亚马逊
// 给定一个数组arr，和一个正数k
// 你可以随意删除arr中的数字，最多删除k个
// 目的是让连续出现一种数字的长度尽量长
// 返回这个尽量长的长度
// 比如数组arr = { 3, -2, 3, 3, 5, 6, 3, -2 }, k = 3
// 你可以删掉-2、5、6(最多3个)，这样数组arr = { 3, 3, 3, 3, -2 }
// 可以看到连续出现3的长度为4
// 这是所有删除方法里的最长结果，所以返回4
// 1 <= arr长度 <= 3 * 10^5
// -10^9 <= arr中的数值 <= 10^9
// 0 <= k <= 3 * 10^5
public class Code07_RemoveMostKContinuousSameLongest {

	// 暴力方法
	// 为了测试
	public static int longest1(int[] arr, int k) {
		return process1(arr, 0, new int[arr.length], 0, k);
	}

	public static int process1(int[] arr, int i, int[] path, int size, int k) {
		if (k < 0) {
			return 0;
		}
		if (i == arr.length) {
			if (size == 0) {
				return 0;
			}
			int ans = 0;
			int cnt = 1;
			for (int j = 1; j < size; j++) {
				if (path[j - 1] != path[j]) {
					ans = Math.max(ans, cnt);
					cnt = 1;
				} else {
					cnt++;
				}
			}
			ans = Math.max(ans, cnt);
			return ans;
		} else {
			path[size] = arr[i];
			int p1 = process1(arr, i + 1, path, size + 1, k);
			int p2 = process1(arr, i + 1, path, size, k - 1);
			return Math.max(p1, p2);
		}
	}

	// 正式方法，每一个数值给一个窗口都折腾自己的，如果不够用，改变次数超过k个，那么就把窗口的左边界扔掉
	// 时间复杂度O(N)
	public static int longest2(int[] arr, int k) {
		HashMap<Integer, LinkedList<Integer>> valueIndies = new HashMap<>();//linkedlist方便左边弹出
		int ans = 1;
		for (int i = 0; i < arr.length; i++) {//每个数遍历，然后以每个数建立窗口
			int value = arr[i];
			if (!valueIndies.containsKey(value)) {
				valueIndies.put(value, new LinkedList<>());//以每个数建立窗口
			}
			LinkedList<Integer> indies = valueIndies.get(value);
			//窗口不为空，且边界已经不够用了，把头部弹出，i=30，peek=24，30-24+1，此时30位置的值和24位置的值一样，
			// 说明从24-29有多少个数，再减去和【24】一样的数量*（即indies的大小）就是这部分要改变的大小，
			// 这个大小如果大于k，把首部弹出
			while (!indies.isEmpty() && i - indies.peekFirst() - indies.size() > k) {
				indies.pollFirst();
			}
			indies.addLast(i);
			ans = Math.max(ans, indies.size());//每步算出一个答案
		}
		return ans;
	}

	// 为了测试
	// 生成长度为n的数组
	// 值在[-v,v]之间等概率随机
	public static int[] randomArray(int n, int v) {
		int[] ans = new int[n];
		for (int i = 0; i < n; i++) {
			ans[i] = (int) (Math.random() * (2 * v + 1)) - v;
		}
		return ans;
	}

	// 为了测试
	public static void main(String[] args) {
		int N = 20;
		int V = 10;
		int K = 5;
		int testTime = 5000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int n = (int) (Math.random() * N) + 1;
			int[] arr = randomArray(n, V);
			int k = (int) (Math.random() * K);
			int ans1 = longest1(arr, k);
			int ans2 = longest2(arr, k);
			if (ans1 != ans2) {
				System.out.println("出错了!");
			}
		}
		System.out.println("测试结束");
	}

}
