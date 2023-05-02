package com.huaweiOD.od2023.s60e79;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-20 12:49
 * @Description:
 */
public class LiangshuzhiheJueduizhizuixiao {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String[] values = in.nextLine().split(" ");
      int ans = solve(values);
      System.out.println(ans);
    }
  }

  public static int solve(String[] values){
    int[] arr = new int[values.length];
    for(int i = 0; i < values.length; i++){
      arr[i] = Integer.valueOf(values[i]);
    }
    int min = Integer.MAX_VALUE;
    for(int i = 0; i < values.length; i++){
      for(int j = i + 1; j < values.length; j++){
        min = Math.min(min, Math.abs(arr[i] + arr[j]));
      }
    }
    return min;
  }


}
