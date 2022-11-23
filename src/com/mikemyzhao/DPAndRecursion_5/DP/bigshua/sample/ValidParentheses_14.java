package com.mikemyzhao.DPAndRecursion_5.DP.bigshua.sample;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-24 20:48
 * @Description:字符串数组全是左右括号，找出最长合法字符串
 */
public class ValidParentheses_14 {
  //两种合法情况：括号 + 括号; 括号 套 括号
  //子串结尾：最长的合法子串
  public int longestValidParentheses(String s) {
    if(s == null || s.length() < 2){
      return 0;
    }
    char[] str = s.toCharArray();
    int[] dp = new int[str.length];
    int pre = 0;
    int ans = 0;
    for(int i = 1; i < str.length; i++) {
      if(str[i] == ')'){
        //当前位置是右括号，前面哪个左括号来陪
        pre = i - dp[i - 1] - 1;//该位置要检测这个括号
        if(pre >= 0 && str[pre] == '('){
          //pre > 0的话就要pre-1位置，右括号的话就是+这个位置
          dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
        }
      }
      ans = Math.max(ans,dp[i]);
    }
    return ans;
  }
}
