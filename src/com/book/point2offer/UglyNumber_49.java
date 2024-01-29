package com.book.point2offer;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-09 9:01
 * @Description:丑数问题，只能被2、3、5整除
 * @Solve:一个丑数等于另外一个数*2/*3/*5得到的
 */
public class UglyNumber_49 {
  public boolean isUgly(int n){
    while(n%2==0){
      n=n/2;
    }
    while(n%3==0){
      n=n/3;
    }
    while(n%5==0){
      n=n/5;
    }
    return n==1;
  }
  public int nthUglyNumber(int n) {
    if(n<0) return 0;
    int[] dp = new int[n];//下标为i时候，第一小的丑数是多少
    dp[0]=1;
    int p2=0,p3=0,p5=0;
    for(int i=1;i<n;i++){
      int lastUgly = dp[i-1];
      while(lastUgly>=dp[p2]*2)p2++;//p2位置的丑数小于最大丑数
      while(lastUgly>=dp[p3]*3)p3++;
      while(lastUgly>=dp[p5]*5)p5++;
      dp[i]=Math.min(Math.min(dp[p2]*2,dp[p3]*3),dp[p5]*5);
    }
    return dp[n-1];
  }


}
