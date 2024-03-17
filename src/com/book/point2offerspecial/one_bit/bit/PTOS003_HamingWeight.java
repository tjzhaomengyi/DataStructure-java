package com.book.point2offerspecial.one_bit.bit;

/**
 * @Author: zhaomengyi
 * @Date: 2023-01-12 6:30 下午
 * @Description:
 */
public class PTOS003_HamingWeight {
  public int[] countBits(int n) {
    int[] ans = new int[n + 1];
    ans[0] = 0;
    //思路 i & (i - 1)可以讲i的最右的1变成0，【利用这个性质】所以i 比 i & (i - 1) 多 1 个1
    for(int i = 1; i <= n; i++){
      ans[i] = ans[i & (i - 1)] + 1;//其实是个巧劲
    }
    return ans;
  }
  public int hanMingWeight(int n){
    int cnt = 0;
    while(n != 0){
      n = n & (n - 1);
      cnt++;
    }
    return cnt;
  }
}
