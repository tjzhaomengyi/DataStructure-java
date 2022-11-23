package com.mikemyzhao.DP_5;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-08 10:41
 * @Description:一个数组代表一列气球，戳气球加分，求最大分数，分数计算方式：
 *  nums[left]*nums[i]*n
 *  戳气球解法：类似于扔鸡蛋的解法，dp[i][j]表示在指针i和j分别表示在第i个气球和第j个气球之间最大分数(不包括i和j)，还需要一个辅助指针k，
 *  K表示在i和j之间最后一个要戳破的气球。通过移动K获得这个区间的最大值。
 *  dp[i][j]=max(dp[i][j],dp[i][k]+dp[k][j]+points[i]*point[k]*point[j])
 * @NO:LC311,
 */
public class Ballon {
  int maxPoints(int[] nums){
    int n = nums.length;//但是这里要加上末尾一个虚拟气球还有队头一个虚拟气球，两个气球分值为1
    int[] pnts = new int[n+2];//还得开辟一个空间存储带虚拟分值的分数，把原有空间转移到一个新的空间
    pnts[0]=pnts[n+1]=1;
    for(int i=1;i<=n;i++){
      pnts[i]=nums[i-1];
    }
    int[][] dp = new int[n+2][n+2];//表示在(i,j)这个区间内最大分值
    /**如果一个字符串或者数组的两个指针，一前一后，最外层从后往前，里面一层从前往后**/
    //这里j在i后面
    for(int i=n;i>=0;i--){
      for(int j=i+1;j<=n+1;j++){
        for(int k=i+1;k<j;k++){
          dp[i][j]=Math.max(dp[i][j],dp[i][k]+dp[k][j]+pnts[i]*pnts[k]*pnts[j]);
        }
      }
    }
//    for(int i=0;i<dp.length;i++){
//      for(int j=0;j<dp[i].length;j++){
//        System.out.print(dp[i][j]+",");
//      }
//    }
    return dp[0][n+1];
  }

  public static void main(String[] args) {
    int[] nums = new int[]{3,1,5,8};
    System.out.println(new Ballon().maxPoints(nums));
  }
}
