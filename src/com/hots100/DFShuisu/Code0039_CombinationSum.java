package com.hots100.DFShuisu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2022-12-17 5:16 下午
 * @Description:
 */
public class Code0039_CombinationSum {
  // 纸币无限使用凑金额问题
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> track = new ArrayList<>();
    //技巧：使用一个下标记录起始位置
    process(candidates, 0, track, res, target);
    return res;
  }

  void process(int[] nums, int start, List<Integer> track, List<List<Integer>> res, int target){
    if(target == 0){
      res.add(new ArrayList<>(track));
      return;
    }
    if(target < 0) return;
    for(int i = start; i < nums.length; i++){
      track.add(nums[i]);
      process(nums, start, track, res, target - nums[i]);
      track.remove(track.size() - 1);
    }
  }
}
