package com.huaweiOD.score200;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-05-12 20:07
 * @Description:
 */
public class ZuzhuangSuzu {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String[] nums_str = in.nextLine().split(" ");
      int[] nums = new int[nums_str.length];
      for(int i = 0; i < nums.length; i++){
        nums[i] = Integer.valueOf(nums_str[i]);
      }

      int targetSum = Integer.valueOf(in.nextLine());

      if(nums.length == 0){
        System.out.println(0);
      }

      int[][] dp = new int[nums.length][targetSum + 1];
      for(int i = 0; i < dp.length; i++){
        for(int j = 0; j < dp[0].length; j++){
          dp[i][j] = -1;
        }
      }
      Arrays.sort(nums);
      int minimumNumber = nums[0];

      int ans = dfs(nums, 0, 0, dp, targetSum, minimumNumber);
      System.out.println(ans);

    }
  }

  public static int dfs(int[] nums, int index, int sum, int[][] dp, int targetSum, int minimumNumber){
    if(index >= nums.length) return 0;
    if(sum > targetSum) return 0;
    if(targetSum - sum >= 0 && targetSum - sum < minimumNumber) return 1; //技巧:这个是题目条件:可以用一个比最小数还小的机会
    if(dp[index][sum] != -1) return dp[index][sum];

    int count  = 0;
    for(int i = index; i < nums.length; i++){//技巧:这里挺恶心的，为了能凑出来，可能一直在一个数上一直进行dfs递归
      count += dfs(nums, i, sum + nums[i], dp, targetSum, minimumNumber);//遍历一遍把能凑的次数凑一遍
    }

    dp[index][sum] = count;
    return dp[index][sum];
  }
}
