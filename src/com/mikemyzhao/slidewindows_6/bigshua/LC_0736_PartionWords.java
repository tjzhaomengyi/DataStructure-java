package com.mikemyzhao.slidewindows_6.bigshua;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-19 14:01
 * @Description:
 */
public class LC_0736_PartionWords {
  //切分字符串，把一种字符尽量放在一块里面，求分法
  public static List<Integer> partitionLabels(String s){
    char[] str = s.toCharArray();
    int[] far = new int[26];
    for(int i = 0; i < str.length; i++){
      far[str[i] - 'a'] = i;//每个字符的最右侧位置记录下来
    }
    List<Integer> ans = new ArrayList<>();
    int left = 0;
    int right = far[str[0] - 'a'];//当前起始字符的最右位置
    for(int i = 1; i < str.length; i++){
      if(i > right){//i位置已经正好包括了第一个字符,重开一段
        ans.add(right - left + 1);
        left = i;
      }
      right = Math.max(right, far[str[i] - 'a']);
    }
    ans.add(right - left + 1);
    return ans;
  }

}
