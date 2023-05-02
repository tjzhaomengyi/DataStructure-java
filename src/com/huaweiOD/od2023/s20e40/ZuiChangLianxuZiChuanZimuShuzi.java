package com.huaweiOD.od2023.s20e40;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-25 12:19
 * @Description:
 */
public class ZuiChangLianxuZiChuanZimuShuzi {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNext()) {
      String str = in.next();
      int max = getMaxString(str);
      System.out.println(max);
    }
  }

  public static int getMaxString(String str){
    String tmp_letter = str.replaceAll("[A-Za-z]","");
    String tmp_dig = str.replaceAll("[0-9]","");
    if(tmp_letter.length() == 0 || tmp_dig.length() == 0){
      return -1;
    }
    char[] arr = str.toCharArray();
    int max = 0;
    for(int i = 0; i < arr.length; i++){
      char c = arr[i];
      int len = 1;
      boolean containLetter = Character.isLetter(c);
      for(int j = i + 1; j < arr.length; j++){
        if(containLetter && Character.isLetter(arr[j])){
          break;
        } else if(!containLetter && Character.isLetter(arr[j])){
          containLetter = true;
          len++;
        } else {
          len++;
        }
      }
      max = Math.max(len, max);
    }
    return max;
  }
}
