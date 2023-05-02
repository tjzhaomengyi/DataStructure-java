package com.huaweiOD.od2023.s80e99;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-19 19:27
 * @Description:
 */
public class DoudizhuShunzi {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str1 = in.nextLine();
      String str2 = in.nextLine();
      int[] pokers = new int[16];
      Arrays.fill(pokers, 4);
      String ans = getLongestShunzi(pokers, str1, str2);
      System.out.println(ans);
    }
  }

  public static String getLongestShunzi(int[] pokers, String str1, String str2){
    String allStr = str1 + "-" + str2;
    String[] arr = allStr.split("-");
    for(int i = 0; i < arr.length; i++){
      int num = 0;
      if(arr[i].equals("J")){
        num = 11;
      } else if(arr[i].equals("Q")){
        num = 12;
      } else if(arr[i].equals("K")){
        num = 13;
      } else if(arr[i].equals("A")){
        num = 14;
      } else {
        num = Integer.valueOf(arr[i]);
      }
      pokers[num]--;
    }

    int max = 0;
    int len = 0;
    String ans = "";
    String tmp = "";
    for(int i = 3; i <= 15; i++){//技巧:多借一位可以看到前面整体情况，就不需要在末尾进行多分支讨论了
      if(pokers[i] == 0 || i == 15){
        if(len >= 5 && len >= max) {
          max = Math.max(max, len);
          ans = tmp;
        }
        len = 0;
        tmp = "";
        continue;
      } else {
        String follw = "";
        if(i == 11){
          follw = "J";
        } else if(i == 12){
          follw = "Q";
        } else if(i == 13){
          follw = "K";
        } else if(i == 14) {
          follw = "A";
        } else {
          follw = String.valueOf(i);
        }
        tmp = tmp + "-" + follw;
        len++;
      }
    }
    if(max != 0){
      return ans.substring(1);
    } else {
      return "NO-CHAIN";
    }
  }

}
