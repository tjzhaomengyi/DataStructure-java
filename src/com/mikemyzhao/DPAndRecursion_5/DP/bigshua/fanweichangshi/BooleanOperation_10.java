package com.mikemyzhao.DPAndRecursion_5.DP.bigshua.fanweichangshi;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-21 16:23
 * @Description:https://leetcode-cn.com/problems/boolean-evaluation-lcci/
 * 给定一个字符串是一个布尔类型的操作，求获得true或者false的组合数量
 */
public class BooleanOperation_10 {
  //暴力：当前的boolean操作符作为最后计算，左边和右边的方法数是多少
  public static class Info{
    public int t;
    public int f;
    public Info(int tr, int fa){
      t = tr;
      f = fa;
    }
  }

  //加一个缓存
  public static int countEval2(String express,int desired){
    if(express == null || express.equals("")){
      return 0;
    }
    char[] exp = express.toCharArray();
    int N = exp.length;
    Info[][] dp = new Info[N][N];
    Info allInfo = func2(exp,0,exp.length-1,dp);
    return desired == 1 ? allInfo.t : allInfo.f;
  }

  public static Info func2(char[] str,int L, int R, Info[][] dp){
    if(dp[L][R] != null){
      return dp[L][R];
    }
    int t = 0,f = 0;
    if(L == R){
      t = str[L] == '1' ? 1 : 0;
      f = str[L] == '0' ? 1 : 0;
    }
    for(int split = L + 1; split < R; split += 2){
      Info leftInfo = func2(str, L ,split - 1,dp);
      Info rightInfo = func2(str, split + 1, R,dp);
      int a = leftInfo.t;
      int b = leftInfo.f;
      int c = rightInfo.t;
      int d = rightInfo.f;
      switch (str[split]){
        case '&':
          t += a * c;
          f += b * c + b * d + a * d;
          break;
        case '|':
          t += a * c + a * d + b * c;
          f += b * d;
          break;
        case '^':
          t += a * d + b * c;
          f += a * c + b * d;
          break;
      }
    }
    dp[L][R] = new Info(t, f);
    return dp[L][R];
  }

  //思路启发式的递归
  public static int countEval(String express,int desired){
    if(express == null || express.equals("")){
      return 0;
    }
    char[] exp = express.toCharArray();
    Info allInfo = func(exp,0,exp.length-1);
    return desired == 1 ? allInfo.t : allInfo.f;
  }

  public static Info func(char[] str,int L, int R){
    if(L == R){
      int t = str[L] == '1' ? 1 : 0;
      int f = str[L] == '0' ? 1 : 0;
      return new Info(t,f);
    }
    int t = 0;
    int f = 0;
    for(int split = L + 1; split < R; split += 2){
      Info leftInfo = func(str, L ,split - 1);
      Info rightInfo = func(str, split + 1, R);
      int a = leftInfo.t;
      int b = leftInfo.f;
      int c = rightInfo.t;
      int d = rightInfo.f;
      switch (str[split]){
        case '&':
          t += a * c;
          f += b * c + b * d + a * d;
          break;
        case '|':
          t += a * c + a * d + b * c;
          f += b * d;
          break;
        case '^':
          t += a * d + b * c;
          f += a * c + b * d;
          break;
      }
    }
    return new Info(t,f);
  }


}
