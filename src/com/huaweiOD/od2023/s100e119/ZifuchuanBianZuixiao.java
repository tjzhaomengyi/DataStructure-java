package com.huaweiOD.od2023.s100e119;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-18 19:19
 * @Description:
 */
public class ZifuchuanBianZuixiao {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str = in.nextLine();
      char[] chars = str.toCharArray();
      System.out.println(makeString(chars));
    }
  }

  public static String makeString(char[] chars){
    int index = 0;
    int minChar = Integer.valueOf(chars[0]);
    for(int i = 0; i < chars.length; i++){
      int charValue = Integer.valueOf(chars[i]);
      if(charValue <= minChar){
        minChar = charValue;
        index = i;
      }
    }
    if(index == 0){
      return String.valueOf(chars);
    } else {
      char tmp = chars[index];
      chars[index] = chars[0];
      chars[0] = tmp;
      return String.valueOf(chars);
    }
  }
}
