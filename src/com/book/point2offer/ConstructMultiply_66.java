package com.book.point2offer;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-10 21:10
 * @Description:不用乘法算出一个数组的乘积,变向DP
 */
public class ConstructMultiply_66 {
  public int[] constructArr(int[] a) {
    int[] dp = new int[a.length];
    for(int i=0,p=1;i<a.length;i++){
      dp[i] = p;
      p*=a[i];
    }
    for(int i=a.length-1,p=1;i>=0;i--){
      dp[i]*=p;
      p*=a[i];
    }
    return dp;
  }

}
