package com.huaweiOD.od2023.s40e59;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-21 19:41
 * @Description:
 */
public class Zifuchuanjiami {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      int n = Integer.valueOf(in.nextLine());
      for(int i = 0; i < n; i++) {
        String str = in.nextLine();
        char[] arr = str.toCharArray();
        String ans = jiami(arr);
        System.out.println(ans);
      }
    }
  }

  public static String jiami(char[] arr){
    int[] pianyi = new int[3];
    pianyi[0] = 1;
    pianyi[1] = 2;
    pianyi[2] = 4;
    for(int i = 0; i < arr.length; i++){
      char c = arr[i];
      int delta = 0;
      if(i < 3){
        delta = pianyi[i];
        c = (char)((c - 'a' + delta) % 26 + 'a');
        arr[i] = c;
      } else {
        delta = pianyi[0] + pianyi[1] + pianyi[2];
        pianyi[0] = pianyi[1];
        pianyi[1] = pianyi[2];
        pianyi[2] = delta;
        c = (char)((c - 'a' + delta) % 26 + 'a');
        arr[i] = c;
      }
    }
    return String.valueOf(arr);
  }


}
