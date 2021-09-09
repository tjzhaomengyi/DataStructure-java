package com.mikemyzhao.slidewindows;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-04 10:56
 * @Description:字符串排列
 * @NO:LC567,判断S2字符串是否包含S1字符串的排列
 */
public class CheckInclustion {
  public boolean checkInclusion(String s1, String s2) {

    Map<Character,Integer> window = new HashMap<Character, Integer>();
    Map<Character,Integer> need = new HashMap<Character, Integer>();
    for (char c:s1.toCharArray()){need.put(c,need.getOrDefault(c,0)+1);}
    int left = 0,right=0;
    int start=0,len=Integer.MAX_VALUE;
    int valid =0;
    while(right<s2.length()){
      char c = s2.charAt(right);
      right++;
      if(need.containsKey(c)){
        window.put(c,window.getOrDefault(c,0)+1);
        if(window.get(c).equals(need.get(c))){
          valid++;
        }
      }
      //这个和最小字符串不同的是,这个要先进行左侧收缩，注意条件
      while(right-left>=s1.length()){
        if(valid==need.size()){
          return true;
        }
        //进行左指针收缩
        char d = s2.charAt(left);
        left++;
        if(need.containsKey(d)){
          if(window.get(d).equals(need.get(d))){
            valid--;
          }
          window.put(d,window.get(d)-1);
        }
      }
    }
    return false;
  }

  public static void main(String[] args) {
    System.out.println(new CheckInclustion().checkInclusion("ab","eidbaooo"));
  }
}

