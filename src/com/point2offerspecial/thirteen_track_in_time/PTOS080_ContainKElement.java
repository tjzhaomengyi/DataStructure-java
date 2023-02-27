package com.point2offerspecial.thirteen_track_in_time;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-14 20:19
 * @Description:
 */
public class PTOS080_ContainKElement {
  public List<List<Integer>> combine(int n, int k) {
    List<List<Integer>> ans = new LinkedList<>();
    if(k == 0) return ans;
    List<Integer> subset = new LinkedList<>();
    process(n, k , 1, (LinkedList<Integer>) subset, ans);
    return ans;
  }

  public void process(int n, int k, int i, LinkedList<Integer> subset, List<List<Integer>> ans){
    if(subset.size() == k){
      ans.add(new LinkedList<>(subset));
    } else if(i <= n){
      process(n, k, i + 1, subset, ans); //不放

      subset.add(i);
      process(n, k, i + 1, subset, ans);
      subset.removeLast();
    }

  }

}
