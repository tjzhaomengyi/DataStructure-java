package com.zhousai.zhousai346;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-05-21 10:58
 * @Description:
 */
public class ZidianxuzuixiaoHuiwen {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str = in.nextLine();
      String ans = makeSmallestPalindrome(str);
      System.out.println(ans);
    }
  }
  public static String makeSmallestPalindrome(String s) {
    int L = 0;
    int R = s.length() - 1;
    int cnt = 0;
    char[] ans = new char[s.length()];
    while(L <= R){
      char lc = s.charAt(L);
      char rc = s.charAt(R);
      if(lc != rc){
        if(lc > rc){
          ans[L] = rc;
          ans[R] = rc;
        } else {
          ans[L] = lc;
          ans[R] = lc;
        }
      } else {
        ans[L] = lc;
        ans[R] = rc;
      }
      L++;R--;
    }
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < ans.length; i++){
      sb.append(ans[i]);
    }
    return sb.toString();
  }
}
