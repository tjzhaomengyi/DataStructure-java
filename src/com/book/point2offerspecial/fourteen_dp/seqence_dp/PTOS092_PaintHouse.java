package com.book.point2offerspecial.fourteen_dp.seqence_dp;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-16 17:08
 * @Description:
 */
public class PTOS092_PaintHouse {
  public int minCost(int[][] costs) {
    if(costs == null || costs.length == 0 || costs[0].length == 0) return 0;
    int len = costs.length;
    int[][] dp = new int[costs.length][3];
    for(int j = 0; j < 3; j++){
      dp[0][j] = costs[0][j];//把0号房子内存搞成三种颜色，然后往后推
    }
    for(int i = 1; i < len; i++){
      for(int j = 0; j < 3; j++){
        //技巧：因为只有三种颜色所以直接取余即可
        int pre1 = dp[i - 1][(j + 1) % 3];
        int pre2 = dp[i - 1][(j + 2) % 3];
        dp[i][j] = Math.min(pre1, pre2) + costs[i][j];
      }
    }
    return Math.min(Math.min(dp[len - 1][0],dp[len - 1][1]),dp[len - 1][2]);
  }

  public static void main(String[] args) {
    int[] a = new int[]{17, 2, 17};
    int[] b = new int[]{16, 16, 5};
    int[] c = new int[]{14, 3, 19};
    int[][] tmp = new int[][]{a, b, c};
    int ans = new PTOS092_PaintHouse().minCost(tmp);
    System.out.println(ans);
  }
}
