package com.huaweiOD.od2023.s60e79;


import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-20 19:40
 * @Description:
 */
public class YasuoBaowenZhengzeFangfa {

  public static final Pattern PATTERN = Pattern.compile("[0-9]+\\[[a-z]+]"); //匹配“数字[字母]”的格式

  public static String solve(String input){
    if(input == null || "".equals(input)){
      return "";
    }
    Matcher matcher = PATTERN.matcher(input);
    if(!matcher.find()){
      return input;
    }

    String group = matcher.group();//找到符合条件的部分
    int pos = group.indexOf("[");//找到符合条件的第一个
    int counts = Integer.parseInt(group.substring(0, pos));
    String words = group.substring(pos + 1, group.length() - 1);
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < counts; i++){
      sb.append(words);
    }
    String fixed = input.replace(group, sb.toString());
    return solve(fixed);
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String input = in.nextLine();
      System.out.println(solve(input));
    }
  }
}
