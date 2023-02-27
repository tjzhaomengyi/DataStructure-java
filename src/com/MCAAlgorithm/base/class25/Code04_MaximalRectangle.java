package com.MCAAlgorithm.base.class25;

import java.util.Stack;

// 测试链接：https://leetcode.com/problems/maximal-rectangle/
public class Code04_MaximalRectangle {
	//数学结论：在单调栈中的数的意义就是，每次单调栈数只要一出栈就表示：当前要入栈的值是比当前栈顶元素小的最右的元素，此时该栈顶的下一个栈中元素是左侧比该栈顶元素小的
	public static int maximalRectangle(char[][] map) {
		if (map == null || map.length == 0 || map[0].length == 0) {
			return 0;
		}
		int maxArea = 0;
		int[] height = new int[map[0].length];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				height[j] = map[i][j] == '0' ? 0 : height[j] + 1;
			}
			maxArea = Math.max(maxRecFromBottom(height), maxArea);
		}
		return maxArea;
	}

	// height是正方图数组
	public static int maxRecFromBottom(int[] height) {
		if (height == null || height.length == 0) {
			return 0;
		}
		int maxArea = 0;
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < height.length; i++) {
			while (!stack.isEmpty() && height[i] <= height[stack.peek()]) {
				int j = stack.pop();
				int k = stack.isEmpty() ? -1 : stack.peek();
				// k j i || height[k] < height[j] > height[i],所以不要，
				// 扣边界：因为stack里面是单调栈，只要新进来的小于当前stack的栈顶，
				//  那么就满足 height[stack.次顶] < heights[stack.peek()] < heights[i],
				//  但是stack.peek()位置到i中间可能还有好几个高度
				//数学结论：在单调栈中的数的意义就是，
				// 每次单调栈数只要一出栈就表示：当前要入栈的值是比当前栈顶元素小的最右的元素，
				// 此时该栈顶的下一个栈中元素是左侧比该栈顶元素小的
				// 结算当前栈顶
				int curArea = (i - k - 1) * height[j];//结算当前栈顶
				maxArea = Math.max(maxArea, curArea);
			}
			stack.push(i);
		}
		while (!stack.isEmpty()) {
			int j = stack.pop();
			int k = stack.isEmpty() ? -1 : stack.peek();
			int curArea = (height.length - k - 1) * height[j];
			maxArea = Math.max(maxArea, curArea);
		}
		return maxArea;
	}

}
