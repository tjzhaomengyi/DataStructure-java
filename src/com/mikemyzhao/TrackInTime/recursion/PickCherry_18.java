package com.mikemyzhao.TrackInTime.recursion;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-03 8:26
 * @Description:一个人沿着矩阵摘樱桃，每个格子上有一个樱桃，摘完就没，求一个来回摘得最多的樱桃
 */
public class PickCherry_18 {
  //设置两个人AB，都从起点出发，最终同时到达终点最多的就是最多樱桃
  //思路:结论如果两个人同时在一个点，说明他们走的路径相同。如果在同一个点只拿一个
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int M = sc.nextInt();
    int[][] matrix = new int[N][M];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        matrix[i][j] = sc.nextInt();
      }
    }
    int ans = cherryPickup(matrix);
    System.out.println(ans);
    sc.close();
  }

  public static int cherryPickup(int[][] grid){
    int N = grid.length;
    int M = grid[0].length;
    //思路：这里a+b = c + d ,所以有d = a + b - c
    int[][][] dp = new int[N][M][N];
    for(int i = 0; i < N; i++){
      for(int j = 0; j < M; j++){
        for(int k = 0; k < N; k++){
          dp[i][j][k] = Integer.MIN_VALUE;//因为0是可能的情况，有可能走不到
        }
      }
    }
    int ans = process(grid,0,0,0,dp);
    return ans < 0 ? 0 : ans;
  }


  //process返回当前所在节点可以拿的樱桃树
  public static  int process(int[][] grid, int x1, int y1, int x2,int[][][] dp){
    int N = grid.length;
    int M = grid[0].length;
    int cur = 0;
    int y2 = x1 + y1 - x2;
    if(x1 == grid.length || y1 == grid[0].length || x2 == grid.length || y2 == grid[0].length){
      return Integer.MIN_VALUE;
    }
    if(dp[x1][y1][x2] != Integer.MIN_VALUE){
      return dp[x1][y1][x2];
    }
    if(x1 == grid.length - 1 && y1 == grid[0].length - 1){
      dp[x1][y1][x2] = grid[x1][y1];//没有后续了
      return dp[x1][y1][x2];
    }
    int best = Integer.MIN_VALUE;

    best = Math.max(best, process(grid,x1 + 1, y1, x2 + 1, dp));
    best = Math.max(best, process(grid, x1 + 1, y1, x2, dp));
    best = Math.max(best, process(grid,x1 , y1 + 1, x2 , dp));
    best = Math.max(best, process(grid, x1, y1 + 1, x2 + 1, dp));
//    if (grid[x1][y1] == -1 || grid[x2][x1 + y1 - x2] == -1 || best == -1) {
//      dp[x1][y1][x2] = -1;
//      return dp[x1][y1][x2];
//    }
    if(x1 == x2 && y1 == y2){
      dp[x1][y1][x2] = grid[x1][y1] + best;
      return dp[x1][y1][x2];
    }

    dp[x1][y1][x2] = grid[x1][y1] + grid[x2][x1 + y1 - x2] + best;
    return dp[x1][y1][x2];
  }
}
