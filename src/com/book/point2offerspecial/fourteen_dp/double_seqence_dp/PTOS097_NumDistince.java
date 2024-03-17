package com.book.point2offerspecial.fourteen_dp.double_seqence_dp;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-17 15:29
 * @Description:
 */
public class PTOS097_NumDistince {
  //思路：注意是子序列，说明找到的字符可以不连着
  public int numDistinct(String s, String t) {
    //明显的匹配类型的动态规划模型
    int n = s.length();
    int m = t.length();
    char[] str1 = s.toCharArray();
    char[] str2 = t.toCharArray();
    int[][] dp = new int[n + 1][m + 1]; // 从s取出i个，从t中取出m个
    dp[0][0] = 1;
    for(int i = 1; i < n + 1; i++){
      dp[i][0] = 1;
    }
    for(int j = 1; j < m + 1; j++){
      dp[0][j] = 0;
    }

    //i表示个数
    for(int i = 1; i < n + 1; i++){
      for(int j = 1; j < m + 1; j++){ //条件限制一下
       // if(j > i) dp[i][j] = 0;//这种事凑不出来的
        if(str1[i - 1] == str2[j - 1]){
          //用i-1凑j-1，或者不用i-1凑j-1，但是这个i-1要取出来
          dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];//不放i和j的方法数，延续过来。不要这个[i-1]凑j长的str2
        }else {//如果[i - 1] 和 [j - 1]不相等，那么只能用i - 1个凑j了
          dp[i][j] = dp[i - 1][j];
        }
      }
    }
    return dp[n][m];
  }
}
