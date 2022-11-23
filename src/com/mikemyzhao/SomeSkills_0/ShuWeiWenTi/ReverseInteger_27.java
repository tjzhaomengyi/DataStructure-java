package com.mikemyzhao.SomeSkills_0.ShuWeiWenTi;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-10 10:32
 * @Description:正整数Integer反转问题
 */
public class ReverseInteger_27 {
  //技巧：只要是这种问题都把正数转到负数上操作，因为负数的最高位是1，能表达的有效范围更大
  public static int reverse(int x){
    boolean neg = (( x >>> 31) & 1) == 1;
    x = neg ? x : -x;
    int m = Integer.MIN_VALUE / 10;
    int o = Integer.MIN_VALUE  % 10;
    int res = 0;
    while(x != 0){
      if(res < m || (res == m && x % 10 < o)){
        return 0;
      }
      res = res * 10 + x % 10;
      x /= 10;
    }
    return neg ? res : Math.abs(res);
  }
}
