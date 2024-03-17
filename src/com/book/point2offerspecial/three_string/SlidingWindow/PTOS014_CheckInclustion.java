package com.book.point2offerspecial.three_string.SlidingWindow;

/**
 * @Author: zhaomengyi
 * @Date: 2023-01-28 11:07
 * @Description: 和ContainAllCharExtratly_12一样
 */
public class PTOS014_CheckInclustion {
  public boolean checkInclusion(String s1, String s2) {
    return  containExactly(s2, s1);
  }
  public boolean containExactly(String s1, String s2) {
    //s2是短的
    if(s2 == null || s1 == null || s1.length() < s2.length()) return false;

    char[] str2 = s2.toCharArray();
    int M = str2.length;
    int[] dict = new int[26];
    for(int i = 0; i < M; i++){
      dict[str2[i] - 'a']++;
    }
    int all = M;
    char[] str1 = s1.toCharArray();
    int R = 0;//窗口右侧
    for(; R < M; R++){//在s2的长度上，鼓捣一下R1，
      if(dict[str1[R] - 'a']-- > 0){ //如果R1有这个字符就减掉all
        all--;
      }
    }
    //技巧:先比较大小(><)再计算加加/减减(--)
    for(; R < str1.length; R++){//这个时候R已经走了R2的长度了，此时看下前面是否满足即all==0。
      //站在[R]位置上看前面是否达标，满足要求
      if(all == 0){
        return true;
      }
      //后面是all不等于0的情况，保证每次循环右边进一个，左边吐一个
      if(dict[str1[R] - 'a']-- > 0){ //此时all还没等于0，并且窗口右侧扩展正好还能满足R2中的字符
        //这里不管什么情况str1[R]中的字符都要--，所以一旦没在dict中，这个位置就为负数了！！！
        all--;
      }
      if(dict[str1[R - M] - 'a']++ >= 0){//吐掉窗口左侧的元素，这个条件记住把
        //这里不管什么情况窗口左侧元素吐出去一定要++
        all++;
      }
    }
    return all == 0 ? true : false;//最后也要潘丹
  }

  public static void main(String[] args) {
    int i = 1;
    System.out.print(i--);
    String s1 = "cdeab";
    String s2 = "ba";
    boolean ans = new PTOS014_CheckInclustion().containExactly(s1, s2);
    System.out.print(ans);
  }
}
