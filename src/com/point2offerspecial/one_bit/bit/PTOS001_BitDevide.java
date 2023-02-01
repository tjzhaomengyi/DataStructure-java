package com.point2offerspecial.one_bit.bit;

/**
 * @Author: zhaomengyi
 * @Date: 2023-01-13 8:30 下午
 * @Description:
 */
public class PTOS001_BitDevide {

  public int divide(int a, int b){
    if(a == Integer.MIN_VALUE && b == Integer.MIN_VALUE){
      return 1;
    } else if(b == Integer.MIN_VALUE){
      return 0;
    } else if(a == Integer.MIN_VALUE) {
      if (b == -1) {
        return Integer.MAX_VALUE;
      }
    }
        int nagative_flag = 2;
        if(a > 0){
          nagative_flag--;
          a = -a;
        }
        if(b > 0){
          nagative_flag--;
          b = -b;
        }
        int ans = div(a,b);
        return nagative_flag == 1 ? -ans : ans;
  }

  public int div(int a, int b){ // 因为负数范围大，所以a和b都是负数
    int ans = 0;
    while (a <= b){
      int val = b;
      int quotient = 1;
      //思路：Integer.MIN_VALUE:0x1后面30个0(-2^31)，
      // Integer.MAX_VALUE:0x0后面30个1（2^31-1）
      //
      while(val >= 0xc0000000 && a <= val + val){ //val的2倍不能超过0x80000000
        quotient += quotient; //2倍
        val += val;//除数每次两倍增长
      }
      ans += quotient;
      a -= val;
    }
    return ans;
  }

  public static void main(String[] args) {
    int ans = Integer.MIN_VALUE;
    int ans1 = Integer.MAX_VALUE;
    int tmp = ans >>> 31;
    int tmp1 = ans1 >> 31;
    System.out.println(tmp);
    System.out.println(tmp1);
    int ans2 = new PTOS001_BitDevide().divide(1, 1);
    System.out.print(ans2);
  }
}
