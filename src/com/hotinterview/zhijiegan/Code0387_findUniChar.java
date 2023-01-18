package com.hotinterview.zhijiegan;

import java.util.HashMap;
import java.util.stream.Stream;

/**
 * @Author: zhaomengyi
 * @Date: 2022-12-20 12:16 下午
 * @Description:
 */
public class Code0387_findUniChar {
  //找到第一个不重复的字符
  public int firstUniqChar(String s) {
    char[] str = s.toCharArray();
    HashMap<Character,Integer> tmp = new HashMap<>();
    int[] map = new int[26];
    for(int i = 0; i < str.length; i++){
      if(tmp.keySet().contains(str[i])){
        tmp.put(str[i],-1);
      } else {
        tmp.put(str[i], i);
      }
    }
    Object[] res = (Object[]) tmp.values().stream().filter(x -> x > -1).sorted(((o1, o2) -> o1 - o2)).toArray();
    return res.length > 0 ? (int)res[0] : -1;
  }
}
