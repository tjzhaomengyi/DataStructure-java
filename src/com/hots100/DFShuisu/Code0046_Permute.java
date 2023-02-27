package com.hots100.DFShuisu;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2022-12-18 1:17 下午
 * @Description:
 */
public class Code0046_Permute {
  //技巧：使用DFS回溯生成全排列
  public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> track = new ArrayList<>();
    process(nums, track, res);
    return res;
  }

  void process(int[] nums, List<Integer> track, List<List<Integer>> res){
    if(track.size() == nums.length){
      res.add(new ArrayList<>(track));
    }
    for(int i = 0; i < nums.length; i++){
      //排除track中已经有的
      if(track.contains(nums[i])){
        continue;
      }
      track.add(nums[i]);
      process(nums, track, res);
      track.remove(track.size() - 1);//技巧：回溯！！！
    }
  }
}
