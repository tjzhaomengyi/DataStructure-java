package com.hotinterview.Nanti;

/**
 * @Author: zhaomengyi
 * @Date: 2023-01-10 9:53 上午
 * @Description:
 */
public class Code0044_RegularExpMatchEasy {
  public boolean isMatch(String str, String exp){
    if(str == null || exp == null){
      return false;
    }
    char[] s = str.toCharArray();
    char[] e = exp.toCharArray();
    int[][] dp = new int[s.length + 1][e.length + 1];
    return isValid(s, e) && process(s, e, 0 , 0, dp);
  }

  public boolean isValid(char[] s, char[] e){
    for(int i = 0; i < s.length; i++){
      if(s[i] == '*' || s[i] == '?'){
        return false;
      }
    }
    for(int i = 0; i < e.length; i++){
      if(e[i] == '*' && e[i - 1] == '*'){
        return false;
      }
    }
    return true;
  }

  public boolean process(char[] s, char[] e, int si, int ei, int[][] dp){
    if(dp[si][ei] != 0){
      return dp[si][ei] == 1;
    }
    boolean ans = false;
    if(ei == e.length){
      ans = si == s.length; //todo:考虑[s.len-1]是否是*
    } else {
      if(e[ei] != '*'){
        ans = si != s.length && (s[si] == e[ei] || e[ei] == '?') && process(s, e, si + 1, ei + 1, dp);
      } else { //如果e[ei] 等于 *
        if(si == s.length){ // s字符串匹配完了
          ans = process(s, e, si, ei + 1, dp);//跳过[ei]=*这个当前字符串往后走
        } else { //si没有结束
          ans = process(s,e,si,ei + 1, dp) || process(s, e, si + 1, ei, dp);
        }
      }
    }
    dp[si][ei] = ans ? 1 : -1;
    return ans;
  }


  /***
   * 这个方法就是模板，动态规划搞定，单个字符的
   * @param reg
   * @param str
   * @return
   */
  public static boolean isMatchByDP(String reg, String str){
    int m = reg.length();
    int n = str.length();
    boolean[][] dp = new boolean[m + 1][n + 1];
    dp[0][0] = true;
    if(reg.charAt(0) == '*'){
      dp[1][0] = true;
    }
    for(int i = 1; i < m + 1; i++){
      char r = reg.charAt(i - 1);
      for(int j = 1; j < n + 1; j++){
        char c = str.charAt(j - 1);
        if(r == '?'){
          if(Character.isDigit(c) || Character.isLetter(c)){
            dp[i][j] = dp[i - 1][j - 1];
          } else {
            dp[i][j] = false;
          }
        } else if(r == '*'){
          if(Character.isDigit(c) || Character.isLetter(c)){
            dp[i][j] = dp[i - 1][j - 1] || dp[i][j - 1] ||dp[i - 1][j];
          } else {
            dp[i][j] = false;
          }
        } else if(c == r){
          dp[i][j] = dp[i - 1][j - 1];
        } else {
          dp[i][j] = false;
        }
      }
    }
    return dp[m][n];
  }

  public static void main(String[] args) {
    boolean ans = new Code0044_RegularExpMatchEasy().isMatch("","***");
  }
}
