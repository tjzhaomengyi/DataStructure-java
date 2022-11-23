package com.mikemyzhao.slidewindows_6.bigshua;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-07 9:32
 * @Description:Str2中是否存在一个子串包含str2的所有字符，子串必须是连续且有序的
 * 数学结论：典型的欠债模型
 */
public class Str2ContainStr1SubString_24 {
  //技巧：还债模型:1、目标字符串的长度all，2、目标字符串，字符的个数：map
  //技巧：目标：让all一点点变成0，只要遇上字符串在字典中有就把(1)map对应字符个数减1，(2)all长度减1
  //技巧：注意：有效还款all减小，无效还款all不变.当all=0的时候
  //https://leetcode.com/problems/minimum-window-substring/,这个要求返回对应子串
  public static String minWindow(String s, String t){//s是否包含t的子串
   if(s.length() < t.length()){
     return null;
   }
   char[] str = s.toCharArray();
   char[] target = t.toCharArray();
   int[] map = new int[256];
   for(char cha : target){
     map[cha]++;//技巧：每个字符欠账个数，正数表示别人要给他，负数表示他要还给别人
   }

   int all = target.length;//技巧：总欠账金额
    //技巧：窗口是左闭右开[0,0),相当于[L,R-1]
   int L = 0;
   int R = 0;
   int len = Integer.MAX_VALUE;
   int ansl = -1;
   int ansr = -1;
   //技巧：外围是有指针卡着，然后里面动左指针
   while(R!= str.length){
     map[str[R]]--;//技巧：不管是谁都减掉，有负的用L再补dddd
     if(map[str[R]] >= 0){//技巧：说明上面的是有效还款，总还款金额要减少
       all--;//技巧：停别动了，缩左侧，把负的补回来
     }
     if(all == 0){
       //注意：一旦等于0就不能缩了，因为这是位置正好卡在有效还款上！！！！
       while(map[str[L]] < 0){//技巧：把多给的要回来
         map[str[L]]++;
         L++;//L往右缩小
       }
       if(len > R - L + 1){
         len = R - L + 1;
         ansl = L;
         ansr = R;
       }
       //技巧：此时注意，到这步已经阶段性成功了！再往里进的就要把欠账金额往上加了
       //技巧：因为是找最小，所以左侧不停在缩小，左边往右推,先动左指针再动右指针！！！
       all++;
       map[str[L]]++;
       L++;
     }
     R++;
   }
   return len == Integer.MAX_VALUE ? "" : s.substring(ansl, ansr + 1);
  }


}
