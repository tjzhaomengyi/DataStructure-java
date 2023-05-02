package com.huaweiOD.od2023.s20e40;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-24 18:33
 * @Description:
 */
public class XinGonghao {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNext()) {
      int renshu = in.nextInt();
      int zimu = in.nextInt();
      int sum = 0;
      double value = Math.pow(26, zimu);
      if(renshu < value){
        System.out.println(1);
      } else {
        int i = 1;
        while(renshu > value * Math.pow(10, i)){
          i++;
        }
        System.out.println(i);
      }
    }
  }
}
