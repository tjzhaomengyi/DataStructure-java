package com.point2offerspecial.three_string.Palindrome;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-03 17:31
 * @Description:
 */
public class PTOS019_DelACharToPalindrome {
  public boolean validPalindrome(String s) {
    if(s == null || s.equals("") || s.length() == 0) return true;
    char[] str = s.toCharArray();
    int start = 0;
    int end = str.length - 1;
    for(; start < str.length / 2; start++, end--){
      if(str[start] != str[end]){
        break;
      }
    }
    return start == str.length / 2 || isValidPalindrom(str, start + 1, end) || isValidPalindrom(str, start, end - 1);
  }

  public boolean isValidPalindrom(char[] str, int start, int end){
    while(start < end){
      if(str[start] != str[end]){
        return false;
      } else {
        start++;
        end--;
      }
    }
    return true;
  }

}
