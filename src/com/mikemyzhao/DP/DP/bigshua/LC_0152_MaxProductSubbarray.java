package com.mikemyzhao.DP.DP.bigshua;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-18 8:19
 * @Description:
 */
public class LC_0152_MaxProductSubbarray {
  //技巧:动态规划的原地化简，纯思路
  //技巧:到i的时候，往左扩多远可以得到最大乘积
  public static double max(double[] arr){
    if(arr == null || arr.length == 0){
      return 0;
    }
    int n = arr.length;
    //动态规划
    double premax = arr[0];//上一步最大
    double premin = arr[0];//上一步最小
    double ans = arr[0];
    //技巧:如果是正数就和上一步最大乘，如果是负数就找上一步的最小乘
    for(int i = 1; i < n; i++){
      double p1 = arr[i];//情况1:只有自己
      double p2 = arr[i] * premax;//是正数
      double p3 = arr[i] * premin;//是负数
      double curmax = Math.max(Math.max(p1, p2), p3);
      double curmin = Math.min(Math.min(p1, p2), p3);
      ans = Math.max(ans, curmax);
      premax = curmax;
      premin = curmin;
    }
    return ans;
  }


}
