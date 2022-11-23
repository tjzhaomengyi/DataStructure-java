package com.mikemyzhao.SomeSkills_0.ShuWeiWenTi;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-18 10:07
 * @Description:
 */
public class LC_0461_HammingDistance {
  //数学结论:求两个二进制数二进制位不同的个数
  public int hammingDistance(int x, int y) {
    int r = x ^ y;
    int res = 0;
    while(r != 0){
      res++;
      r = r ^ (r & (-r));
    }
    return res;
  }
}
