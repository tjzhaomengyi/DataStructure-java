package com.point2offer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-09 19:29
 * @Description:单调递增找两数之和
 */
public class TwoSun_57 {
  public int[] twoSum(int[] nums, int target) {
    int i=0,j=nums.length-1;
    while(i<j){
      int s = nums[i]+nums[j];
      if(s<target) i++;
      else if(s>target) j--;
      else return new int[]{nums[i],nums[j]};
    }
    return new int[0];
  }

  public int[][] findContinuousSequence(int target) {

    return null;
  }
}
