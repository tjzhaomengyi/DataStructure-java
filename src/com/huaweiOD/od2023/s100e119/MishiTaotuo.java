package com.huaweiOD.od2023.s100e119;

import java.util.Scanner;
import java.util.TreeSet;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-18 10:06
 * @Description:
 */
public class MishiTaotuo {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String key = in.nextLine();
      String input = in.nextLine();
      System.out.println(getAns(key, input));
    }
  }


  public static int getAns(String key, String input) {
    if (key == null || input == null) {
      return -1;
    }
    String[] strs = input.substring(2).split(" ");
    for(int i = 0; i < strs.length; i++){
      String low = strs[i].toLowerCase();
      TreeSet<Character> charTreeset = new TreeSet<>();
      for(char c : low.toCharArray()){
        if(c <= 'z' && c >= 'a'){
          charTreeset.add(c);
        }
      }
      if(key.length() == charTreeset.size()){
        StringBuilder sb = new StringBuilder();
        for(Character cha : charTreeset){
          sb.append(cha);
        }
        if(key.equals(sb.toString())){
          return i ;
        }
      }
    }
    return -1;
  }



}


