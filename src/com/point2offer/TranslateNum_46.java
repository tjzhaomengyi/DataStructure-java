package com.point2offer;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-08 20:17
 * @Description:
 */
public class TranslateNum_46 {

  public int translateNum(int num) {
    String numstr=String.valueOf(num);
    int n = numstr.length();
    int[] dp = new int[n+1];
    dp[0]=1;dp[1]=1; //长度为n的时候有多少方法
    for(int i=2;i<n+1;i++){
      if(numstr.charAt(i-2)=='1' ||(numstr.charAt(i-2)=='2' && numstr.charAt(i-1)<'6')){
        dp[i]=dp[i-2]+dp[i-1];
      }else{
        dp[i]=dp[i-1]+1;
      }
    }
    return dp[n];
  }
}
