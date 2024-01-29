package com.book.point2offer;

import java.util.Stack;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-07 9:54
 * @Description:popped的顺序是否可以按照pushed的顺序弹出,
 * @Solve:注意下标的巧妙用法
 */
public class ValidStackSequence_31 {
  public boolean validateStackSequences(int[] pushed, int[] popped) {
    Stack tmp = new Stack();
    if(pushed==null) return false;
    int n = pushed.length;
    int cnt=0;
    for(int i=0;i<n;i++){
      tmp.push(pushed[i]);
        while(!tmp.isEmpty() && tmp.peek().equals(popped[cnt])){
          tmp.pop();
          cnt++;
        }
    }
    return cnt==n;
  }

  public static void main(String[] args) {
    new ValidStackSequence_31().validateStackSequences(new int[]{1,2,3,4,5,},new int[]{4,5,3,2,1});
  }
}
