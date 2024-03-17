package com.book.point2offerspecial.six_stack;

import java.util.Stack;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-07 14:05
 * @Description:
 */
public class PTOS038_DailyTemperatures {
  public int[] dailyTemperatures(int[] temperatures) {
    if(temperatures == null) return null;
    int[] ans = new int[temperatures.length];
    Stack<Integer> store = new Stack<>();//存温度的下标
    for(int i = 0; i < temperatures.length; i++){
      while (!store.isEmpty() && temperatures[i] > temperatures[store.peek()]){
        int prev = store.pop();//当前进来的温度比里面的温度高，所以前面小于当前温度的出去就行
        ans[prev] = i - prev;
      }
      store.push(temperatures[i]);
    }
    return ans;
  }
}
