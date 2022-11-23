package com.mikemyzhao.SortList_ALL.Stack.bigshua;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-19 13:49
 * @Description:
 */
public class LC_0739_NearestTempreature {
  public static int[] dailyTemperatures(int[] arr) {
    if(arr == null || arr.length == 0){
      return new int[0];
    }
    int N = arr.length;
    int[] ans = new int[N];
    Stack<List<Integer>> stack = new Stack<>();
    for(int i = 0; i < N; i++){
      while(!stack.isEmpty() && arr[stack.peek().get(0)] < arr[i]){
        List<Integer> popIs = stack.pop();
        for(Integer pop : popIs){
          ans[pop] = i - pop;//求天数差
        }
      }
      if(!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]){
        stack.peek().add(Integer.valueOf(i));
      } else {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(i);
        stack.push(list);
      }
    }
    return ans;
  }
}
