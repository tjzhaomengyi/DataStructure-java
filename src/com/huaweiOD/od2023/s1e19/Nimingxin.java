package com.huaweiOD.od2023.s1e19;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-26 10:47
 * @Description:
 */
public class Nimingxin {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String[] dict = in.nextLine().split(" ");
      String[] target = in.nextLine().split(" ");
      boolean ans = true;
      List<String> dictList = makeDict(dict);
      for(int i = 0; i < target.length; i++){
        char[] word = target[i].toCharArray();
        Arrays.sort(word);
        if(!dictList.contains(String.valueOf(word))){
          ans = false;
          break;
        }
      }
      System.out.println(ans);
    }
  }

  public static List<String> makeDict(String[] dict) {
    ArrayList<String> lst = new ArrayList<String>();
    for(int i = 0; i < dict.length; i++){
      char[] word = dict[i].toCharArray();
      Arrays.sort(word);
      lst.add(String.valueOf(word));
    }
    return lst;
  }

}
