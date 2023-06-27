package com.huaweiOD.score200;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-05-23 15:53
 * @Description:
 */
public class Buhan101deshu {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextInt()) {
      int start = in.nextInt();
      int end = in.nextInt();
      int ans = 0;
      for(int i = start; i <= end; i++){
        String binary = Integer.toBinaryString(i);
        ans += binary.contains("101") ? 1 : 0;
      }
      System.out.println(ans);
    }
  }
}
