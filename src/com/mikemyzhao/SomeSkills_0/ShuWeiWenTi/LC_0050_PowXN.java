package com.mikemyzhao.SomeSkills_0.ShuWeiWenTi;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-16 18:55
 * @Description:
 */
public class LC_0050_PowXN {
  //数学结论:计算x的n次方，用他的2次幂连乘解决,把指数往右挪，降幂，只要二进制是1就乘进去，如果不是就把底平方
  public static double myPow(double x, int n){
    if(n == 0){
      return 1D;
    }
    int pow = Math.abs(n == Integer.MIN_VALUE ? n + 1 : n);
    double t = x;
    double ans = 1D;
    while(pow != 0){
      if((pow & 1) != 0){
        ans *= t;//t作为底要不停的变
      }
      //技巧:挪一位乘一个底，挪一位乘一个底
      pow = pow >> 1;
      t = t * t;
    }
    if(n == Integer.MIN_VALUE){
      ans = ans * x;
    }
    return n < 0 ? (1D / ans) : ans;
  }
}
