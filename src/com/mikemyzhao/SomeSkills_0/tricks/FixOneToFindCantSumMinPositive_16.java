package com.mikemyzhao.SomeSkills_0.tricks;

import java.util.Arrays;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-27 10:18
 * @Description:正整数数组中中出不能累加出的最小正整数
 */
public class FixOneToFindCantSumMinPositive_16 {
  //通过补充1，让1为后面补坑，假设数组中有1这个数
  public static int unformedSum(int[] arr){
    if(arr == null || arr.length == 0){
      return 0;
    }
    Arrays.sort(arr);
    int range = 1;//默认数组中有1这个数
    //todo:如果arr[i] > range，说明累加不到当前这个数，如果arr[i] < range说明可以累加到[i],
    //todo:此时range扩充 = range + arr[i]
    for (int i = 1; i < arr.length; i++){
      if(arr[i] > range + 1){
        return range + 1;//这个扩充不到就是累加和缺失的最小的数
      } else {
        range += arr[i];
      }
    }
    return range + 1;
  }
}
