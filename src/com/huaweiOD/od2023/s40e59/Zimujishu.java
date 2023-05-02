package com.huaweiOD.od2023.s40e59;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-21 18:32
 * @Description:
 */
public class Zimujishu {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str = in.nextLine();
      int[] big = new int[26];
      int[] small = new int[26];
      for(char c : str.toCharArray()){
        if(Character.isLowerCase(c)){
          small[(int)(c - 'a')]++;
        }else if(Character.isUpperCase(c)){
          big[(int)(c - 'A')]++;
        }
      }
      for(int i = 0; i < 26; i++){
        if(small[i] > 0){
          System.out.print((char)(i + 'a') + ":" +small[i] + ";");
        }
      }
      for(int i = 0; i < 26; i++){
        if(big[i] > 0){
          System.out.print((char)(i + 'A') + ":" + big[i] + ";");
        }
      }
      System.out.println();
    }

  }
}
