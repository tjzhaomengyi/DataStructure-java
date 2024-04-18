package com.MCAAlgorithm.weeklyfactory.class_2022_11_4_week;

import java.util.Arrays;
import java.util.HashSet;

// 给定你一个整数数组 nums
// 我们要将 nums 数组中的每个元素移动到 A 集合 或者 B 集合中
// 使得 A 集合和 B 集合不为空，并且 average(A) == average(B)
// 如果可以完成则返回true，否则返回false。
// 注意：对于数组 arr,  average(arr) 是 arr 的所有元素的和除以 arr 长度。
// 测试链接 : https://leetcode.cn/problems/split-array-with-same-average/
public class Code04_SplitArrayWithSameAverage {
//  思路：分治的题，把数组分成两份，部分sum累加和/个数是否和另一部分一样不一样，这题挺难
	// 左边最多15个数，右边最多15个数，2^15,右边2^15
	// 数学结论：如果从整个数组的左半部分再拿出s1，从右半部分数组再拿出s2有如下关系
	//  整个数组累加和是sum，长度为N，左边的累加和s1，右边的累加和s2，左边数字个数t1，右边数字个数t2，推出（s1+s2）/（t1+t2）= sum/N
	//  【终极结论：如果左右两个部分合起来满足题目要求，那么剩下部分也肯定满足要求，这个结论太他妈讨巧了，但是这就是这道题分治的本质】
//  那么这个s1和s2的兄弟集合，即左右部分剩下的组成的集合一定也能满足上式关系！！！
	// 通过公式推导出一个关系 N * s1 - sum * t1 = -N * s2 + sum * t2，左右两侧肯定有这个指标，这样肯定让两组的平均数一样大 
	/**
	public static int n;
	public static int sum;

	public static boolean can(int[] arr) {
		n = arr.length;
		if (n == 1) {
			return false;
		}
		sum = 0;
		for (int num : arr) {
			sum += num;
		}
		//把数组分成两半，选某个数组成结果，或者不选某个数组成结果，两侧暴力有2^15代价，3万
		//左侧
		int leftSize = n / 2;
		int[] left = new int[leftSize];
		for (int i = 0; i < leftSize; i++) {
			left[i] = arr[i];
		}

		//右侧
		int rightSize = n - leftSize;
		int[] right = new int[leftSize];
		for (int i = 0, j = leftSize; j < n; i++, j++) {
			right[i] = arr[j];
		}

		//left ： t1 s1， right：t2 s2；满足这个公式N * s1 - sum * t1 = -N * s2 + sum * t2
		HashSet<Integer> ans1 = new HashSet<>();//左侧的指标
		//left左侧，来到0位，数字选了0个，当前sum=0，ans1就是指标集合
		collect(left, 0, 0, 0, ans1); //左侧所有的指标
		HashSet<Integer> ans2 = new HashSet<>(); //右侧的指标
		collect(right, 0, 0, 0, ans2); // 右侧所有指标



		 // ans1 所有的指标 一共有 2 ^ 15  -> 3万
		 // ans2  所有的指标 一共有 2 ^ 15


		//枚举ans1里面的所有指标
		for(int x : ans1) { // 2 ^ 15
			// ans2 -x  O(1) 去ans2去查是否有x的相反数
			if(ans2.contains(-x)){
				return true;
			}

		}
		//这里要加上左侧不选择，右侧只能



		// left : T1 S1
		// right: T2 S2 不能出现T1+T2=0，也不能出现T1+T2=N，左方案不能收集所有数都不拿的方案，右方案不能所有数都拿，等价于规避掉上面两种情况
		// S1 + S2 / T1 + T2 == Sum / N
		// N * S1 - Sum * T1 == - (N * S2 - Sum * T2)
		return false;

	}

	// left[i.....]  X Y
	// t选了几个数, s这些数的和
	// ans
	public static void collect(int[] part, 
			int i, int t, int s, HashSet<Integer> ans) {
		if(i == part.length) { //如果遍历完了，往ans结果数组里面增加指标
			ans.add(n * s - sum * t); //指标就是上面给的公式
		}else {
			collect(part, i+1, t, s, ans); //如果没有遇到结尾执行暴力展开，当前的数不要，走一个分支
			collect(part, i+1, t+1, s + part[i], ans);//当前的数要，走一个分支
		}
	}
	 **/

//	// 左，

	public static int n;
	public static int s;
	public static int l;
	public static int r;
	public static int[] lvalues = new int[1 << 15]; //指标
	// 3000 l = 3000
	// 0...2999
	public static int[] rvalues = new int[1 << 15]; //指标

	public static boolean splitArraySameAverage(int[] nums) {
		n = nums.length;
		if (n == 1) {
			return false;
		}
		s = 0;
		for (int num : nums) {
			s += num;
		}
		l = 0;
		r = 0;
		int[] larr = new int[n / 2];
		int[] rarr = new int[n - larr.length];
		for (int i = 0; i < larr.length; i++) {
			larr[i] = nums[i];
		}
		for (int i = larr.length, j = 0; i < nums.length; i++, j++) {
			rarr[j] = nums[i];
		}
		//利用结论：左侧不能一个都不要，右侧不能全都要，就这里不好想，如果左侧全选，右侧不选 肯定和左侧全不选，右侧全选对称
		// 左侧 : 收集指标的时候，不能一个数也没有
		collect(larr, true);
		// 右侧 : 收集指标的时候，不能所有数都用
		collect(rarr, false);
		Arrays.sort(rvalues, 0, r);
		for (int i = 0; i < l; i++) {
			// 左侧x  -x
			if (contains(-lvalues[i])) { //排序后用二分
				return true;
			}
		}
		return false;
	}

	public static void collect(int[] arr, boolean isLeft) {
		process(arr, 0, 0, 0, isLeft);
	}

	public static void process(int[] arr, int index, int sum, int num, boolean isLeft) {
		if (index == arr.length) {//注意：在终止的时候
			if (isLeft && num > 0) { //选择的数量一定要大于0
				lvalues[l++] = s * num - n * sum;
			}
			if (!isLeft && num != arr.length) { //选择的长度不能全选
				rvalues[r++] = s * num - n * sum;
			}
		} else {
			//两个展开
			process(arr, index + 1, sum, num, isLeft);
			process(arr, index + 1, sum + arr[index], num + 1, isLeft);
		}
	}

	public static boolean contains(int num) {
		for (int left = 0, right = r - 1, mid = 0; left <= right;) {
			mid = (left + right) / 2;
			if (rvalues[mid] == num) {
				return true;
			} else if (rvalues[mid] < num) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return false;
	}

}
