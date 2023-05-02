package com.huaweiOD.od2023.s100e119;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-18 13:43
 * @Description:
 */
public class RemoveLeastInString {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNext()) {
      String str = in.next();
      System.out.println(removeLeast(str));
    }
  }

  public static String removeLeast(String str){
    char[] chars = str.toCharArray();
    int[] dict = new int[26];
    int min = Integer.MAX_VALUE;
    for(char c : chars){
      dict[c - 'a']++;
    }
    StringBuilder sb = new StringBuilder("[");
    for(int i = 0; i < 26; i++){
      if(dict[i] > 0) {
        min = Math.min(min, dict[i]);
      }
    }
    for(int i = 0; i < 26; i++){
      if(dict[i] == min){
        sb.append((char)(i+'a'));
      }
    }
    sb.append("]");
    return str.replaceAll(sb.toString(), "");
  }


}
