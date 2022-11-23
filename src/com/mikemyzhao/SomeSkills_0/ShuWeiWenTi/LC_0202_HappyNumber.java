package com.mikemyzhao.SomeSkills_0.ShuWeiWenTi;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-18 9:17
 * @Description:
 */
public class LC_0202_HappyNumber {
  //技巧:什么是快乐数，就是把数的每一位平方求和后，如果结果是1就是快乐数，如果不是1就不是快乐数
  //数学结论:这是一个纯数学问题，属于歌德的不完备性猜想。
  // 一个数如果不是快乐数，肯定会得到4的结果。如果一个数是快乐数那么结果是1
  public  static boolean isHappy(int n){
    while(n != 1 && n != 4){//不管怎么算最后n，肯定等于1或者4
      int sum = 0;
      while(n != 0){
        sum += (n % 10) * (n % 10);//计算每一位的平方
        n /= 10;
      }
      n = sum;
    }
    return n == 1;
  }
}
