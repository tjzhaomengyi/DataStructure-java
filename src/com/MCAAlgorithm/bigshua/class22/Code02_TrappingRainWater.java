package com.MCAAlgorithm.bigshua.class22;

import java.util.Scanner;

// 本题测试链接 : https://leetcode.com/problems/trapping-rain-water/
public class Code02_TrappingRainWater {

	public static int trap(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int N = arr.length;
		int L = 1;
		int leftMax = arr[0];
		int R = N - 2;
		int rightMax = arr[N - 1];
		int water = 0;
		while (L <= R) {
			if (leftMax <= rightMax) {
				water += Math.max(0, leftMax - arr[L]);
				leftMax = Math.max(leftMax, arr[L++]);
			} else {
				water += Math.max(0, rightMax - arr[R]);
				rightMax = Math.max(rightMax, arr[R--]);
			}
		}
		return water;
	}

	public static void main(String[] args) {
	  Scanner in = new Scanner(System.in);
		while (in.hasNextLine()) {
			String[] str = in.nextLine().split(" ");
			int[] nums = new int[str.length];
			for(int i = 0; i < nums.length; i++){
				nums[i] = Integer.valueOf(str[i]);
			}
			int ans = trap(nums);
			System.out.println(ans);

		}
	}

}
