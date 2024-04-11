package com.MCAAlgorithm.weeklyfactory.class_2022_09_2_week;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

// 来自百度
// 二狗买了一些小兵玩具，和大胖一起玩
// 一共有n个小兵，这n个小兵拍成一列
// 第i个小兵战斗力为hi，然后他们两个开始对小兵进行排列
// 一共进行m次操作，二狗每次操作选择一个数k，将前k个小兵战斗力从小到大排列
// 大胖每次操作选择一个数k，将前k个小兵战斗力从大到小排列
// 问所有操作结束后，排列顺序什么样
// 给定一个长度为n的数组arr，表示每个小兵的战斗力
// 给定一个长度为m的数组op, 
// op[i] = { k , 0 }, 表示对前k个士兵执行从小到大的操作
// op[i] = { k , 1 }, 表示对前k个士兵执行从大到小的操作
// 返回数组ans，表示最终的排列
// 1 <= n, m <= 2 * 10^5
// - 10^9 <= arr[i] <= + 10^9
public class Code01_SortGame {

	// 暴力方法
	// 为了验证
	public static int[] game1(int[] arr, int[][] op) {
		int n = arr.length;
		Integer[] help = new Integer[n];
		for (int i = 0; i < n; i++) {
			help[i] = arr[i];
		}
		for (int[] o : op) {
			if (o[1] == 0) {
				Arrays.sort(help, 0, o[0], (a, b) -> a - b);
			} else {
				Arrays.sort(help, 0, o[0], (a, b) -> b - a);
			}
		}
		int[] ans = new int[n];
		for (int i = 0; i < n; i++) {
			ans[i] = help[i];
		}
		return ans;
	}

	// 正式方法 ： 单调栈 + 有序表
	// 时间复杂度O(M) + O(N*logN)
	// op表示操作[0]表示要排序的长度 [1]表示从小到大，或者从大到小
	// 小根堆，把[0]小的放在上面，按照顺序从前撸到最后，如果进来的不合理就弹出栈中的，让栈合理。
	// 准备一个有序表，让他支持能够加入重复数字，每次每次把间隔的范围不动填回结果即可
	public static int[] game2(int[] arr, int[][] op) {
		int n = arr.length;
		int m = op.length;
		int[] stack = new int[m]; //单调栈，他又自己折腾数组，太烦了
		int r = 0;
		for (int i = 0; i < m; i++) {
			while (r != 0 && op[stack[r - 1]][0] <= op[i][0]) { //不满足要求的，从栈中删除
				r--;
			}
			stack[r++] = i; //加入操作的下标，具体操作可以通过下标反查操作
		}
		int[] ans = new int[n];
		int ansi = n - 1;
		int l = 0;
		for (; ansi >= op[stack[l]][0]; ansi--) { //没有操作的数字，倒着填进去
			ans[ansi] = arr[ansi];
		}
		TreeSet<Number> sorted = new TreeSet<>(new NumberComparator());
		for (int i = 0; i < op[stack[l]][0]; i++) { //操作的数放入有序表中
			sorted.add(new Number(arr[i], i));//这里直接在sorted里面排序好
		}
		while (l != r) { //如果栈不为空
			// 当前处理的指令
			int[] cur = op[stack[l++]];//从栈底部往上取操作
			if (l != r) {
				int[] next = op[stack[l]]; //cur上面的那个数拿出来
				int num = cur[0] - next[0];
				//在结果里从后往前填写
				if (cur[1] == 0) { //如果是从小到大
					for (int i = 0; i < num; i++) {
						ans[ansi--] = sorted.pollLast().value; //
					}
				} else {
					for (int i = 0; i < num; i++) {
						ans[ansi--] = sorted.pollFirst().value;
					}
				}
			} else { //最后的福根，根据最后一个操作填写好
				if (cur[1] == 0) {
					while (!sorted.isEmpty()) {
						ans[ansi--] = sorted.pollLast().value;
					}
				} else {
					while (!sorted.isEmpty()) {
						ans[ansi--] = sorted.pollFirst().value;
					}
				}
			}
		}
		return ans;
	}

	//裸的有序表TreeSet不支持重复的数字
	public static class Number {
		public int value;
		public int index;

		public Number(int v, int i) {
			value = v;
			index = i;
		}
	}

	public static class NumberComparator implements Comparator<Number> {

		@Override
		public int compare(Number o1, Number o2) {
			if (o1.value != o2.value) {
				return o1.value - o2.value;
			} else {
				return o1.index - o2.index;
			}
		}

	}

	// 为了测试
	public static int[] randomArray(int n, int v) {
		int[] ans = new int[n];
		for (int i = 0; i < n; i++) {
			ans[i] = (int) (Math.random() * v) + 1;
		}
		return ans;
	}

	// 为了测试
	public static int[][] randomOp(int m, int n) {
		int[][] ans = new int[m][2];
		for (int i = 0; i < m; i++) {
			ans[i][0] = (int) (Math.random() * (n + 1));
			ans[i][1] = Math.random() < 0.5 ? 0 : 1;
		}
		return ans;
	}

	// 为了测试
	public static boolean isEqual(int[] arr1, int[] arr2) {
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}

	// 为了测试
	public static void main(String[] args) {

//		int[] arr = { 3, 3, 7, 7, 7 };
//
//		TreeSet<Number> set = new TreeSet<>(new NumberComparator());
//
//		for (int i = 0; i < arr.length; i++) {
//			set.add(new Number(arr[i], i));
//		}
//		
////		System.out.println(set.size());
//		
//		while(!set.isEmpty()) {
//			System.out.println(set.pollLast().value);
//		}

		int N = 100;
		int M = 100;
		int V = 200;
		int testTimes = 5000;
		System.out.println("测试开始");
		for (int i = 0; i < testTimes; i++) {
			int n = (int) (Math.random() * N) + 1;
			int m = (int) (Math.random() * M) + 1;
			int[] arr = randomArray(n, V);
			int[][] op = randomOp(m, n);
			int[] ans1 = game1(arr, op);
			int[] ans2 = game2(arr, op);
			if (!isEqual(ans1, ans2)) {
				System.out.println("出错了!");
			}
		}
		System.out.println("测试结束");
	}

}
