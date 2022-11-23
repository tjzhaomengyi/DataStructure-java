package com.mikemyzhao.slidewindows_6.bigshua;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-19 12:40
 * @Description:
 */
public class LC_0438_AllYiWeiCiStart {
  public static List<Integer> findAnagrams(String s, String p) {
    List<Integer> ans = new ArrayList<>();
    if(s == null || p == null || s.length() < p.length()){
      return ans;
    }
    char[] str = s.toCharArray();
    int N = str.length;
    char[] pst = p.toCharArray();
    int M = pst.length;
    HashMap<Character, Integer> map = new HashMap<>();
    for(char cha : pst){
      if(!map.containsKey(cha)){
        map.put(cha, 1);
      } else {
        map.put(cha, map.get(cha) + 1);
      }
    }
    int all = M;
    for(int end = 0; end < M - 1; end++){
      if(map.containsKey(str[end])){
        int count = map.get(str[end]);
        if(count > 0){
          all--;
        }
        map.put(str[end], count - 1);
      }
    }
    for(int end = M - 1, start = 0; end < N; end++, start++){
      if(map.containsKey(str[end])){
        int count = map.get(str[end]);
        if(count > 0){
          all--;
        }
        map.put(str[end], count - 1);
      }
      if(all == 0){
        ans.add(start);
      }
      if(map.containsKey(str[start])){
        int count = map.get(str[start]);
        if(count >= 0 ){
          all++;
        }
        map.put(str[start], count + 1);
      }
    }
    return ans;
  }

}
