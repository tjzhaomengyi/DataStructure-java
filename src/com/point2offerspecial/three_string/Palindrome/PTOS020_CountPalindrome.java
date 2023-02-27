package com.point2offerspecial.three_string.Palindrome;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-03 18:01
 * @Description:
 */
public class PTOS020_CountPalindrome {

  //向外扩张
  //参考0647 可以使用manacher算法直接解
  public int countPalindrome(char[] s, int start, int end){
    int cnt = 0;
    while(start < end && start > 0 && end < s.length && s[start] == s[end]){
        cnt++;
        start--;
        end++;
    }
    return cnt;
  }
  public int countSubstrings(String s){
    if(s == null || s.length() == 0){
      return  0;
    }
    int count = 0;
    char[] str = s.toCharArray();
    for(int i = 0; i < s.length(); i++){
      count += countPalindrome(str, i, i);
      count += countPalindrome(str, i, i + 1);
    }
    return count;
  }
}
