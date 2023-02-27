package com.mikemyzhao.SortList_ALL.Stack.bigshua;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-19 13:49
 * @Description:这道题左神复杂了
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

  //这个方法比上面的方法更省略一点，思路：就是当前要进来的温度如果比stack中的温度高的话把前面低的温度出栈，并求天数差即可。然后这个温度入栈
  public int[] dailyTemperatures_easy(int[] temperatures) {
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
