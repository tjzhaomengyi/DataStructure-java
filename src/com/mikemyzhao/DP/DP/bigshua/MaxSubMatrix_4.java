package com.mikemyzhao.DP.DP.bigshua;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-16 16:59
 * @Description:在二维矩阵中找到最大子矩阵，就是接着最大子数组的问题，把每行累加，然后再求该数组的最大子数组
 */
public class MaxSubMatrix_4 {
  public static int maxSum(int[][] m){
    if(m == null || m.length == 0 || m[0].length == 0){
      return 0;
    }
    int N = m.length;
    int M = m[0].length;
    int max = Integer.MIN_VALUE;
    for(int i = 0; i < N; i++){
      //当前这一行到下面某一行求最大子数组
      int[] s = new int[M];
      for(int j = i; j < N; j++){
        for(int k = 0; k < M; k++){
          s[k] = s[k] + m[j][k];
        }
        max = Math.max(max,maxSubArr(s));
      }
    }
    return max;
  }
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
