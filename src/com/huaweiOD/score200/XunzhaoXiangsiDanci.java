package com.huaweiOD.score200;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-05-30 15:36
 * @Description:
 */
public class XunzhaoXiangsiDanci {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      int n = Integer.valueOf(in.nextLine());
      String[] arrs = new String[n];
      for(int i = 0; i < n; i++){
        arrs[i] = in.nextLine();
      }
      String check = in.nextLine();
      ArrayList<String> ans = new ArrayList<String>();
      for(int i = 0; i < arrs.length; i++){
        String s = arrs[i];
        if(isBroWords(s, check)){
          ans.add(s);
        }
      }
      if(ans.isEmpty()){
        System.out.println("null");
      } else {
        Collections.sort(ans);
        for(int i = 0; i < ans.size(); i++){
          System.out.print(ans.get(i) + " ");
        }
      }
    }


  }

  public static boolean isBroWords(String check, String s){
    if(check.length() != s.length()){
      return false;
    }
    char[] check_arr = check.toCharArray();
    char[] s_arr = s.toCharArray();

    Arrays.sort(check_arr);
    Arrays.sort(s_arr);
    for(int i = 0; i < check_arr.length; i++){
      if(check_arr[i] != s_arr[i]){
        return false;
      }
    }
    return true;
  }
}
