package com.point2offerspecial.three_string.SlidingWindow;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-01 10:23 上午
 * @Description:
 */
public class PTOS017_MinWindow {
  public String minWindow(String s, String t) {
    //欠债表和还债表
    if(s == null || t == null || s.length() < t.length()) return "";
    char[] s1 = s.toCharArray();
    char[] s2 = t.toCharArray();
    int all = s2.length;//欠债总长
    int[] dict = new int[256];
    for(int i = 0; i < s2.length; i++){
      dict[s2[i]]++;//每个金额的欠债
    }
    int L = 0, R = 0;
    int ansl = -1, ansr = -1;
    int len = Integer.MAX_VALUE;
    for(; R < s1.length; R++){
      dict[s1[R]]--;//因为这个是包含情况，所以和全排列那个不一样这个全部都剪掉
      if(dict[s1[R]] >= 0){
        all--;
      }
      if(all == 0){
        //这个时候缩小左侧
        while(dict[s1[L]] < 0){
          dict[s1[L]]++;//还上
          L++;
        }
        if(len > R - L  + 1){//找到了一部分答案
          len = R - L + 1;
          ansl = L;
          ansr = R;
        }
        all++;
        dict[s1[L]]++;//只要缩小窗口，就把对应字典的字符加上
        L++;
      }
    }


    return len == Integer.MAX_VALUE ? "" : s.substring(ansl, ansr + 1);
  }

}

