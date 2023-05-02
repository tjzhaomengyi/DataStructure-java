package com.huaweiOD.od2023.s1e19;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-26 15:04
 * @Description:
 */
public class ZifuchuanPanding {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str = in.nextLine();
      String dict = in.nextLine();
      int last_index = findLash(str,dict);
      System.out.println(last_index);
    }
  }

  public static int findLash(String str, String dict){
    int index = 0;
    for(int i = 0; i < dict.length(); i++){
      if(str.charAt(index) == dict.charAt(i)){
        if(index == str.length() - 1){
          return i;
        }
        index++;
      }
    }
    return -1;
  }

}
