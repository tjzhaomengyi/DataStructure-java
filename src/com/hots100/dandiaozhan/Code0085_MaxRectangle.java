package com.hots100.dandiaozhan;

import java.util.Stack;

/**
 * @Author: zhaomengyi
 * @Date: 2022-12-09 12:23 下午
 * @Description:
 */
public class Code0085_MaxRectangle {
  //技巧：单调栈 + 动态规划：经典的单调栈问题 【也算是儿童益智】
  // 从上到下 以每行的数字作为地基，计算每个地基和他上面的高度。遇到0就清0
  // 1 1 1 1 1   压缩数组[1,1,1,1,1]
  // 1 0 1 1 1          [2,0,2,2,2]
  // 0 1 0 1 1          [0,1,1,3,3]

  //数学结论：在单调栈中的数的意义就是，每次单调栈数只要一出栈就表示：当前要入栈的值是比当前栈顶元素小的最右的元素，此时该栈顶的下一个栈中元素是左侧比该栈顶元素小的
  public int maximalRectangle(char[][] matrix) {
    if(matrix.length == 0 || matrix == null || matrix[0].length == 0 || matrix[0] == null){
      return 0;
    }
    int maxArea = 0;
    int[] height = new int[matrix[0].length];//记录以每层为地基的最大高度
    for(int i = 0; i < matrix.length; i++){
      for(int j = 0; j < matrix[0].length; j++){
        height[j] = matrix[i][j] == '0' ? 0 : height[j] + 1;//技巧：如何这个地基点一旦是0，那么整个就要清空了
      }
      maxArea = Math.max(maxRecFromBottom(height), maxArea);
    }
    return maxArea;
  }

  public int maxRecFromBottom(int[] height){
    if(height == null || height.length == 0){
      return 0;
    }
    int maxArea = 0;
    Stack<Integer> stack = new Stack<>();
    for(int i = 0; i < height.length; i++){
      //只要stack不为空，并且height[i]和当前栈顶元素比较小的时候就要做弹出处理
      while(!stack.isEmpty() && height[i] <= height[stack.peek()]){
        int j = stack.pop();//栈顶
        int k = stack.isEmpty() ? -1 : stack.peek();
        //求一次j栈顶最大的面积
        // k j i || height[k] < height[j] > height[i],不要i位置和k位置的高度
        int curArea = (i - k - 1) * height[j];
        maxArea = Math.max(maxArea, curArea);
      }
      stack.push(i);
    }
    while(!stack.isEmpty()){
      int j = stack.pop();
      int k = stack.isEmpty() ? -1 : stack.peek();
      int curArea = (height.length - k - 1) * height[j];
      maxArea = Math.max(maxArea, curArea);
    }
    return maxArea;
  }
}
