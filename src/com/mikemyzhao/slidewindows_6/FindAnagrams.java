package com.mikemyzhao.slidewindows_6;

import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-04 11:42
 * @Description:找出所有字母异位词
 * @NO:LC438,从字符串s中返回开始下标能包含p中的异位词
 */
public class FindAnagrams {
  public List<Integer> findAnagrams(String s, String p){
    Map<Character,Integer> window = new HashMap<Character, Integer>();
    Map<Character,Integer> need = new HashMap<Character, Integer>();
    for(char c:p.toCharArray()){need.put(c,need.getOrDefault(c,0)+1);}
    int left=0,right=0;
    int valid=0;
    List<Integer> res = new LinkedList<>();

    while(right<s.length()){
      char c = s.charAt(right);
      right++;
      if(need.containsKey(c)){
        window.put(c,window.getOrDefault(c,0)+1);
        if(window.get(c).equals(need.get(c))){
          valid++;
        }
      }

      while(right-left>=p.length()){
        if(valid==need.size()){
          res.add(left);
        }
        char d = s.charAt(left);
        left++;
        if(need.containsKey(d)){
          if(window.get(d).equals(need.get(d))){
            valid--;
          }
          window.put(d,window.get(d)-1);
        }
      }
    }

    return res;
  }

  public static void main(String[] args) {
    System.out.println(new FindAnagrams().findAnagrams("cbaebabacd","abc"));
  }
}
