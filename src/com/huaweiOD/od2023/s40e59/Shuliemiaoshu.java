package com.huaweiOD.od2023.s40e59;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-21 18:25
 * @Description:
 */
public class Shuliemiaoshu {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNext()) {
      int len  = in.nextInt();
      System.out.println(solve(len));
    }
  }


  public static String solve(int len){
    String tmp = "1";
    for(int i = 1; i <= len; i++){ //从1到4
      int index = 0;
      char preChar = ' ';
      StringBuilder sb = new StringBuilder();
      for(int j = 0; j < tmp.length(); j++){ //遍历tmp
        if(j == 0){
          preChar = tmp.charAt(j);
        }
        char c = tmp.charAt(j);
        if(c == preChar){
          index++;
        } else {
          sb.append(index).append(preChar);
          index = 1;
          preChar = c;
        }
      }
      sb.append(index).append(preChar);
      tmp = sb.toString();
    }
    return tmp;
  }

}
