package com.mikemyzhao.SomeSkills_0.tricks;

import java.util.HashMap;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-27 18:01
 * @Description:给定一个字符串返回其子序列有多少不同的字面值【子序列：以[i]结尾，前面的字符可有可无，但是序列顺序不能乱】
 */
public class StringSubsequnces_17 {
  public static int countSubsequnces(String s){
    if(s == null || s.length() == 0){
      return 0;
    }
    //每次没有出现过的字符时候，新子序列 = pre + 【pre，[i]】
    //每次有出现过的字符，新子序列个数 = pre + 【pre,[i]】 - 上次新加的即【上次的pre,p[i]】
    char[] str = s.toCharArray();
    HashMap<Character, Integer> map = new HashMap<>();
    int all = 1;//一个字符没有遍历的时候只有空集
    int m = 1000000007;//增加取模处理
    for(char x : str) {
      int curAll = all;
      int newAdd = all;//每次新加的都是上次算完的all
      curAll = (curAll + newAdd) % m;
      curAll = (curAll - (map.containsKey(x) ? map.get(x) : 0) + m) % m;
      all = curAll;
      map.put(x, newAdd);
    }
    return all;//lc要求不带空集
  }
}
