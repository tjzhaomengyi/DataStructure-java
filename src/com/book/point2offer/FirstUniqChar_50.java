package com.book.point2offer;

import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-08 22:38
 * @Description:第一个只出现一次的字符
 */
public class FirstUniqChar_50 {
  public char firstUniqChar(String s) {
    if(s.equals("")) return ' ';
    HashMap<Character,Boolean> tmp = new LinkedHashMap<>();
    for(Character c:s.toCharArray()){
      if(tmp.containsKey(c)) {
        tmp.put(c,true);
      }else{
        tmp.put(c,false);
      }
    }
    for(Map.Entry ele:tmp.entrySet()){
      if(ele.getValue().equals(false)){
        return (char) ele.getKey();
      }
    }
    return ' ';
  }
}
