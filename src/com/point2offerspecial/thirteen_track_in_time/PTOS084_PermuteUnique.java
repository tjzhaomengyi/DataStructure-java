package com.point2offerspecial.thirteen_track_in_time;

import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-15 18:47
 * @Description:
 */
public class PTOS084_PermuteUnique {
  public List<List<Integer>> permuteUnique(int[] nums) {
    HashSet<List<Integer>> ans = new HashSet<>();
    List<Integer> subset = new LinkedList<>();
    HashMap<Integer,Boolean> mem = new HashMap<>();
    process(nums, 0, (LinkedList<Integer>) subset, ans, mem);
    return new ArrayList<>(ans);
  }

  void process(int[] nums, int index, LinkedList<Integer> subset, HashSet<List<Integer>> ans
      , HashMap<Integer, Boolean> mem){
    if(subset.size() == nums.length){
      ans.add(new LinkedList<Integer>(subset));
    }
    for(int i = 0; i < nums.length; i++){
      if(mem.get(i) == true){
        continue;
      }
      subset.add(nums[i]);
      mem.put(i, true);
      process(nums, index + 1, subset, ans, mem);
      subset.removeLast();
      mem.put(i, false);
    }
  }
}
