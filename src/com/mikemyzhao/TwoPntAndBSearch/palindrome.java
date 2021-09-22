package com.mikemyzhao.TwoPntAndBSearch;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-14 12:54
 * @Description:双指正+递归实现回文查找
 */
public class palindrome {
  String palindrome(String s,int l,int r){
    while(l>=0 && r<s.length() && s.charAt(l) == s.charAt(r)){
      l--;r++;
    }
    return s.substring(l+1,r-l-1);
  }
  String longestPalindrome(String s){
    String res="";
    for(int i=0;i<s.length();i++){
      String s1 = palindrome(s,i,i);//长度为奇数
      String s2= palindrome(s,i,i+1);
      res = res.length()>s1.length()?res:s1;
      res = res.length()>s2.length()?res:s2;
    }
    return res;
  }
}
