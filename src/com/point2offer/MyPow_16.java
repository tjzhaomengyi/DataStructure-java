package com.point2offer;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-06 12:20
 * @Description:求x的n次幂
 */
public class MyPow_16 {
  public double myPow(double x, int n) {
//    if(n==0) return 1;
//    if(n<0){
//      return 1/myPow(x,-n);
//    }else if(n%2==1){
//      return myPow(x,n-1)*x;
//    }
//    return myPow(x*x,n/2);
    if(n == 0) return 1;
    if(n == 1) return x;
    if(n == -1) return 1 / x;
    double half = myPow(x, n / 2);
    double mod = myPow(x, n % 2);
    return half * half * mod;
  }
}
