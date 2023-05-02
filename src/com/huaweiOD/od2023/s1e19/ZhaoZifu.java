package com.huaweiOD.od2023.s1e19;

import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-26 14:41
 * @Description:
 */
public class ZhaoZifu {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str = in.nextLine();
      String dictStr = in.nextLine();
      char[] strChars = str.toCharArray();
      Arrays.sort(strChars);
      char[] dictChars = dictStr.toCharArray();
      Set<Character> set = new HashSet<>();
      for(char c : dictChars){
        set.add(c);
      }

      Set<Character> resultSet = new TreeSet<>();
      for(char c :strChars){
        if(set.contains(c)){
          resultSet.add(c);
        }
      }

      for(char c : resultSet){
        System.out.print(c);
      }
      System.out.println();
    }
  }


}
