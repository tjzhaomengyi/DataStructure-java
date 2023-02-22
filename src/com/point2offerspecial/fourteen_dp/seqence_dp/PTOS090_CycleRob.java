package com.point2offerspecial.fourteen_dp.seqence_dp;

import java.util.Arrays;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-16 15:27
 * @Description:
 */
public class PTOS090_CycleRob {
  public int rob(int[] nums) {
    if(nums == null) return 0;
    int len = nums.length;
    if(len == 1) return nums[0];
    if(len == 2) return Math.max(nums[0], nums[1]);
    //技巧：如果从0开始抢，那么 n - 1位置是不能碰的，所以从0到n-2计算
    int money1= robChoose(nums, 0, len - 2);
    int money2 = robChoose(nums, 1, len - 1);
    return Math.max(money1, money2);
  }

  public int robChoose(int[] nums, int start, int end){
    int len = end - start + 1;
    int[] dp = new int[len];
    dp[0] = nums[start];//0可以取也可以不取，但是最大就是自己
    //技巧：因为[0]可以取可以不取，所以1也跟着0位置的取值含义
    dp[1] = Math.max(nums[start], nums[start + 1]);//因为这里把回链断开了，所以又回到单路径的打家劫舍问题
    for(int i = 2; i < len; i++){
      dp[i] = Math.max(dp[i - 2] + nums[start + i], dp[i - 1]);
    }
    return dp[len - 1];
  }

  public static void main(String[] args) {
    int[] tmp = new int[]{1,3,1,3,100};
    int ans = new PTOS090_CycleRob().rob(tmp);
    System.out.println(ans);
  }
}
