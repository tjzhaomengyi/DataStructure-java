package com.huaweiOD.od2023.s100e119;

import java.util.Iterator;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-18 18:13
 * @Description:
 */
public class DNA {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str = in.nextLine();
      int len = Integer.parseInt(in.nextLine());
      solveMethod(str, len);
    }
  }


  public static void solveMethod(String input, int len){
    double max = 0.0;
    String res = "";
    for(int i = 0 ; i < input.length() - len + 1; i++){
      String str1 = input.substring(i, len + i);
      String str2 = str1.replaceAll("[^CG]","");
      double x = str2.length() * 1.0 / str1.length();
      if(x > max){
        res = str1;
        max = x;
        if(x == 1.0){
          break;
        }
      }
    }
    System.out.print(res);
  }

}
