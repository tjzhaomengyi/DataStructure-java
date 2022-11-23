package com.mikemyzhao.divide_and_conquer_10.bigshua;

import java.util.HashSet;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-27 8:45
 * @Description:求子序列，必须从左往右，能不能累加出K
 */
public class SubSequenceIsSumK_16 {
  //如果K比较大，考虑使用分治的算法，分为左右半边
  //todo:暴力递归版本求子序列的累加和

  public static boolean isSum(int[] arr, int sum){
    if(sum == 0){
      return true;//一个都不选
    }
    if(arr == null || arr.length == 0){
      return false;
    }
    if(arr.length == 1){
      return arr[0] == sum;
    }

    int N = arr.length;
    int mid = N >> 1;
    HashSet<Integer> leftSum = new HashSet<>();
    HashSet<Integer> rightSum = new HashSet<>();
    for(int l : leftSum){
      if(rightSum.contains(sum - l)){
        return true;
      }
    }
    return false;
  }
  //使用暴力递归求解子序列累加和
  public static void process(int[] arr, int i, int end, int pre, HashSet<Integer> ans){
    //pre记录之前的累加结果
    if(i == end){
      ans.add(pre);
    }else{
      //分为用当前数字和不用当前数字
      process(arr, i + 1, end, pre, ans);
      process(arr, i + 1, end, pre + arr[i], ans);
    }
  }
}
