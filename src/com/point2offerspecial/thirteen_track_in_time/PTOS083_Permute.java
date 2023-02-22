package com.point2offerspecial.thirteen_track_in_time;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-15 17:28
 * @Description:
 */
public class PTOS083_Permute {
  public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> ans = new LinkedList<>();
    List<Integer> subset = new LinkedList<>();
    process(nums, (LinkedList<Integer>) subset, ans);
    return ans;
  }

  void process(int[] nums, LinkedList<Integer> subset, List<List<Integer>> ans){
    if(subset.size() == nums.length){
      ans.add(new LinkedList<>(subset));
    }
    for(int i = 0; i < nums.length; i++){
      if(subset.contains(nums[i])){
        continue;
      }
      subset.add(nums[i]);
      process(nums, subset, ans);
      subset.removeLast();
    }
  }
}
