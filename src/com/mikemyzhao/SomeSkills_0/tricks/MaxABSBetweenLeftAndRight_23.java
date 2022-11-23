package com.mikemyzhao.SomeSkills_0.tricks;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-06 8:50
 * @Description:给定一个数组，中间给一刀，找出左右数组最大差值
 */
public class MaxABSBetweenLeftAndRight_23 {
  //数学结论：就让这一刀在最左边留下[0],或者最右边留下[n-1]，min([0],[n-1]),res = max - min
  public static  int getMaxAbs(int[] arr){
    int max = Integer.MIN_VALUE;
    for(int i = 0; i < arr.length; i++){
      max = Math.max(max, arr[i]);
    }
    return max - Math.min(arr[0], arr[arr.length - 1]);
  }
}
