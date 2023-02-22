package com.mikemyzhao.DP.DP.bigshua.HouseRobber;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-18 11:43
 * @Description:
 */
public class LC_0213_HouseRobberII {
  //相邻的不能抢,不带环
  public static int robI(int[] arr){
    int n = arr.length;
    int[] dp = new int[n];
    dp[0] = arr[0];
    dp[1] = Math.max(arr[0],arr[1]);
    for(int i = 2; i < n; i++){
      int p1 = arr[i];//技巧:情况1只抢这一家,这里是考虑了负数，其实不用，都是正数
      int p2 = dp[i - 1];//不抢当前这家
      int p3 = arr[i] + dp[i - 2];//抢当前这家
      dp[i] = Math.max(p1,Math.max(p2,p3));
    }
    return dp[n - 1];
  }

  //练一下用有限几个变量代替上面数组
  public static int robIBetter(int[] arr){
    int n = arr.length;
//    int[] dp = new int[n];
//    dp[0] = arr[0];
//    dp[1] = Math.max(arr[0],arr[1]);
    int pre2 = arr[0];
    int pre1 = Math.max(arr[0], arr[1]);
    for(int i = 2; i < n; i++){
      int tmp = Math.max(pre1, arr[i] + pre2);
      pre2 = pre1;
      pre1 = tmp;
    }
    return Math.max(pre1, pre2);
  }

  //打家劫舍II:也是不相邻抢，但是房子带环
  //思路:(1)如果一开始在[0]选，[0..1]也是pk出一个值，推到n-2停！把n-1甩了 【0到n-2 选怎么最牛逼】
  // (2)从[1]开始选，转到n-1停 【1到n-1上选怎么最牛逼】
  public static int rob(int[] nums){
    if(nums == null || nums.length == 0){
      return 0;
    }
    if(nums.length == 1){
      return nums[0];
    }
    if(nums.length == 2){
      return Math.max(nums[0], nums[1]);
    }
    int ans1 = 0;
    //技巧:情况1从0开始选，选到n-2停止
    int pre2 = nums[0];//只有0自己
    int pre1 = Math.max(nums[0], nums[1]);//0和1选一个
    //可以选0的情况
    for(int i = 2; i < nums.length - 1; i++){
      int tmp = Math.max(pre1, nums[i] + pre2);
      pre2 = pre1;
      pre1 = tmp;
    }
    ans1 = Math.max(pre1, pre2);
    //技巧:情况2 从 1 开始选，这次可以选到 n-1
    int ans2 = 0;
    pre2 = nums[1];
    pre1 = Math.max(nums[1],nums[2]);
    for(int i = 3; i < nums.length; i++){
      int tmp = Math.max(pre1, pre2 + nums[i]);
      pre2 = pre1;
      pre1 = tmp;
    }
    ans2 = Math.max(pre2, pre1);
    return Math.max(ans1, ans2);

  }
}
