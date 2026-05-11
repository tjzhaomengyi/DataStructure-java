package com.mikemyzhao.DP.DP.bigshua.sample;

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
    int ans = 0;
    for(int i = 1; i < str.length; i++) {
      if(str[i] == ')'){
        //当前位置是右括号，前面哪个左括号来陪
        int pre = i - 1 - dp[i - 1] ;//该位置要检测这个括号
        if(pre >= 0 && str[pre] == '('){
          //pre > 0的话就要pre-1位置，右括号的话就是+这个位置
          dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
        }
      }
      ans = Math.max(ans,dp[i]);
    }
    return ans;
  }

  // 标准写法只判断当前位置是不是右括号即可
  public int longestValidParenthesesStanard(String s){
    if(s == null || s.length() < 2){
      return 0;
    }

    int n = s.length();
    int[] dp = new int[n];

    int ans = 0;
    for(int i = 1; i < n; i++){
      if(s.charAt(i) == ')'){
        // 如果当前是），并且前面是（，完美凑上一对()
        if(s.charAt(i - 1) == '('){
          dp[i] = i - 2 >= 0 ? dp[i - 2] + 2 : 2;
        } else {
          // 前面是 （）（（）） 访问到最后一个），并且前面是（，需要使用下面
          int j = i - 1 - dp[i - 1];  //这里 j 到第二个左括号(
          if(j >= 0 && s.charAt(j) == '('){
            dp[i] = dp[i - 1] + 2;
            if(j - 1 >= 0){ //左括号前面也有东西，把前面的长度也要加上
              dp[i] += dp[j - 1];
            }
          }

        }
        ans = Math.max(dp[i], ans);
      }
    }
    return ans;
  }
}
