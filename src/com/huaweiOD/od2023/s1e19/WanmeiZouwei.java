package com.huaweiOD.od2023.s1e19;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-26 14:34
 * @Description:
 */
public class WanmeiZouwei {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNext()) {
      String str = in.next();
      int len = str.length();
      int part_len = len / 4;
      int[] need = new int[4];
      Arrays.fill(need, part_len);
      for(int i = 0; i < len; i++){
        char c = str.charAt(i);
        if(c == 'A'){
          need[0]--;
        } else if(c == 'D'){
          need[1]--;
        } else if(c == 'W'){
          need[2]--;
        } else if(c == 'S'){
          need[3]--;
        }
      }
      int ans = 0;
      for(int i = 0; i < 4; i++){
        if(need[i] > 0){
          ans += need[i];
        }
      }
      System.out.println(ans);
    }
  }
}
