package com.huaweiOD.od2023.s100e119;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-18 13:57
 * @Description:
 */
public class Qishuiping {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      int n = Integer.valueOf(in.nextLine());
      if(n > 0) {
        System.out.println(in.nextInt() / 2);
      }
    }
  }
}
