package com.mikemyzhao.slidewindows_6;

import java.util.HashMap;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-04 12:24
 * @Description:找出字符串中,无重复的最长子串
 * @NO:LC3,直接用一个滑动窗口就行，不用记录比较了，因为就一个字符串
 */
public class LengthOfLongestSubstring {
  public int lengthOfLongestSubstring(String s) {
    HashMap<Character,Integer> window = new HashMap<Character, Integer>();
    int left = 0,right = 0;
    int res = 0;
    while(right<s.length()){
      char c = s.charAt(right);
      right++;
      window.put(c,window.getOrDefault(c,0)+1);
      while(window.get(c)>1){ //如果窗口中这个c出现了1次以上，说明要收缩窗口了
        char d = s.charAt(left);
        left++;
        window.put(d,window.get(d)-1);
      }
      res = Integer.max(res,right-left);
    }
    return res;
  }
}
