package com.mikemyzhao.DPAndRecursion_5.DP.bigshua;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-16 15:05
 * @Description:求最大子数组的和
 */
public class MaxSubArray_4 {
  public static int maxSubArr(int[] arr){
    if(arr == null || arr.length == 0){
      return 0;
    }
    int pre = arr[0];
    int max = arr[0];
    for(int i = 1; i < arr.length; i++){
      int p1 = arr[i];//当前的子数组最大值就是自己
      int p2 = pre + arr[i];//当前如果是最大的话，说明前面的i-1的pre子数组和也是最大
      int cur = Math.max(p1,p2);
      max = Math.max(max,cur);
      pre = cur;
    }
    return max;
  }

}
