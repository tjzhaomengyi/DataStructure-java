package com.point2offerspecial.thirteen_track_in_time;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-14 20:39
 * @Description:
 */
public class PTOS081_SubsetsForSum {
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> ans = new LinkedList();
    List<Integer> subset = new LinkedList();
    process(candidates, 0, target, (LinkedList<Integer>) subset, ans);
    return ans;
  }

  //ji\
  public void process(int[] nums, int i, int target, LinkedList<Integer> subset, List<List<Integer>> ans){
    if(target == 0 || i > nums.length){
      ans.add(new LinkedList(subset));
    } else if(i < nums.length && target > 0){
      process(nums, i + 1, target, subset, ans);

      subset.add(nums[i]);
      //注意：这里相同的数可以重复出现，所以还可以再选进去
      process(nums, i , target - nums[i], subset, ans);
      int last = subset.removeLast();
    }
  }
}
