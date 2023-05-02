package com.huaweiOD.nowcoder;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-11 17:55
 * @Description:https://www.cnblogs.com/ryelqy/p/10842794.html
 */
public class GetSqr {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while(in.hasNext()){

    }
  }

  public static double sqr(double in){
    double x = 1;
    double x1 = x - (x * x * x - in) / (3 * x * x);//技巧:使用二阶泰勒展开式求梯度
    while(x - x1 > 0.0000001 || x1 - x > 0.0000001){
      x = x1;
      x1 = x - (x * x * x - in) / (3 * x * x);
    }
    return x1;
  }
}
