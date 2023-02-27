package com.mikemyzhao.DP.DP;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-06 21:52
 * @Description:构造回文最小次数,成为回文串的最小操作次数
 * @NO:LC1312 状态转移公式s[i]=s[j]:无需操作dp[i][j]=dp[i-1][j-1];s[i]!=s[j]：在i一侧添加，或者在j一侧添加，或者保持不变，取最小值
 */
public class CreateCycleArr {
  int minInsertions(String s){
    int m = s.length();
    int dp[][] = new int[m][m];
//    dp[0][0]=0;
//    for(int i=0;i<m;i++){
//      dp[i][i]=Integer.MAX_VALUE;
//    }
    for(int i=m-2;i>=0;i--){
      for(int j=i+1;j<=m-1;j++){
        if(s.charAt(i)==s.charAt(j)){
          dp[i][j]=dp[i+1][j-1];
        }else{
          dp[i][j]=Math.min(dp[i+1][j],dp[i][j-1])+1;
        }
      }
    }
    return dp[0][m-1];
  }

  public static void main(String[] args) {
    System.out.println(new CreateCycleArr().minInsertions("zzazz"));
  }
}
