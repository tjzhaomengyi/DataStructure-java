package com.book.point2offerspecial.fourteen_dp.bag_dp;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-20 11:40
 * @Description:
 */
public class PTOS104_CombinationSum4 {
  public int combinationSum4(int[] nums, int target) {
    //下面的方法找到的组合不重复，解法和Code03_CoinsWayNoLimit的dp2相似，但是是正向求解
    if(nums == null || nums.length == 0) return 0;
    int N = nums.length;
    int[][] dp = new int[N + 1][target + 1];//选择几个数，能凑的目标,走到用几种，说明走到那就必须用！！

    for(int i = 0; i < N; i++){
      dp[i][0] = 1; //空排列算1种，一个不拿
    }

    for(int i = 1; i < N + 1; i++){
      for(int j = 0; j < target + 1; j++) {
        int ways = 0;
        for(int zhang = 0; zhang * nums[i - 1] <= j; zhang++) {
           ways +=  dp[i - 1][j - nums[i - 1] * zhang];
        }
        dp[i][j] = ways;
      }
    }
    for(int i = 0; i < N + 1; i++){
      for(int j = 0; j < dp[0].length; j++){
        System.out.print(dp[i][j] + ",");
        if(j == dp[0].length -1){
          System.out.println();
        }
      }
    }
    System.out.println(N);
    System.out.println(target);
    return dp[N][target];
  }

  public int combinationSum4_2(int[] nums, int target){
    //但是这道题傻逼在他可以重复，就是数字的顺序可以不一样，那就可以变成nums中循环找数字凑target
    if(nums == null || nums.length == 0) return 0;
    int[] dp = new int[target + 1]; //用nums中数字来凑target的方法数
    dp[0] = 1;//凑target0有多少种方法，一种什么都不放
    for(int i = 1; i <= target; i++){
      for(int num : nums){
        if(i >= num) {
          dp[i] += dp[i - num];//凑成i的方法是通过i-num数搞定的
        }
      }
    }
    return dp[target];
  }

  public static void main(String[] args) {
    int[] problem = new int[]{1,2,3};
    int target = 4;
    int ans = new PTOS104_CombinationSum4().combinationSum4(problem, target);
    System.out.println(ans);
  }
}
