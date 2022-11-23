package com.mikemyzhao.DPAndRecursion_5.DP.bigshua.sample;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-21 15:36
 * @Description:https://leetcode-cn.com/problems/k-inverse-pairs-array/
    给定n，求1到n可以得到k个逆序对的方法
 */
public class KInversePairs_10 {
  //这是一个样本对应模型
  //dp[i][j]表示以当前数结尾，凑成j个逆序对的方法数有多少，
  // 此时可以把arr[i]放到前面数中，只要保证arr[i]和后续数逆序对数量不超过j即可。
  //所以在拆分情况的时候分为了i<j的情况和j>=i的情况,具体公式推导见草纸
  public static int kInversePairs2(int n, int k){
    if(n < 1 || k < 0){
      return 0;
    }
    int[][] dp = new int[n + 1][k + 1];
    int mod = 1000000007;
    //注意base case的 理解和设置
    dp[0][0] = 1; //以0结尾凑成0个逆序串，只有自己本身这个字符
    for(int i = 1; i <= n; i++){
      dp[i][0] = 1;
      for(int j = 1; j <= k; j++){
        dp[i][j] = (dp[i][j-1] + dp[i - 1][j]) % mod;
        if(j >= i){
          dp[i][j] = (dp[i][j] - dp[i - 1][j - i] + mod) % mod;
        }
      }
    }
    return dp[n][k];
  }
}
