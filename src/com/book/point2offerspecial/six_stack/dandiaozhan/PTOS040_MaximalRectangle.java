package com.book.point2offerspecial.six_stack.dandiaozhan;

import java.util.Stack;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-07 16:50
 * @Description:
 */
public class PTOS040_MaximalRectangle {
  public int maximalRectangle(String[] matrix) {
    if(matrix.length == 0 || matrix[0].length() == 0 || matrix == null) return 0;
    int[] heights = new int[matrix[0].length()];
    int ans = 0;
    for(String row : matrix){//技巧：【以行为单位】从上面的行开始，一行一行这样做底部，一旦有0表示断开
      char[] row_chars = row.toCharArray();
      for(int i = 0; i < row_chars.length; i++){//技巧：【以列为单位】一列一列算高度，一旦有0表示当前高度断开了
        if(row_chars[i] == '0'){
          heights[i] = 0;
        } else {
          heights[i]++;
        }
      }
      int cur_area = largestRectangleArea(heights);
      ans = Math.max(ans, cur_area);
    }
    return ans;
  }

  public int largestRectangleArea(int[] heights) {
    if(heights == null) return 0;
    Stack<Integer> stack = new Stack<>();
    int ans = 0;
    for(int i = 0; i < heights.length; i++){
      //技巧：使用单调栈计算当前heights[i]之前最大的面积
      //扣边界：如果heights[i]小于等于当前的height[stack.peek]，那么这个stack.peek的高度就可以计算了，
      // 这个时候有一个重要的结论：首先使用while进行弹栈操作，stack.peek 到 i位置中间可能有好多数可以计算但是都从栈中弹出，
      // 并且stack的次顶位置（即stack.pop;heights[stack.peek()] 这个元素肯定比 当前前未执行弹出操作的栈顶的值要小。
      // 根据这个结论我们就可以计算当前height[stack.pop()]的大小了
      while(!stack.isEmpty() && heights[i] <= heights[stack.peek()]){
        int j = stack.pop();//待计算的位置，一会用它取高度
        int k = stack.isEmpty() ? -1 :stack.peek(); //上面解释中的次顶位,扣边界别弹没了
        ans = Math.max((i - k - 1) * heights[j] ,ans);//i位置不能算进去
      }//到这个时候比新进来i都高的已经全都出去了，stack还是一个递增的单调栈
      stack.push(i);
    }
    //最后做清算,计算stack中的最大值即可
    while(!stack.isEmpty()){
      int j = stack.pop();//每次弹出清算
      int k = stack.isEmpty() ? -1 : stack.peek();
      int curArea = (heights.length - k - 1) * heights[j];//扣边界：此时，高度已经全部进stack了，弹栈的位置j右侧的高度全比height[j]小
      ans = Math.max(curArea ,ans);
    }
    return ans;
  }
}
