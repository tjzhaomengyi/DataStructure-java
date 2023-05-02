package com.huaweiOD.od2023.s20e40;

import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-24 16:06
 * @Description:
 */
public class YinwenShurufa {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str = in.nextLine();
      String[] splits = str.split(" ");
      String input = in.nextLine();
      List<String> ans = getXiangjin(splits, input);
      if(ans.size() == 0){
        System.out.println(input);
      } else {
        for(int i = 0; i < ans.size(); i++){
          System.out.print(ans.get(i) + " ");
        }
      }
      System.out.println();
    }
  }

  public static ArrayList<String> getXiangjin(String[] splits, String input){
    ArrayList<String> ans = new ArrayList<String>();
    for(String str : splits){
      if(str.startsWith(input)){
        ans.add(str);
      }
    }
    Collections.sort(ans);
    return ans;
  }
}
