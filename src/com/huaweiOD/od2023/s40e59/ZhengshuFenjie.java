package com.huaweiOD.od2023.s40e59;

import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-21 16:53
 * @Description:
 */
public class ZhengshuFenjie {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      int str = Integer.valueOf(in.nextLine());
      List<String> ans = fenjie(str);
      Collections.sort(ans,(a, b) -> a.length() - b.length());
      for(int i = 0; i < ans.size(); i++){
        System.out.println(ans.get(i));
      }
      System.out.println("Result:" + ans.size());
    }
  }

  public static List<String> fenjie(int num){
    List<String> lst = new ArrayList<String>();
    lst.add(num+"="+num);
    for(int i = 1; i <= num; i++){
      for(int j = i + 1; j <= num; j++){
        int sum = (i + j) * (j - i + 1) / 2;
        if(sum > num){
          break;
        }else if(((i + j) * (j - i + 1) % 2) == 0 && sum == num){
          String ans = String.valueOf(num) + "=";
          for(int a = i; a <= j; a++){
            ans += String.valueOf(a) + "+";
          }
          lst.add(ans.substring(0,ans.length() - 1));
        }
      }
    }
    return lst;
  }
}
