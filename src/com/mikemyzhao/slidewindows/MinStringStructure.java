package com.mikemyzhao.slidewindows;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-04 10:10
 * @Description:求最小字符串无注释版本，看到滑动窗口整体对称框架
 */
public class MinStringStructure {
  public String minWindow(String s,String t){
    Map<Character,Integer> window = new HashMap<Character, Integer>();
    Map<Character,Integer> need = new HashMap<Character, Integer>();
    for(char c : t.toCharArray()){need.put(c,need.getOrDefault(c,0)+1);}
    int left =0,right=0;
    int valid =0;
    int start=0,len=Integer.MAX_VALUE;
    while(right<s.length()){
      char c = s.charAt(right);
      right++;
      if(need.containsKey(c)){
        window.put(c,window.getOrDefault(c,0)+1);
        if(window.get(c).equals(need.get(c))){
          valid++;
        }
      }
      while(valid==need.size()){
        if(right-left<len){
          len = right-left;
          start = left;
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

    return len==Integer.MAX_VALUE?"":s.substring(start,start+len);
  }

  public static void main(String[] args) {
    System.out.println(new MinStringStructure().minWindow("ADOBECODEBANC","ABC"));
  }
}
