package com.mikemyzhao.SomeSkills_0.SumAndPreSum_0;

import java.util.TreeSet;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-24 21:13
 * @Description:// 请返回arr中，求个子数组的累加和，是<=K的，并且是最大的。
 * 	// 返回这个最大的累加和
 */
public class MaxSubArrSumLessOrEqualK_14 {
  public static int getMaxLessOrEqualSubArr(int[] arr, int K){
    TreeSet<Integer> set = new TreeSet<>();
    set.add(0);
    int max = Integer.MIN_VALUE;
    int sum = 0;
    for(int i = 0; i < arr.length; i++){
      sum += arr[i];//当前累加和
      //在presum >= sum - k;
      if(set.ceiling(sum - K) != null){
        max = Math.max(max ,sum - set.ceiling(sum - K));
      }
      set.add(sum);
    }
    return max;
  }
}
