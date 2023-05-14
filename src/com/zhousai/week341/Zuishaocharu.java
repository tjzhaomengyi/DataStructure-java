package com.zhousai.week341;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-22 10:56
 * @Description:
 */
public class Zuishaocharu {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()){
      String str = in.nextLine();
      System.out.println(addMinimum(str));
    }
  }


  public static int addMinimum(String word){
    List<Character> target = Arrays.asList('a','b','c');
    String tmp0 = word.replace("abc", ",");
    int cnt = 0;
    String tmp1 = tmp0.replaceAll("ab", ":")
        .replaceAll("ac",":")
        .replaceAll("bc",":");
    for(int i = 0; i < tmp1.length(); i++){
      if(tmp1.charAt(i) == ':'){
        cnt++;
      }
    }
    String tmp2 = tmp1.replace(":","");
    if(tmp2.length() == 0) return cnt;
    for(int i = 0; i < tmp2.length(); i++){
      if(target.contains(tmp2.charAt(i))){
        cnt += 2;
      }
    }

    return cnt;
  }
}
