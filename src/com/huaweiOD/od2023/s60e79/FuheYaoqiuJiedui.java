package com.huaweiOD.od2023.s60e79;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-20 13:53
 * @Description:
 */
public class FuheYaoqiuJiedui {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      int person = Integer.valueOf(in.nextLine());
      String[] lines = in.nextLine().split(" ");
      int target = Integer.valueOf(in.nextLine());
      solve(person, lines, target);
    }
  }

  public static void solve(int person, String[] lines, int target){
    int[] arr = new int[person];
    for(int i = 0; i < person; i++){
      arr[i] = Integer.parseInt(lines[i]);
    }
    int result = 0;
    for(int i = 0; i < person; i++){
      for(int j = i + 1; j < person; j++){
        if(target == arr[i] + arr[j]){
          result++;
        }
      }
    }
    System.out.println(result);
  }



}
