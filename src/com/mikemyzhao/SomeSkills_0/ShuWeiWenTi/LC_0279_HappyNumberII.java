package com.mikemyzhao.SomeSkills_0.ShuWeiWenTi;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-18 17:28
 * @Description:
 */
public class LC_0279_HappyNumberII {
  //数学结论:一个整数最多由四个数的平方数组成
  // 规律一：个数不超过4
  // 规律二：出现1个的时候，显而易见
  // 规律三：任何数 % 8 == 7，一定是4个
  // 规律四：任何数消去4的因子之后，剩下rest，rest % 8 == 7，一定是4个
  public static int numsquare(int n){
    int rest = n;
    while(rest % 4 == 0){
      rest /= 4;
    }
    if(rest % 8 == 7){
      return 4;
    }

    int f = (int) Math.sqrt(n);
    if(f * f == n){
      return 1;
    }

    for(int first = 1; first * first <= n; first++){
      int second = (int) Math.sqrt(n - first * first);
      if(first * first + second * second == n){
        return 2;
      }
    }
    return 3;
  }
}
