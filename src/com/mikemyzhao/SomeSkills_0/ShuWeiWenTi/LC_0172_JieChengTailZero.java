package com.mikemyzhao.SomeSkills_0.ShuWeiWenTi;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-18 8:37
 * @Description:
 */
public class LC_0172_JieChengTailZero {
  //数学结论:看有多少5的倍数
  public static int trailingZeroes(int n){
    int ans = 0;
    while(n != 0){
      n /= 5;
      ans += n;
    }
    return ans;
  }
}
