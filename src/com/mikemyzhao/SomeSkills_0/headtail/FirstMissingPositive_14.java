package com.mikemyzhao.SomeSkills_0.headtail;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-24 22:32
 * @Description:https://leetcode.com/problems/first-missing-positive/
 * 未排序的数组，找到第一个缺失的正数
 */
public class FirstMissingPositive_14 {
  public static int firstMissingPositive(int[] arr){
    //l盯着的位置
    // 0 ~ L-1有效区
    int L = 0;
    int R = arr.length;
    while(L != R){
      //1 2 3 4...
      //0 1 2 3...
      if(arr[L] == L + 1){
        //进入有效区,L向右移动扩大
        L++;
      } else if(arr[L] <= L || arr[L] > R || arr[arr[L] - 1] == arr[L]) {//L位置该出现的小于L
        swap(arr,L,--R);
      } else{
        swap(arr, L, arr[L] - 1);
      }
    }
    return L + 1;
  }
  public static void swap(int[] arr, int i, int j){
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }
}
