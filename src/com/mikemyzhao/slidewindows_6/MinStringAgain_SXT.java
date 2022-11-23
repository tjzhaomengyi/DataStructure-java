package com.mikemyzhao.slidewindows_6;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-10 18:21
 * @Description:
 */
public class
MinStringAgain_SXT {
  //滑窗
  String minWindow(String s,String t) {
    Map<Character,Integer> need = new HashMap<>();//存储t中字符及个数
    Map<Character,Integer> window = new HashMap<>();//存储window中字符及葛素
    for(char c:t.toCharArray()){
      need.put(c,need.getOrDefault(c,0)+1);//把目标字符t放入ned中
    }
    int left=0,right=0;
    int valid = 0;//window窗中是否包含了need中的字符
    int start =0,len=Integer.MAX_VALUE;//存储最终结果的下标和长度

    while (right<s.length()){
      char c = s.charAt(right);
      right++;//右移动窗口
      if(need.containsKey(c)){//如果t包含当前字符，更新滑窗
        window.put(c,window.getOrDefault(c,0)+1);
        if(window.get(c).equals(need.get(c))){//need【t】和window中都有c字符并且个数一样，标志为+1
          valid++;
        }
      }
      //左侧进行窗口收缩
      while(valid==need.size()){//window中已经包含了needs【t】中的所有字符,移动左指针找最小子串
        if (right-left<len){
          start = left;
          len=right-left;
        }
        char remove = s.charAt(left);
        left++;
        //如果need中包含这个remove并且，window滑窗包含这个字符和need中包含这个字符数量相等，这样window刚好对应
        if(need.containsKey(remove)){
          if(window.get(remove).equals(need.get(remove))){
            valid--;
          }
          window.put(remove,window.get(remove)-1);//left收缩
        }
      }
    }
    return len==Integer.MAX_VALUE?"":s.substring(start,start+len);
  }

}
