package com.huaweiOD.od2023.s1e19;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-26 14:48
 * @Description:
 */
public class JiaohuanZifu {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str = in.nextLine();
      System.out.println(swap(str));
    }
  }

  public static String swap(String str){
    char[] chars = str.toCharArray();
    for(int i = 0; i < chars.length; i++){
      for(int j = i + 1; j < chars.length; j++){
        if(chars[i] - chars[j] > 0){
          char tmp = chars[j];
          chars[j] = chars[i];
          chars[i] = tmp;
          return String.valueOf(chars);
        }
      }
    }
    return str;
  }
}
