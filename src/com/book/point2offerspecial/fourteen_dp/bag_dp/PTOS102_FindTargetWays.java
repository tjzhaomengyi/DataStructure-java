package com.book.point2offerspecial.fourteen_dp.bag_dp;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-17 19:55
 * @Description:
 */
public class PTOS102_FindTargetWays {
  public int findTargetSumWays(int[] nums, int target) {
    if(nums == null) return 0;

    //思路：牛逼我左神：（1）因为nums是正数,sums<target不行
    // （2）数的奇偶性，如果sums 和 target不一致 不行
    // （3）这才到问题转换，所有题解都做这一步了：因为有Sum(pos) - Sum(nag) = target
    //     所以，在两侧加上sum(p) + sum(n) ，有 sum(p) + sum(n) + sum(p) - sum(n) = sum(p) + sum(n) + target
    //     2sum(p) = sum(p) + sum(n) + target
    //    最终，问题变成了 sum(p) 是否等于 （Nums的累加和 + target）/ 2
    int sum = 0;
    for(int n : nums){
      sum += n;
    }
    int trans_target = (sum + target) / 2;
    return (sum < target || ((target & 1) ^ (sum & 1)) != 0) ? 0 : subset(nums, trans_target);
  }

  //技巧：问题转化为优化3--在nums数组中可以找到多少个子数组和为trans_target
  public int subset(int[] nums, int s){
    if(s < 0) return 0;
    int N = nums.length;

    //dp的i表示个数
    int[][] dp = new int[N + 1][s + 1];
    dp[0][0] = 1;
    for(int i = 1; i <= N; i++){
      for(int j = 0; j <= s; j++){
        if(nums[i - 1] > j){
          dp[i][j] = dp[i - 1][j];//第i-1个数没有用，只能用i-1个凑
        } else {
          dp[i][j] = dp[i - 1][j - nums[i - 1]] + dp[i - 1][j];//可以用第i-1个数凑，也可以不用
        }
      }
    }
    return dp[N][s];
  }
}
