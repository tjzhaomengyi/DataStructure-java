package com.huaweiOD.od2023.s40e59;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-21 14:01
 * @Description:
 */
public class ChazhaoChongfuDaima {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str1 = in.nextLine();
      String str2 = in.nextLine();
      String minStr = str1.length() <= str2.length() ? str1 : str2;
      String maxStr = minStr .equals(str1) ? str2 : str1;
      String result = "";
      for(int i = 0; i < minStr.length(); i++){
        for(int j = i + 1; j < minStr.length() + 1; j++){
          String tmp = minStr.substring(i, j);
          if(tmp.length() > result.length() && maxStr.contains(tmp)){
            result = tmp;
          }
        }
      }
      System.out.println(result);
    }
  }


}
