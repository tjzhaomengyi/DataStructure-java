package com.mikemyzhao.slidewindows_6.bigshua;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-18 20:30
 * @Description:
 */
public class LC_0340_LongestSubstringWithAtMostKDistinctChar {
  public static int lengthOfLongestSubstringKDistinct(String s, int k) {
    if(s == null || s.length() == 0 || k < 1){
      return 0;
    }
    char[] str = s.toCharArray();
    int N = str.length;
    int[] count = new int[256];
    int diff = 0;
    int R = 0;
    int ans = 0;
    for(int i = 0; i < N; i++){
      //右侧窗口边界,技巧:R小于N，并且是diff个数小于K，或者diff等于k并且str[R]当前字符记录过
      while(R < N && (diff < k || (diff == k && count[str[R]] > 0))){
        diff += count[str[R]] == 0 ? 1 : 0;//如果没记录过说明diff又来了一个
        count[str[R++]]++;//当前的计数+1，并且R向后推
      }
      //技巧:R来到了违规的第一个位置，赶紧动L啊啊啊啊
      ans = Math.max(ans, R - i);
      diff -= count[str[i]] == 1 ? 1 : 0;//剩最后一个了，把diff统计的不同字符个数减掉1个
      count[str[i]]--;
    }
    return ans;
  }
}
