package com.book.point2offerspecial.fourteen_dp.seqence_dp;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-16 14:38
 * @Description: 个人非常好理解的版本
 */
public class PTOS089_RobI {
  public int rob(int[] nums) {
    if(nums == null) return 0;
    int len = nums.length;
    if(len == 1) return nums[0];
    int[] dp = new int[len];//走到当前这家的最大值可以是多少，拿就加上，不拿就找之前的最大值max
    dp[0] = nums[0];//拿这家
    dp[1] = Math.max(nums[0], nums[1]);
    int ans = Math.max(dp[0], dp[1]);
    for(int i = 2; i < len; i++){
      dp[i] = Math.max(dp[i-2] + nums[i], ans); //拿这家(上一家不拿)或者不拿这家(那么就拿上一家dp[i-1])
      //dp[i] = Math.max(dp[i - 2] + nums[i], dp(i - 1));
      ans = Math.max(dp[i], ans);
    }
    return ans;
  }

  public int robLessMem(int[] nums){
    if(nums == null) return 0;
    int len = nums.length;
    if(len == 1) return nums[0];
    //技巧：因为求当前i位置最大的时候只用i-2和i-1位置的结果，所以是一个滚动的
    int[] dp = new int[2];//长度为2的进行滚动即可
    dp[0] = nums[0];
    dp[1] = Math.max(nums[0],nums[1]);
    for(int i = 2; i < nums.length; i++){
      dp[i % 2] = Math.max(dp[(i - 1) % 2], dp[i % 2] + nums[i]);
    }
    return dp[(nums.length - 1) % 2];
  }

  public static void main(String[] args) {
    int[] homes = new int[]{1,2,3,1};
   int ans = new PTOS089_RobI().robLessMem(homes);
    System.out.println(ans);

  }
}
