package com.book.point2offerspecial.thirteen_track_in_time;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-15 12:08
 * @Description:
 */
public class PTOS082_SubsetForSumII {
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    Arrays.sort(candidates
    );
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
      process(nums, getNext(nums, i), target, subset, ans);

      subset.add(nums[i]);
      //注意：这里相同的数不可以重复出现
      process(nums, i + 1 , target - nums[i], subset, ans);
      subset.removeLast();
    }
  }

  private int getNext(int[] nums, int index){
    int next = index;
    while(next < nums.length && nums[next] == nums[index]){
      next++;
    }
    return next;
  }


}
