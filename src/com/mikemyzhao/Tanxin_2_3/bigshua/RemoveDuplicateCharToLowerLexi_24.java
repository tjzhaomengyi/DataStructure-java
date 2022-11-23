package com.mikemyzhao.Tanxin_2_3.bigshua;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-07 11:36
 * @Description:字符串每种字符值保留一个，让字符串的字典序最小
 */
public class RemoveDuplicateCharToLowerLexi_24 {
  //技巧：纯贪心。如果保留当前的字符，左侧的字符串全不要，当前位置就是前面字符串结束位置的后一个位置。但是这里面也有一个还账表。就是每个字符出现个数
  // 如果个数为0，就考虑在这部分位置选1个位0的字符开始，然后往后面选，继续第一个过程
  public static String removeDuplicateLetter(String s) {
    if(s == null || s.length() < 2){
      return s;
    }
    int map[] = new int[26];
    char[] str = s.toCharArray();
    for (int i = 0; i < str.length; i++) {
      map[str[i] - 'a']++;
    }
    int minACSIndex = 0;
    for (int i = 0; i < str.length; i++) {
      minACSIndex = str[minACSIndex] > str[i] ? i : minACSIndex;//如果当前位置的ascii码小，并且统计个数为0，把这个位置拿出来，
      if (--map[str[i] - 'a'] == 0) {
        break;
      }
    }
    String nextString = s.substring(minACSIndex + 1).replaceAll(String.valueOf(str[minACSIndex]), "");
    return String.valueOf(str[minACSIndex]) + removeDuplicateLetter(nextString);


  }

}
