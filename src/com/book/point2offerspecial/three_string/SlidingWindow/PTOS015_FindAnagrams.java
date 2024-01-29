package com.book.point2offerspecial.three_string.SlidingWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2023-01-28 20:47
 * @Description:
 */
public class PTOS015_FindAnagrams {

  //PTOS014的衍深题目
  public List<Integer> findAnagrams(String s, String p){
    List<Integer> ans = new ArrayList<>();
    if(s == null || p == null || s.length() < p.length()) return ans;
    char[] str2 = p.toCharArray();
    int M = str2.length;
    int[] dict = new int[26];
    for(int i = 0; i < M; i++){
      dict[str2[i] - 'a']++;
    }
    int all = M;
    char[] str1 = s.toCharArray();
    int R = 0;
    for(; R < M; R++){
      if(dict[str1[R] - 'a']-- > 0){ //dict中有这个字符，all补上
        all--;
      }
    }
    for(; R < str1.length; R++){
      if(all == 0){
        ans.add(R - str2.length);
      }
      //窗口右侧移动
      if(dict[str1[R] - 'a']-- > 0){
        all--;
      }
      if(dict[str1[R - M] - 'a']++ >= 0){
        all++;
      }
    }
    //注意最后一个也要判断一下
    if(all == 0) ans.add(R - str2.length);
    return ans;
  }
}
