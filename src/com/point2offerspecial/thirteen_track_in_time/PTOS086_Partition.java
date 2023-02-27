package com.point2offerspecial.thirteen_track_in_time;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-16 11:26
 * @Description:
 */
public class PTOS086_Partition {
  //这道题其实求的是全组合结果，所以第一反应就是回溯，除非记住了才能想到左神的dp优化方案
  public String[][] partition(String s) {
    List<List<String>> ans = new LinkedList<>();
    process(s, 0 , new LinkedList<String>(), ans);
    String[][] res = new String[ans.size()][];
    for(int i = 0; i < ans.size(); i++){
      List<String> sub = ans.get(i);
      String[] tmp_sub = (String[]) sub.toArray(new String[sub.size()]);
      res[i] = tmp_sub;
    }

    return res;
  }

  private void process(String str, int start, LinkedList<String> sub, List<List<String>> ans){
    if(start == str.length()){
      ans.add(new LinkedList<>(sub));
    }
    for(int i = start; i < str.length(); i++){
      if(isParlindrome(str, start, i)){
        sub.add(str.substring(start, i + 1));//技巧：到i可以组成回文，左闭右开
        process(str, i + 1, sub, ans);//从下一位开始
        sub.removeLast();
      }
    }
  }

  private boolean isParlindrome(String str, int start, int end){
    while(start < end){
      if(str.charAt(start++) != str.charAt(end--)){
        return false;
      }
    }
    return true;
  }
}
