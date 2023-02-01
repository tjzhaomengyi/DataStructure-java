package com.mikemyzhao.slidewindows_6.bigshua;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-23 15:24
 * @Description:// 本题测试链接 : https://leetcode.com/problems/permutation-in-string/
 * 判断s2是否包含s1的全排列，s1的排列是s2的一个子串
 */
public class ContainAllCharExtratly_12 {
  //保证s2和s1中该满足要求的子串种类和个数一样
  //s2.len = s1的子串.len;all记录，每个字符用map统计，当all=0，每个字符个数都等于0时，就是满足要求的子串

  public static boolean containExactly(String s1, String s2)
  {
    //s1是长串s2是子串
    if(s1 == null || s2 == null || s1.length() < s2.length()){
      return false;
    }
    char[] str2 = s2.toCharArray();
    int M = str2.length; //窗口大小
    int[] count = new int[256];
    for(int i = 0; i < M; i++){
      count[str2[i]]++;
    }
    int all = M;
    char[] str1 = s1.toCharArray();
    int R = 0;
    //初始化让窗口形成
    for(; R < M; R++){
      if(count[str1[R]]-- > 0){
        all--;
      }
    }
    //窗口形成，接下来窗口右进一个，左边吐一个
    for(;R < str1.length; R++){
      if(all == 0){
        return true;
      }
      if(count[str1[R]]-- > 0){//吐出的时候减掉,说明减掉的字符在count里面有记录
        all--;
      }
      if(count[str1[R-M]]++ >=0){//左神上课这块也没讲清楚，如果正好不是str2里面的字符为什么还+1
        all++;
      }
    }
    return all == 0 ? true : false;
  }
}
