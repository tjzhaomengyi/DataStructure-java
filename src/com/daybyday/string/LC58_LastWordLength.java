package com.daybyday.string;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-28 18:30
 * @Description:
 */
public class LC58_LastWordLength {
  public int lengthOfLastWord(String s) {
    char[] str = s.toCharArray();
    int len = str.length;
    int l = 0;
    while(l == ' '){
      l++;
    }
    int start = l;
    int r = len - 1;
    while(l <= r){
      if(str[r] == ' ') {
        r--;
      }
      if(str[l] == ' '){
        if(str[l + 1] != ' '){
          start = l + 1;
        }
      }
      l++;
    }
    int ans = r - start + 1;
    return ans == 1 && str[r] == ' ' ? 0 : ans;
  }
}

