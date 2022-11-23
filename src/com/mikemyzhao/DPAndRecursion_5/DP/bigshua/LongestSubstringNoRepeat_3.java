package com.mikemyzhao.DPAndRecursion_5.DP.bigshua;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-15 12:00
 * @Description:
 */
public class LongestSubstringNoRepeat_3 {
  //解法：两个标准1)当前位置的字符和上一个该字符的位置，2)上一个最长无重复子序列的起始位置,看这两个位置哪个距离更近
  //这个动态规划体现在map[str[i]]上次字符串位置出现位置的重复使用，还有pre的长度的重复利用
  public static int lengthOfLongestNoRepeat(String s){
    if(s == null || s.length() == 0 || s.equals("")){
      return 0;
    }
    char[] str = s.toCharArray();//一个char是8位，所以一共256种形式
    //因为是字符，所以256个位代表每个字符,map的值表示该字符上次出现的位置
    int[] map = new int[256];
    for(int i = 0; i < 256; i++){
      map[i] = -1;
    }
    map[str[0]] = 0;
    int N = str.length;
    int ans = 1;
    int pre = 1;
    for(int i = 1; i<N; i++){
      pre = Math.min(i-map[str[i]],pre+1);
      ans = Math.max(ans,pre);
      map[str[i]] = i;
    }
    return ans;
  }
}
