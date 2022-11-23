package com.mikemyzhao.slidewindows_6.bigshua;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-19 9:05
 * @Description:给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。
 * 返回这一子串的长度。
 */
public class LC_395_LongestSameKSubString {
  public int longestSubstring(String s, int k) {
    char[] str = s.toCharArray();
    int N = str.length;
    int max = 0;
    for(int require = 1; require <=26; require++){//技巧:子串中必须有require种字符有多长，一直枚举到26，过26遍
      int[] count = new int[26];
      int collectKind = 0;//技巧:变量1:窗口收集多少种字符
      int satisfy = 0;//技巧:变量2: 窗口中>=k的字符有几种
      int R = -1;
      for(int L = 0; L < N; L++) {//L尝试最左的位置
        //技巧:右窗口移动的条件(1)下一个位置 (2)【绝逼的踩点！！！】如果当前位置收集的种类到了要求种类，并且下一个还没统计过，这样肯定就超了，这样就不行！！！
        while (R + 1 < N && !(collectKind == require && count[str[R + 1] - 'a'] == 0)) { //技巧右侧指针
          R++;
          if(count[str[R] - 'a'] == 0){
            collectKind++;
          }
          if(count[str[R]- 'a'] == k - 1){
            satisfy++;
          }
          count[str[R] - 'a']++;//把当前[R]加上
        }
        if(satisfy == require){
          max = Math.max(max, R - L + 1);
        }//技巧:到这窗口的R边才他妈比现眼完事
        //L++
        if(count[str[L] - 'a'] == 1){
          collectKind--;
        }
        if(count[str[L] - 'a'] == k){
          satisfy--;
        }
        count[str[L] - 'a']--;
      }
    }
    return max;
  }
}
