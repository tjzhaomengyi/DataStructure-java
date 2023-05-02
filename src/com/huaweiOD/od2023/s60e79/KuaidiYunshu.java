package com.huaweiOD.od2023.s60e79;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-20 14:13
 * @Description:
 */
public class KuaidiYunshu {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String[] line = in.nextLine().split(",");
      int maxValue = Integer.parseInt(in.nextLine());
      int[] arr = new int[line.length];
      for(int i = 0; i < line.length; i++){
        arr[i] = Integer.parseInt(line[i]);
      }
      Arrays.sort(arr);
      System.out.println(solve(arr, 0, 0, 0, maxValue));
    }
  }

  public static int solve(int[] arr, int index, int count, int sum, int maxValue){
    if(sum > maxValue || index >= arr.length){
      return count - 1;
    }
    int ans1 = solve(arr, index + 1, count + 1, sum + arr[index], maxValue);
    int ans2 = solve(arr, index + 1, count, sum, maxValue);
    return Math.max(ans1, ans2);
  }


}
