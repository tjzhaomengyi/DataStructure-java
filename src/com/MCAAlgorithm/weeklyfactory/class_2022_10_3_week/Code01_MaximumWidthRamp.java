package com.MCAAlgorithm.weeklyfactory.class_2022_10_3_week;

import java.util.Stack;

// 给定一个整数数组 A，坡是元组 (i, j)，其中  i < j 且 A[i] <= A[j]
// 这样的坡的宽度为 j - i
// 找出 A 中的坡的最大宽度，如果不存在，返回 0
// 示例 1：
// 输入：[6,0,8,2,1,5]
// 输出：4
// 解释：
// 最大宽度的坡为 (i, j) = (1, 5): A[1] = 0 且 A[5] = 5
// 示例 2：
// 输入：[9,8,1,0,1,9,4,0,4,1]
// 输出：7
// 解释：
// 最大宽度的坡为 (i, j) = (2, 9): A[2] = 1 且 A[9] = 1
// 测试链接 : https://leetcode.cn/problems/maximum-width-ramp/
public class Code01_MaximumWidthRamp {
	// 【5 7 5 3 8 1。。。。】
	//1位置的如果能够和0位置形成坡，那么就不往栈里放，大于和相等都不进栈，只有递减了栈才存 （0-》5，3-》2，5—》1）
	// 栈的意义：某个位置往后撸可能构成最好坡的起始位置。这样撸完下来，开始从右往左遍历
	//	5 4 3
	// 17 18 19
	// 来到5位置的1，和19位置的3凑成一对求一个结果，ans=14，弹出5-》1；然后只要构成坡依次弹出，然后来到18位的4，依次搞栈中的东西，直到栈为空
	public static int maxWidthRamp(int[] arr) {
		int n = arr.length;
		// 栈中只放下标
		int[] stack = new int[n];//又来了数组形式栈，得了改成系统的吧省心
//		Stack<Integer> mystack = new Stack<>();
//		mystack.push()

		// 栈的大小
		int r = 0; //确定后续撸的【i】位置比栈顶元素还小，那么就把【i】放入栈中
		for (int i = 0; i < n; i++) {
			if (r == 0 || arr[stack[r - 1]] > arr[i]) {
				stack[r++] = i;
			}
		}
		int ans = 0;
		// 从右往左遍历
		// j = n - 1
		for (int j = n - 1; j >= 0; j--) {
			while (r != 0 && arr[stack[r - 1]] <= arr[j]) {
				int i = stack[--r];
				ans = Math.max(ans, j - i);
			}
		}
		return ans;
	}

}
