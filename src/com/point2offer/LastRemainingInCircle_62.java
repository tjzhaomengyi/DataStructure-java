package com.point2offer;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-12 15:10
 * @Description:约瑟夫环问题 n = 5, m = 3
 */
public class LastRemainingInCircle_62 {public int lastRemaining(int n, int m) {
  //x即dp[i,m]在n个数字下，删除第m个数字最后剩下为dp[i,m];dp[0,0]最后肯定是0
  /**约瑟夫环的结论：(当前index + m) % 上一轮剩余数字的个数**/
  int x = 0;
  for (int i = 2; i <= n; i++) {
    x = (x + m) % i;
  }
  return x;
}
}
