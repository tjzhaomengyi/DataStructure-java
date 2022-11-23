package com.mikemyzhao.SomeSkills_0.ShuWeiWenTi;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-18 9:11
 * @Description:
 */
public class LC_0191_NumberOf1Bit {
  public static int hammingWeight(int n){
    int bits = 0;
    int rightOne = 0;
    while(n != 0){
      bits++;
      rightOne = n & (-n);
      n ^= rightOne;//技巧:如果两个是1的就消掉了
    }
    return bits;
  }

  public static int hammingWeight2(int n) {
    n = (n & 0x55555555) + ((n >>> 1) & 0x55555555);
    n = (n & 0x33333333) + ((n >>> 2) & 0x33333333);
    n = (n & 0x0f0f0f0f) + ((n >>> 4) & 0x0f0f0f0f);
    n = (n & 0x00ff00ff) + ((n >>> 8) & 0x00ff00ff);
    n = (n & 0x0000ffff) + ((n >>> 16) & 0x0000ffff);
    return n;
  }
}
