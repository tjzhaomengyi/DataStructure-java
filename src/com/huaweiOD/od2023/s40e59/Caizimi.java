package com.huaweiOD.od2023.s40e59;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-24 11:11
 * @Description:
 */
public class Caizimi {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String[] words = in.nextLine().split(",");
      String[] dicts = in.nextLine().split(",");
      List<String> ans = new ArrayList<>();
      for(String word : words){
        boolean isExist = false;
        for(String dict : dicts){
          if(isMatch(word, dict)){
            ans.add(dict);
            isExist = true;
            break;
          }
        }
        if(!isExist){
          ans.add("not found");
        }
      }
      System.out.println(String.join(",", ans));
    }
  }

  //这么做起始不对
  public static boolean isMatch(String word, String dict){
    //技巧:正则表达式，正则表达式中\\，第一个\表示这里要开始变成正则表达的含义了，第二个\1+,在正则表达式中\num,匹配num，num是一个正整数，对所获取的匹配的引用。
    // (。)\1表示匹配两个连续的相同的字符
    Pattern pattern = Pattern.compile("(.)\\1+");//对查找到连续出现进行替换，替换成连续的自己
    Matcher matcher = pattern.matcher(dict);
    StringBuffer sb = new StringBuffer();
    while(matcher.find()){
      String repeat = matcher.group(1);
      matcher = matcher.appendReplacement(sb, "(" + repeat + ")");
    }
    matcher.appendTail(sb);
    System.out.println(sb.toString());
    if(word.equals(dict)){
      return true;
    } else {
      Set<Character> wordSet = new HashSet<>();
      Set<Character> dictSet = new HashSet<>();
      for(char c: word.toCharArray()){
        wordSet.add(c);
      }
      for(char c : dict.toCharArray()){
        dictSet.add(c);
      }
      return wordSet.equals(dictSet);
    }
  }

}
