package com.hots100.dandiaozhan;

import java.util.Stack;

/**
 * @Author: zhaomengyi
 * @Date: 2022-12-15 7:10 下午
 * @Description: 参考Code04_MaximalRectangle的解释【单调栈的：扣边界 + 数学结论】
 */
public class Code0084_MaxRecInBars {

  public int largestRectangleArea(int[] heights) {
    if (heights == null || heights.length == 0) return 0;
    int maxArea = 0;
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < heights.length; i++) {
      while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {//技巧：单调栈只要使用等于进行跳出，就表示后续跳出的计算结果可以把前面错误弥补
        int j = stack.pop();
        int k = stack.isEmpty() ? -1 : stack.peek();
        int curArea = (i - k - 1) * heights[j];
        maxArea = Math.max(curArea, maxArea);
      }
      stack.push(i);
    }
    while (!stack.isEmpty()) {
      int j = stack.pop();
      int k = stack.isEmpty() ? -1 : stack.peek();
      int curArea = (heights.length - k - 1) * heights[j];
      maxArea = Math.max(curArea, maxArea);
    }
    return maxArea;
  }

}
