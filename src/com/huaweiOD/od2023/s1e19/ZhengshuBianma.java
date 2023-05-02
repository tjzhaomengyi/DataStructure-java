package com.huaweiOD.od2023.s1e19;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-26 11:15
 * @Description:
 */
public class ZhengshuBianma {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNext()) {
      int num = in.nextInt();
      String ans = solve(num);
      System.out.println(ans);
    }
  }


  public static String solve(int num){
    String numStr = Integer.toBinaryString(num);
    boolean stau;
    StringBuilder sb = new StringBuilder();
    while(numStr.length() > 0){
      if(numStr.length() > 7){
        stau = true;//后面有7个字节
      } else {
        stau = false;
        int midIdx = 7 - numStr.length();
        for(int i = 0; i < midIdx; i++){
          numStr = "0" + numStr;
        }
      }
      String subStr = numStr.substring(numStr.length() - 7);
      numStr = numStr.substring(0, numStr.length() - 7);
      if(stau){
        subStr = "1" + subStr;
      } else{
        subStr = "0" + subStr;
      }
      String resultStr = Integer.toHexString(Integer.parseInt(subStr, 2));
      if(resultStr.length() < 2){
        resultStr = "0" + resultStr;
      }
      for(int i = 0; i < resultStr.length(); i++){
        char c = resultStr.charAt(i);
        if(c <= 'z' && c >= 'a'){
          c = (char)(c - 'a' + 'A');
        }
        sb.append(c);
      }
    }
   return sb.toString();
  }

}
