package com.book.point2offerspecial.fourteen_dp.path_dp;

import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-17 17:55
 * @Description:
 */
//思路：把树往左推对齐！！！！转换就是这一层[i][j] 只能从[i-1][j - 1]或者[i - 1][j]过来，如果到这一层相等那么只能从上一层加过来
public class PTOS100_MininumTriangle {
  public int minimumTotal(List<List<Integer>> triangle) {
    if(triangle == null) return 0;
    int n = triangle.size();
    int[][] dp = new int[n][n];//到第i层第j节点的最短距离
    dp[0][0] = triangle.get(0).get(0);
    for(int i = 1; i < n; i++){
      dp[i][0] = dp[i - 1][0] + triangle.get(i).get(0); //都选一样的
    }
    for(int i = 1; i < n; i++){
      for(int j = 1; j <= i; j++){
        if(i == j){//技巧：如果是i = j的值，只能从上一层的最后位置过来，题目要求只能从 j或者j-1位置过来
          dp[i][j] = dp[i - 1][j - 1] + triangle.get(i).get(j);
        } else {
          dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + triangle.get(i).get(j);
        }
      }
    }

    int min = Integer.MAX_VALUE;
    for(int num : dp[n - 1]){
      min = Math.min(min, num);
    }
    return min;
  }
}
