package com.book.point2offerspecial.fourteen_dp.seqence_dp;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-16 20:10
 * @Description:
 */
public class PTOS094_MinCut {
  //太难了~直接记住例子吧 ： 套子题，就这个解法
  public int minCut(String s) {
    if(s == null || s.length() < 2) return 0;
    char[] str = s.toCharArray();
    int n = str.length;
    boolean[][] checkmap = isPraladrome(str);
    int dp[] = new int[n];//到当前位置有多少个回文串
    dp[n - 1] = 1;
    //技巧：从外围往里兜住找
    for(int i = n - 2; i >= 0; i--){
      if(checkmap[i][n - 1]){
        dp[i] = 1;
      } else {
        int next = Integer.MAX_VALUE;//到时候找最小切割部分
        for(int j = i; j < n; j++){
          if(checkmap[i][j]){
            next = Math.min(next, dp[j + 1]);
          }
        }
        dp[i] = 1 + next;
      }
    }
    return dp[0] - 1;
  }

  private boolean[][] isPraladrome(char[] s){
    int len = s.length;
    boolean[][] ans = new boolean[len][len];
    //先把最基本情况搞好,自己到自己和自己
    for(int i = 0; i < len - 1; i++){
      ans[i][i] = true;
      ans[i][i + 1] = s[i] == s[i + 1] ? true : false;
    }
    ans[len - 1][len - 1] = true;
    //太难了~直接记住例子吧 填表法填表法!!!【5*5的表格画一下就懂了】
    for(int i = len - 3; i >= 0; i--){
      for(int j = i + 2; j < len; j++){
        ans[i][j] = s[i] == s[j] && ans[i + 1][j - 1];
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    String a = "aab";
    int ans = new PTOS094_MinCut().minCut(a);
    System.out.println(ans);
  }
}
