package com.point2offer;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-10 13:23
 * @Description:不用加减乘除的四则运算,纯粹有病，
 * 1、c=a&b<<1左移做进位
 * 2、a^b 做加法
 * 3、b=c
 *
 */
public class SumBinary_65 {
  /**无进位和与异或相同，进位和与运算相同**/
  public int add(int a, int b) {
    while(b!=0){
      int c= (a&b)<<1;// c=进位
      a^=b;//a=非进位和
      b=c;//b=进位
    }
    return a;
  }

  public static void main(String[] args) {
    new SumBinary_65().add(1,1);
  }
}
