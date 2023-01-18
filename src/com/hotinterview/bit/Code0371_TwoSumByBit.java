package com.hotinterview.bit;

/**
 * @Author: zhaomengyi
 * @Date: 2023-01-04 9:46 下午
 * @Description:
 */
public class Code0371_TwoSumByBit {
  public int getSum(int a, int b) {
    int ans = a;
    while(b != 0){
      ans = a ^ b;
      b = (a & b) << 1;
      a = ans;
    }
    return ans;
  }
}
