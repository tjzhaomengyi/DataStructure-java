package com.point2offer;

import java.util.HashMap;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-08 22:08
 * @Description:最长字符串长度，LC3
 */
public class LengthOfLongestSubString_48 {
  public int lengthOfLongestSubstring(String s) {
    //到left++全是模板
    HashMap<Character,Integer> window = new HashMap<>();
    int left =0,right=0;
    int res =0;
    while(right<s.length()){
      char c = s.charAt(right);
      right++;
      window.put(c,window.getOrDefault(c,0)+1);
      while(window.get(c)>1){//只要大于1收缩左侧
        char d = s.charAt(left);
        left++;
        window.put(d,window.get(d)-1);
      }
      res = Integer.max(res,right-left);
    }
    return res;
  }

  public static void main(String[] args) {
    System.out.println(new LengthOfLongestSubString_48().lengthOfLongestSubstring("abcabcbb"));
  }

}
