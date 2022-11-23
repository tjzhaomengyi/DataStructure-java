package com.point2offer;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-06 13:35
 * @Description:
 */
public class RegMatch_19 {
  //s是被匹配字符，p是待*和.的匹配字符串
  public boolean isMatch(String s, String p) {
    int m = s.length();
    int n = p.length();
    boolean[][] dp = new boolean[m + 1][n + 1];//m和n表示长度，遍历下标的时候减1
    dp[0][0] = true;
    for (int j = 2; j <= n; j++) {
      if (p.charAt(j - 1) == '*') {
        dp[0][j] = dp[0][j - 2];
      }
    }
    for(int i=1;i<=m;i++){//长度遍历
      for(int j=1;j<=n;j++){//长度遍历
        if(s.charAt(i-1)==p.charAt(j-1)||p.charAt(j-1)=='.'){//下标匹配,p当前=s当前或者p=‘。’
          dp[i][j]=dp[i-1][j-1];
        }
        else if(p.charAt(j-1)=='*'){//等于*就要分开讨论
          //(1)出现*可以匹配上
          if(s.charAt(i-1)==p.charAt(j-2)||p.charAt(j-2)=='.'){
            dp[i][j]=dp[i][j-2]||dp[i-1][j]; //
          }
          //(2)出现*匹配不上.就要把*和前面的摸掉所以
          else if(s.charAt(i-1)!=p.charAt(j-2)){
            dp[i][j]=dp[i][j-2];
          }
        }else{
          dp[i][j]=false;
        }
      }
    }
    return dp[m][n];
  }

  }
