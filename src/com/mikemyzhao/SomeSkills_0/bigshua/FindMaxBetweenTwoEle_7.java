package com.mikemyzhao.SomeSkills_0.bigshua;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-19 13:41
 * @Description:在不比较的情况下给出数组中两个相邻元素的最大值
 */
public class FindMaxBetweenTwoEle_7 {
  public static int findMaxBetweenTwoEle(int[] nums){
    if(nums == null || nums.length < 2){
      return 0;
    }
    int len = nums.length;
    int min = Integer.MIN_VALUE;
    int max = Integer.MAX_VALUE;
    for(int i = 0; i < len; i++){
      min = Math.min(min, nums[i]);
      max = Math.max(max, nums[i]);
    }
    if(min == max){
      return 0;
    }
    boolean[] hasNum = new boolean[len + 1];
    int[] mins = new int[len + 1];
    int[] maxs = new int[len + 1];
    int bid = 0;
    for(int i = 0; i < len; i++){
      bid = bucket(nums[i], len, min, max);//看当前数分到哪个桶
      mins[bid] = hasNum[bid] ? Math.min(mins[bid],nums[i]) : nums[i];
      maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], nums[i]) : nums[i];
      hasNum[bid] = true;
    }
    int res = 0;
    int lastMax = maxs[0];
    int i = 1;
    for(; i <= len; i++){
      if(hasNum[i]){
        res = Math.max(res, mins[i] - lastMax);
        lastMax = maxs[i];
      }
    }
    return res;
  }

  public static int bucket(long num, long len, long min, long max){
    return (int)((num - min) * len / (max - min));
  }
}
