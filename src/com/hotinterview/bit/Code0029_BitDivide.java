package com.hotinterview.bit;

/**
 * @Author: zhaomengyi
 * @Date: 2023-01-10 4:30 下午
 * @Description:
 */
public class Code0029_BitDivide {
  public int add(int a, int b){
    int ans = a;
    while(b != 0){
      ans = a ^ b;
      b = (a & b) << 1;
      a = ans;
    }
    return ans;
  }

  public int neg(int a){
    return add(~a, 1);
  }

  public boolean isNeg(int a){
    return a < 0;
  }

  public int minus(int a, int b){
    return add(a, neg(b));
  }

  public int multi(int a, int b){
    int res = 0;
    while(b != 0){
      if((b & 1) != 0){
        res = add(res, a);
      }
      //思路：就是原始的乘法运算的步骤，b的下一位要乘a的最后一位(b右推)，但是相加的时候要比之前的位高1(所以a向左推)
      b >>>= 1;//把b向右侧推
      a <<= 1; //把a向左推
    }
    return res;
  }

  public int div(int a, int b){
    int x = isNeg(a) ? neg(a) : a;
    int y = isNeg(b) ? neg(b) : b;
    int res = 0;
    for(int i = 30; i >= 0; i = minus(i, 1)){
      if((x >> i) >= y){
        res |= (1 << i);// 或到结果中，把1加到 res结果中
        x = minus(x, y << i);
      }
    }
    return isNeg(a) ^ isNeg(b) ? neg(res) : res;
  }

  //因为Integer.MIN 的 相反数不能表示为正数，所以要特殊处理
  public int divide(int a, int b){
    if(a == Integer.MIN_VALUE && b == Integer.MIN_VALUE){
      return 1;
    } else if(b == Integer.MIN_VALUE){
      return 0;
    } else if(a == Integer.MIN_VALUE){
      if(b == neg(1)){
        return Integer.MAX_VALUE;
      } else { //a是最小值
        int c = div(add(a, 1) , b);
        return add(c, div(minus(a, multi(c, b)),b));//太难了~直接记住例子吧：见稿纸
      }
    } else {
      return div(a, b);
    }
  }
}
