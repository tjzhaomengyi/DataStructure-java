package com.mikemyzhao.TwoPntAndBSearch.bsearch.bigshua;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-20 21:21
 * @Description:【经典】最长递增子序列问题，这个子序列可以是不连续的
 */
public class LongestIncreasingSubsequence_9 {
  //使用二分方法，建立一个end区，end[i]记录当前所有最大长度为i+1的递增子序列的最小值
  public static int LIS(int[] arr){
    if(arr == null || arr.length == 0){
      return 0;
    }
    int[] ends = new int[arr.length];
    ends[0] = arr[0];
    //比较end中有效区最右位置
    int right = 0;
    //控制end的二分查找
    int l = 0;
    int r = 0;
    int m = 0;
    int max = 1;
    for(int i = 1; i < arr.length; i++){
      l = 0;
      r = right;
      while(l <= r){
        m = (l + r) / 2;
        if(arr[i] > ends[m]){
          l = m + 1;
        } else {
          r = m - 1;
        }
      }
      right = Math.max(right, l);
      ends[l] = arr[i];//这个位置可能是没出现过的也可能是找到的位置
      max = Math.max(max, l + 1);
    }
    return max;
  }
}
