package com.huaweiOD.od2023.s80e99;


import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-18 19:54
 * @Description:
 */
public class MaxStringInteger {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str = in.nextLine();
      StringBuilder sb = new StringBuilder();
      String[] arr = str.split(" ");
      for(String s : arr){
        sb.append(s);
      }
      char[] tmp = sb.toString().toCharArray();
      Arrays.sort(tmp);
      String anstmp = String.valueOf(tmp);
      System.out.print(new StringBuilder(anstmp).reverse().toString());
    }
  }
}
