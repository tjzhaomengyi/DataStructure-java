package com.mikemyzhao.recursion.extrainfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-20 21:01
 * @Description:给定一个括号字符串，求删除成合理括号组合的所有方法
 */
public class ShanChuKuoHaoZongFangfaShu_9 {
  public static List<String> removeInvalidParentheses(String s){
    List<String> ans = new ArrayList<>();
    remove(s, ans, 0, 0, new char[] { '(', ')' });
    return ans;
  }
  //checkindex下次开始检查的位置就是每次滑动的结束点，deleteIndex下次要删除开始检查的点，
  //deleteIndex不能大于checkIndex
  //par表示左右括号
  public static void remove(String s,List<String> ans, int checkIndex, int deleteIndex, char[] par){
    for(int count = 0, i = checkIndex; i < s.length(); i++){
      if(s.charAt(i) == par[0]){
        count++;
      }
      if(s.charAt(i) == par[1]){
        count--;
      }
      //右边括号多了要搞事情
      if(count < 0){
        for(int j = deleteIndex; j <= i; j++){
          //((()))),j开始遍历当j-1时左括号的时候！这个是要删除最左边的右括号！
          if(s.charAt(j) == par[1] && (j == deleteIndex || s.charAt(j - 1) != par[1])){
            remove(s.substring(0,j) + s.substring(j + 1,s.length()), ans, i, j, par);
          }
        }
        return;
      }
    }
    //todo：优雅的地方 把String反转过来用同样的方法考察左括号
    String reversed = new StringBuilder(s).reverse().toString();
    if (par[0] == '(') {
      remove(reversed, ans, 0, 0, new char[] { ')', '(' });
    } else {
      ans.add(reversed);
    }
  }

}
