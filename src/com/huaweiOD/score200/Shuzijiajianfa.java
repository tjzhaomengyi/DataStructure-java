package com.huaweiOD.score200;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-05-26 12:16
 * @Description:
 */
public class Shuzijiajianfa {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextInt()) {
      int s = in.nextInt();
      int e = in.nextInt();
      int a = in.nextInt();
      int b = in.nextInt();
      System.out.println(getMinTimes(s, e, a, b));
    }
  }

  public static int getMinTimes(int s, int e, int a, int b){
    int timeA = 0;
    int timeB = 0;
    int distance = e - s;
    int t_d = distance;
    while (t_d % b != 0){
      t_d -= a;
      timeA++;
    }

    t_d = distance;
    while (t_d % b != 0){
      t_d += a;
      timeB++;
    }
    return Math.min(timeA, timeB);
  }


}
