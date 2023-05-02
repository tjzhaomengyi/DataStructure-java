package com.huaweiOD.od2023.s120e138;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-17 13:58
 * @Description:
 */
public class StringSplit {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str = in.nextLine();
      int partLen = Integer.valueOf(in.nextLine());
      System.out.println(changeStr(str, partLen));
    }
  }

  public static String changeStr(String str, int k){
    String[] arr = str.split("-");
    StringBuffer newStrSb = new StringBuffer();
    newStrSb.append(arr[0]);

    StringBuffer sb  = new StringBuffer();
    for(int i = 1; i < arr.length; i++){
      sb.append(arr[i]);
    }
    int left = 0;
    int right = left + k;
    while(right < sb.length()){
      String subString = sb.substring(left, right);
      String s = countUpCase(subString.toString(), k);
      newStrSb.append("-").append(s);
      left += k;
      right = left + k;
    }
    if(left < sb.length()){
      newStrSb.append("-").append(sb.substring(left));
    }
    return newStrSb.toString();
  }

  public static String countUpCase(String str, int k){
    int letter = 0;
    int capital = 0;
    for(int i = 0; i < str.length(); i++){
      if(str.charAt(i) <= 'z' && str.charAt(i) >= 'a'){
        letter++;
      }
      if(str.charAt(i) <= 'Z' && str.charAt(i) >= 'A'){
        capital++;
      }
    }
    if(letter == capital){
      return str;
    } else if(letter > k / 2){
      return str.toLowerCase();
    } else {
      return str.toUpperCase();
    }
  }


}
