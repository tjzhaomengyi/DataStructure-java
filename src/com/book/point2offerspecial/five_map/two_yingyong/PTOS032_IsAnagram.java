package com.book.point2offerspecial.five_map.two_yingyong;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-06 21:42
 * @Description:
 */
public class PTOS032_IsAnagram {
  public boolean isAnagram(String s, String t) {
    if(s == null || t == null || s.length() != t.length() || s.equals(t)) return false;
    int[] dict = new int[26];
    char[] str1 = s.toCharArray();
    char[] str2 = t.toCharArray();
    for(int i = 0 ; i < str1.length; i++){
      dict[str1[i] - 'a']++;
    }
    for(int i = 0; i < str2.length; i++){
      dict[str2[i] - 'a']--;
      if(dict[str2[i]] < 0) return false;
    }
    for(int i = 0; i < dict.length; i++){
      if(dict[i] > 0) return false;
    }
    return true;
  }
}
