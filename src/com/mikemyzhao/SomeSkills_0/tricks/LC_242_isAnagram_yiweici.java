package com.mikemyzhao.SomeSkills_0.tricks;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-18 13:48
 * @Description:
 */
public class LC_242_isAnagram_yiweici {
  public boolean isAnagram(String s, String t) {
    if(s.length() != t.length()){
      return false;
    }
    char[] s1 = s.toCharArray();
    char[] s2 = t.toCharArray();
    int[] cnt = new int[256];
    for(char c : s1){
      cnt[c]++;
    }
    for(char c : s2){
      cnt[c]--;
      if(cnt[c] < 0){
        return false;
      }
    }
    return true;
  }
}
