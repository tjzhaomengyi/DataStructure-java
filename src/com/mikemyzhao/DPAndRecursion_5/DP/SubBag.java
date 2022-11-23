package com.mikemyzhao.DP_5;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-08 18:26
 * @Description:子集背包问题，给一个数组判断能否拆成两个子集，使得两个子集的和相等
 * @NO:LC ,目标问题的题解就是dp[N][sum/2],N长度的数组能否拆成sum/2的子串
 */
public class SubBag {
  boolean canPartition(int[] nums){
    int sum = 0;
    for(int num:nums){
      sum = sum+num;
    }
    if(sum%2!=0) return false;
    int n = nums.length;
    sum = sum / 2;
    Boolean[][] dp = new Boolean[n][sum];//对于前i个物品，背包容量为j是能否取得sum
    //base case
    for (int i=0;i<=n;i++){
      dp[i][0]=true;
    }
    for(int i=1;i<=n;i++){
      for(int j=1;j<=sum;j++){
        if(j<nums[i]){
          dp[i][j]=dp[i-1][j];
        }else{
          /**这和背包不同要理解**/
          dp[i][j]=dp[i-1][j]||dp[i-1][j-nums[i-1]]; //dp[i-1][j]前面i-1长的数组已经可以满足j就不放了，dp[i-1][j-num[i-1]]这次需要放入
        }
      }
    }


    return false;
  }

}
