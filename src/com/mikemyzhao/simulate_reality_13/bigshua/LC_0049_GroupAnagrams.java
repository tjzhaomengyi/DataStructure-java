package com.mikemyzhao.simulate_reality_13.bigshua;

import com.MCAAlgorithm.base.class33.Hash;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-16 12:08
 * @Description:
 */
public class LC_0049_GroupAnagrams {
  public List<List<String>> groupAnagrams(String[] strs) {
    HashMap<String,List<String>> map = new HashMap<>();
    for(String str : strs){
      char[] chs = str.toCharArray();
      Arrays.sort(chs);
      String key = String.valueOf(chs);
      if(!map.containsKey(key)){
        map.put(key, new ArrayList<>());
      }
      map.get(key).add(str);
    }
    List<List<String>> res = new ArrayList<>();
    for(List<String> list : map.values()){
      res.add(list);
    }
    return res;
  }
}
