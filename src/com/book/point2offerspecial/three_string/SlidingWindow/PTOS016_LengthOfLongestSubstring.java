package com.book.point2offerspecial.three_string.SlidingWindow;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-01 9:12 上午
 * @Description:
 */
public class PTOS016_LengthOfLongestSubstring {
  //解法1：【第一反应】使用滑动窗口
  public int lengthOfLongestSubstring(String s) {
    if(s == null || s.length() == 0 || s.equals("")) return 0;
    char[] str = s.toCharArray();
    int[] map = new int[256];//一个字符是8位，所以是256
    int l = 0, r = 0;
    int res = 1;
    while(r < str.length){
      char c = str[r];
      map[c]++;
      r++;
      while(map[c] > 1){
        char d = str[l];
        l++;
        map[d]--;
      }
      res = Integer.max(res, r - l);
    }
    return res;
  }
  //解法2：使用DP动态规划，
  // 解法：两个标准1)当前位置的字符和上一个该字符的位置，2)上一个最长无重复子序列的起始位置,看这两个位置哪个距离更近
  //这个动态规划体现在map[str[i]]上次字符串位置出现位置的重复使用，还有pre的长度的重复利用
  public static void main(String[] args) {
    int ans = new PTOS016_LengthOfLongestSubstring().lengthOfLongestSubstring("abcabcbb");
    System.out.println(ans);
  }
}
