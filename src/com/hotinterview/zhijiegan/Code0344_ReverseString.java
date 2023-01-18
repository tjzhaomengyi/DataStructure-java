package com.hotinterview.zhijiegan;

/**
 * @Author: zhaomengyi
 * @Date: 2022-12-20 6:28 下午
 * @Description:
 */
public class Code0344_ReverseString {
  public void reverseString(char[] s) {
    if(s == null || s.length == 0) return;
    int l = 0;
    int r = s.length - 1;
    while(l <= r){
      char tmp = s[l];
      s[l++] = s[r];
      s[r--] = tmp;
    }
  }
}
