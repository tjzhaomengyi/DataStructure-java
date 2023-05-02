package com.huaweiOD.od2023.s120e138;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-17 16:06
 * @Description:https://dream.blog.csdn.net/article/details/129191123
 */
public class NJinzhiJianfa {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str = in.nextLine();
      String[] input = str.split(" ");
      int numOcatl;
      try{
        numOcatl = Integer.valueOf(input[0]);
        if(numOcatl < 2 || numOcatl > 35){
          System.out.println(-1);
          return;
        }
      } catch (Exception e){
        System.out.println(-1);
        return;
      }
      String firstStr = input[1];
      String secondStr = input[2];
      //检查开头
      if(firstStr == null || secondStr == null || firstStr.length() == 0 || secondStr.length() == 0||
        firstStr.startsWith("0") || secondStr.startsWith("0")){
        System.out.println(-1);
        return;
      }

      //检查结尾
      if(firstStr.endsWith("\0")){
        firstStr = firstStr.substring(0, firstStr.length() - 2);
      }
      if(secondStr.endsWith("\0")){
        secondStr = secondStr.substring(0, secondStr.length() - 2);
      }
      int num1 = 0;
      int num2 = 0;
      try{
        num1 = Integer.parseInt(firstStr, numOcatl);
        num2 = Integer.parseInt(secondStr, numOcatl);
      } catch (Exception e){
        System.out.println(-1);
        return;
      }
      int res = num1 - num2;
      if(res > 0){
        System.out.print(0 + " ");
      } else {
        System.out.print(1 + " ");
      }
      System.out.println(Integer.toString(res, numOcatl));

    }
  }


}
