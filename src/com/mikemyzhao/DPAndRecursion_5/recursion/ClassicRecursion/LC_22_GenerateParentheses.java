package com.mikemyzhao.DPAndRecursion_5.recursion.ClassicRecursion;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-15 16:59
 * @Description:
 */
public class LC_22_GenerateParentheses {
  //生成括号
  public List<String> generateParenthesis(int n) {
    char[] path = new char[n << 1];
    List<String> ans = new ArrayList<>();
    process(path, 0, 0, n, ans);
    return ans;
  }
  //技巧:用左括号-右括号表示是否缺左括号:leftMinusRight，leftRest表示还剩多少左括号.index表示在path的哪个位置
  public static void process(char[] path, int index,int leftMinusRight, int leftRest,List<String> ans){
    if(index == path.length){
      ans.add(String.valueOf(path));
    } else {
      //补左括号
      if(leftRest > 0){
        path[index] = '(';
        process(path, index + 1, leftMinusRight + 1, leftRest - 1, ans);
      }
      //补右括号
      if(leftMinusRight > 0){
        path[index] = ')';
        process(path, index + 1, leftMinusRight - 1, leftRest, ans);
      }
    }
  }
}
