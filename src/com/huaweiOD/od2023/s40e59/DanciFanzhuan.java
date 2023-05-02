package com.huaweiOD.od2023.s40e59;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-21 14:59
 * @Description:
 */
public class DanciFanzhuan {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str = in.nextLine();
      String[] lines = str.split(" ");
      int start = Integer.parseInt(in.nextLine());
      int end = Integer.parseInt(in.nextLine());
      String[] ans = new String[lines.length];
      int len = lines.length;
      int tag = start;
      for(int i = 0; i < lines.length; i++){
        if(i >= start && i < end){
          ans[len - 1 - i] = lines[i];
        } else if(i < start){
          ans[i] = lines[i];
        } else if(i >= end){
          ans[tag++] = lines[i];
        }
      }
      StringBuilder tmp = new StringBuilder();
      for(String a : ans){
        tmp.append(a+ " ");
      }
      System.out.println(tmp.toString());

    }
  }
}
