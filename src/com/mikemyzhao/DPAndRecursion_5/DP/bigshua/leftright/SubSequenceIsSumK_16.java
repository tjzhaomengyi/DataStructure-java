package com.mikemyzhao.DPAndRecursion_5.DP.bigshua.leftright;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-27 9:03
 * @Description:子序列【从左往右】，累加和是否为k[DP版本，还有分治的暴力版本],
 * 注意这个数组中有正有负
 */
public class SubSequenceIsSumK_16 {
  public static boolean isSum(int[] arr, int sum){
    if(sum == 0){
      return true;
    }
    if(arr.length == 0 || arr == null){
      return false;
    }
    //看看sum是否比数组的最大值还大，比最小值还小
    int min = 0;
    int max = 0;
    for(int i = 0; i < arr.length; i++){
      min += Math.min(min, min + arr[i]);
      max += Math.max(max, max + arr[i]);
    }
    if(sum < min || sum > max){
      return false;
    }

    int N = arr.length;
    boolean[][] dp = new boolean[N][max - min + 1];
    //dp[0][0] = true; //0到0范围能不能搞定0这个累加和
    //向右平移;//0
    dp[0][-min] = true;
    //dp[0][arr[0]]
    dp[0][arr[0] - min] = true;
    for(int i = 1; i < N; i++){
      for(int j = min; j <= max; j++){
        //dp[i][j] = dp[i - 1][j]//没有使用[i],可以凑出j
        dp[i][j - min] = dp[i - 1][j - min];
        //dp[i][j] = dp[i - 1][j - arr[i] - min]//使用arr[i]凑出j
        int next = j - arr[i] - min;
        dp[i][j - min] |= (next >= 0 && next < max - min && dp[i - 1][next]);
      }
    }
    return dp[N - 1][sum - min];
  }

}
