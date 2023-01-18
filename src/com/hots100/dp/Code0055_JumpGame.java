package com.hots100.dp;

/**
 * @Author: zhaomengyi
 * @Date: 2022-12-08 12:22 下午
 * @Description:
 */
public class Code0055_JumpGame {
  //方法1：DP方法直接求解
  public boolean canJump(int[] nums) {
    if(nums.length == 0 || nums == null || (nums[0] == 0 && nums.length > 0)) return false;
    int[] dp = new int[nums.length];//当前i位置最远可以跳到多远
    dp[0] = nums[0];
    //陷阱因为是下标位置，所以要考虑从0位置到1位置的情况
    if((dp[0] > 0 && nums.length==2) || (nums.length == 1)){
      return true;
    }
    for(int i = 1; i < nums.length-1; i++){
      dp[i] = Math.max(i + nums[i], dp[i-1]);
      if(dp[i] <= i){
        return false;
      }
      if(dp[i] >= nums.length - 1){
        return true;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    System.out.println(new Code0055_JumpGame().canJump(new int[]{1,0,1,0}));
  }
}
