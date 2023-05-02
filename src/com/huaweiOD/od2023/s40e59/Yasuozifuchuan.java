package com.huaweiOD.od2023.s40e59;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-21 22:02
 * @Description:
 */
public class Yasuozifuchuan {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNext()) {
      String str = in.next();
      String ans = yasuo(str);
      if(ans.length() == str.length()){
        System.out.println("!error");
      } else {
        System.out.println(ans);
      }
    }
  }

  public static String yasuo(String str){
    int num = 0;
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < str.length(); i++){
      char c = str.charAt(i);
      if(num == 0 && Character.isLetter(c)){
        sb.append(c);
      } else if(Character.isDigit(c)){
        num = num * 10 + Character.getNumericValue(c);
      } else if(Character.isLetter(c) && num != 0){
        for(int j = 0; j < num; j++){
          sb.append(c);
        }
        num = 0;
      }
    }
    return sb.toString();
  }



}
