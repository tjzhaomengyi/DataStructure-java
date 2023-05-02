package com.huaweiOD.od2023.s60e79;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-20 15:29
 * @Description:
 */
public class Shifouzixulie {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str1 = in.nextLine();
      String str2 = in.nextLine();
      int m = str1.length();
      int n = str2.length();

      int last_index = n - 1;
      int ans = -1;
      for(int i = m - 1; i >= 0; i--){
        char target = str1.charAt(i);
        for(int j = last_index; j >= 0;){
          if(target == str2.charAt(j) && i != 0){
            last_index = j;
            break;
          } else if(target == str2.charAt(j) && i == 0){
            ans = j;
            break;
          }
          j--;
        }
      }
      System.out.println(ans);
    }
  }


}
