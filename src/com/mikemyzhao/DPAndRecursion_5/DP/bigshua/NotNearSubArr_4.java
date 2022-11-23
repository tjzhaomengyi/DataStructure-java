package com.mikemyzhao.DPAndRecursion_5.DP.bigshua;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-16 17:15
 * @Description:
 */
public class NotNearSubArr_4 {
  public static int subSeqMaxSumNoNext(int[] arr){
    if(arr == null || arr.length == 0){
      return 0;
    }
    if(arr.length == 1){
      return arr[0];
    }
    int[] dp = new int[arr.length];
    dp[0] = arr[0];
    dp[1] = Math.max(arr[0],arr[1]);
    for(int i = 2; i < arr.length; i++){
      int p1 = dp[i-1];//不要i
      int p2 = dp[i-2] + arr[i]; //要i
      int p3 = arr[i];//i比前面的子序列和都大
      dp[i] = Math.max(Math.max(p1,p2),p3);
    }
    return dp[arr.length - 1];
  }
}
