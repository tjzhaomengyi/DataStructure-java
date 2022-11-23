package com.mikemyzhao.SomeSkills_0.bigshua;

import java.util.HashMap;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-10 9:34
 * @Description:
 */
public class TwoSum_27 {
  public static int[] twoSum(int[] nums, int target){
    HashMap<Integer, Integer> map = new HashMap<>();
    for(int i = 0; i < nums.length; i++){
      if(map.containsKey(target - nums[i])){
        return new int[]{map.get(target - nums[i]),i};
      }
      map.put(nums[i], i);
    }
    return new int[]{-1, -1};
  }
}
