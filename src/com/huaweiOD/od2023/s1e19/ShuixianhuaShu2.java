package com.huaweiOD.od2023.s1e19;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-26 16:35
 * @Description:
 */
public class ShuixianhuaShu2 {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNext()) {
      int cnt = 2;
      String str = in.nextLine();
      if(str == null || str.length() == 0){
        System.out.println("input error");
        return;
      }
      findData(str, cnt);
      int result = 0;
       if(1 < resultLinkList.size()){
         result =  - 1;
       } else if(resultLinkList.size() == 1){
         result = resultLinkList.get(0);
       }
       System.out.println(result);
    }
  }

  public static LinkedList<Integer> resultLinkList = new LinkedList<>();
  public static boolean isWaterNum(int num){
    int a = num % 10;
    int b = num / 10 % 10;
    int c = num / 100;
    return a * a * a + b * b * b + c * c + c == num;
  }
  public static int getStringAcsIISum(String s){
    int result = 0;
    for(char c : s.toCharArray()){
      result += c;
    }
    return result;
  }

  public static void findData(String vales, int number){
    String subStr1;
    String subStr2;
    for(int j = 1; j < vales.length(); j++){
      subStr1 = vales.substring(0, j);
      subStr2 = vales.substring(j);
      if(isWaterNum(getStringAcsIISum(subStr1))){
        if(isWaterNum(getStringAcsIISum(subStr2))){
          resultLinkList.add(number);
        } else {
          findData(subStr2, number++);
        }
      }
    }
  }
}
