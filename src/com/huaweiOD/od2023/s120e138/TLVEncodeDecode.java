package com.huaweiOD.od2023.s120e138;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-17 16:28
 * @Description:https://dream.blog.csdn.net/article/details/129191146
 */
public class TLVEncodeDecode {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String tag = in.nextLine();
      String str = in.nextLine();
      Map<String, String> map = parseStream(str);
      String ans = map.get(tag);
      System.out.println(ans);
    }
  }

  public static HashMap<String, String> parseStream(String str){
    HashMap<String, String> map = new HashMap<>();
    String[] input = str.split(" ");
    int i = 0;
    while(i < input.length){
      String tagTemp = input[i];
      String lengStr = input[i + 2] + input[i + 1];//小端
      i = i + 3;

      int length = Integer.parseInt(lengStr, 16);
      StringBuilder sb = new StringBuilder();
      for(int j = i; j < i + length; j++){
        sb.append(input[j]).append(" ");
      }
      map.put(tagTemp, sb.toString());
      i = i + length;
    }
    return map;
  }
}
