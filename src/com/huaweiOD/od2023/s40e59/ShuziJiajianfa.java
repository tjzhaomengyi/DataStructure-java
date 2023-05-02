package com.huaweiOD.od2023.s40e59;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-21 12:01
 * @Description:
 */
public class ShuziJiajianfa {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNext()) {
      int s = in.nextInt();
      int e = in.nextInt();
      int a = in.nextInt();
      int b = in.nextInt();
      int distance = Math.abs(e - s);
      int oneMinVlue = 0;
      int tmp = distance;
      while(0 != tmp % b){
        tmp -= a;
        oneMinVlue++;
      }
      tmp = distance;
      int twoMinValue = 0;
      while (0 != tmp % b){
        tmp += a;
        twoMinValue++;
      }
      System.out.println(Math.min(oneMinVlue, twoMinValue));
    }
  }


}
