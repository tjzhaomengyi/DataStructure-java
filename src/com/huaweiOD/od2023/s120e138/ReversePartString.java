package com.huaweiOD.od2023.s120e138;


import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-17 9:43
 * @Description:
 */
public class ReversePartString {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str = in.nextLine();
      String start = in.nextLine();
      String end = in.nextLine();
      System.out.println(reverse(str, Integer.parseInt(start), Integer.parseInt(end)));
    }
  }

  public static String reverse(String str, int start, int end){
    String[] content = str.split(" ");
    while(end > start){
      String temp = "";
      temp = content[end];
      content[end] = content[start];
      content[start] = temp;
      start++;
      end--;
    }
    StringBuilder stringBuilder = new StringBuilder();
    for(int i = 0; i < content.length; i++) {
      stringBuilder.append(content[i]);
      if (i + 1 < content.length) {
        stringBuilder.append(" ");
      }
    }
    return stringBuilder.toString();
  }
}
