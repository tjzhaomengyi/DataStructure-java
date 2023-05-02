package com.huaweiOD.od2023.s60e79;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-20 16:46
 * @Description:
 */
public class DancJielong {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      int start = Integer.parseInt(in.nextLine());
      int n = Integer.parseInt(in.nextLine());
      if(n <= 1 || n >= 20){
        System.out.println("data error");
      }
      ArrayList<String> wordList = new ArrayList<>();
      for(int i = 0; i < n; i++){
        wordList.add(in.nextLine());
      }
      StringBuilder sb = new StringBuilder();
      sb.append(wordList.get(start));
      wordList.remove(start);

      //排序,这么做就是左右解，把长度长的，字典序小的放在前面
      wordList.sort((word1, word2) ->{
        int len1 = word1.length();
        int len2 = word2.length();
        if(len1 != len2){
          return len2 - len1;
        } else {
          return word1.compareTo(word2);
        }
      });

      int length = 0;
      do {
        length = sb.length();
        String last = sb.substring(sb.length() - 1);
        for (int i = 0; i < wordList.size(); i++) {
          String value = wordList.get(i);
          if (value.startsWith(last)) {
            sb.append(value);
            wordList.remove(value);
            break;
          }
        }
      } while (length != sb.length());//技巧:如果往sb中添加了元素，说明还需要继续找后面的元素接着执行
      System.out.println(sb);
    }
  }



}
