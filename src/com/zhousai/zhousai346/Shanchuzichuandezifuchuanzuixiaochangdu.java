package com.zhousai.zhousai346;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-05-21 10:47
 * @Description:
 */
public class Shanchuzichuandezifuchuanzuixiaochangdu {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str = in.nextLine();
      int ans = minLength(str);
      System.out.println(ans);
    }
  }

  public static int minLength(String s) {
    int ans = s.length();
    String tmp = s;
    while(tmp.contains("AB") || tmp.contains("CD")){
      if(tmp.contains("AB")){
        tmp = tmp.replace("AB", "");

      }
      if(tmp.contains("CD")){
        tmp = tmp.replace("CD", "");

      }
    }
    return tmp.length();
  }
}
