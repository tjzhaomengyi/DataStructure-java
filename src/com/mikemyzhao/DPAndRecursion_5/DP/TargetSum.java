package com.mikemyzhao.DPAndRecursion_5.DP;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-09 12:39
 * @Description:目标和问题,findSumWays给一个数组求能得到目标值的方法有多少种；findTargetSumWays是可以添加+和-操作符号把两组数凑成target
 * @LC：NO494，这种
 */
public class TargetSum {
  int findTargetSumWays(int[] nums,int target){
    /**target=sum1-sum2；
     * sum1=target+sum2;
     * 2sum1 = target+sum2+sum1 = target+sum(nums)
     * 找到sum1==(target+sum)/2即可
     * **/
    int sum=0;
    for(int n:nums){
      sum = sum + n;
    }
    if(Math.abs(target)>Math.abs(sum)){return 0;}//这种{100},-200的情况，没法凑出来
    if(sum<target||(sum+target)%2==1){
      return 0;
    }else{
      return findSumWays(nums,(sum+target)/2);//这个是把两个op：加减操作转成一个加法操作的条件
    }
  }
  public int findSumWays(int[] nums,int target){
    int n = nums.length;
    int[][] dp = new int[n+1][target+1];//dp[i][j]取到长度为i的nums，得到j的方法数量
    //base case
    for(int i=0;i<=n;i++){
      dp[i][0]=1;
    }
    for(int i=1;i<=n;i++){
      for(int j=0;j<=target;j++){
        if(nums[i-1]<=j){//取的值小于目标值
          dp[i][j] = dp[i-1][j]+dp[i-1][j-nums[i-1]];//两种情况，放入这个值的方法数以及不放入这个方法数之和
        }else {
          dp[i][j] = dp[i-1][j];
        }
      }
    }
    return dp[n][target];
  }

  public static void main(String[] args) {
    System.out.println(new TargetSum().findTargetSumWays(new int[]{100},-200));
  }

}
