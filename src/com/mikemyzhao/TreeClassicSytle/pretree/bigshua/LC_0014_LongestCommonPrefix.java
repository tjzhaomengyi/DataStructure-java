package com.mikemyzhao.TreeClassicSytle.pretree.bigshua;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-15 16:06
 * @Description:
 */
public class LC_0014_LongestCommonPrefix {
  public String longestCommonPrefix(String[] strs) {
    if(strs == null || strs.length == 0){
      return "";
    }
    char[] chs = strs[0].toCharArray();
    int min = Integer.MAX_VALUE;
    for(String str : strs){
      char[] tmp = str.toCharArray();
      int index = 0;
      while(index < tmp.length && index < chs.length) {
        if (chs[index] != tmp[index]) {
          break;
        }
        index++;
      }
        min = Math.min(index, min);

    }
    if(min == 0){
      return "";
    }

    return strs[0].substring(0,min);
  }
}
