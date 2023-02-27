package com.point2offerspecial.five_map.two_yingyong;

import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-06 22:36
 * @Description:
 */
public class PTOS033_groupAnagrams {
  public List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>>  dict = new HashMap<>();
    for(String str : strs){
      char[] tmp = str.toCharArray();
      Arrays.sort(tmp);
      String key = String.valueOf(tmp);
      if(!dict.containsKey(key)){
        dict.put(key, new ArrayList<>());
      }
      dict.get(key).add(str);

    }
    List<List<String>> res = new ArrayList<>();
    for(List<String> list : dict.values()){
      res.add(list);
    }
    return res;
  }
}
