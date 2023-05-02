package com.huaweiOD.od2023.s100e119;


import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-18 17:51
 * @Description:
 */
public class JuzhenZuidaMianji {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      int len  = Integer.parseInt(in.nextLine());
      int sum = 0;
      for(int i = 0; i < len; i++){
        String[] strs = in.nextLine().split(",");
        StringBuilder stringBuilder = new StringBuilder();
        for(int j = 0; j < len; j++){
          stringBuilder.append(strs[j]);
        }
        sum += getMax(stringBuilder.toString(), len);
      }

      System.out.println(sum);
    }
  }


  public static int getMax(String s, int len){
    int max = Integer.parseInt(s, 2);
    for(int i = 0; i < len; i++){
      String tmpStr = s.substring(1)  + s.charAt(0);
      max = Math.max(max, Integer.valueOf(tmpStr, 2));
      s = tmpStr;
    }
    return max;
  }
}

