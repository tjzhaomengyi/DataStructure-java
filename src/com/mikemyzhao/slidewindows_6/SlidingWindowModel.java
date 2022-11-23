package com.mikemyzhao.slidewindows;

import java.util.HashMap;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-03 12:56
 * @Description:
 */
public class SlidingWindowModel {
    /**滑窗模板**/
    void slidingWindow(String s,String t){
      HashMap<Character,Integer> need=new HashMap<>();
      HashMap<Character,Integer> window = new HashMap<>();
      for(Character c:t.toCharArray()){
        need.put(c,need.getOrDefault(c,0)+1);
      }
      int left =0,right=0;
      int valid = 0;
      while(right<s.length()){
        char c = s.charAt(right);//c是将窗口移入的字符
        right++;
        /**进行数据窗口的更新window的操作,略**/
        /** debug输出位置 **/
        System.out.println("window:"+Integer.toString(left+right));
        //判断左边是否要进行收缩,伪代码
        /**
        while(window is need to shrink){
          //d移除窗口
          char d =s[left];
         left++;
         //进行窗口操作
        }
         **/

      }
    }
}
