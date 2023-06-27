package com.zhousai.zhousai345;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-05-14 11:18
 * @Description:这道题和华为OD200分的jiqirenhuodongquyu(机器人活动区域问题)一起看
 */
public class Juzhenyidongzuidacishu {
 public static void main(String[] args) {
//   Scanner in = new Scanner(System.in);
//   while (in.hasNextInt()) {
//     int m = in.nextInt();
//     int n = in.nextInt();
//     int[][] grids = new int[m][n];
//     for(int i = 0; i < m; i++){
//       for(int j = 0; j < n; j++){
//         grids[i][j] = in.nextInt();
//       }
//     }
//     int ans = maxMoves(grids);
//     System.out.println(ans);
//   }
   int[][] grids = new int[][]{{3,2,4},{2,1,9},{1,1,7}};
   int ans_w = new Juzhenyidongzuidacishu().maxMoves(grids);
   System.out.println(ans_w);
 }

  public static int[][] DIR = new int[][]{{-1, 1}, {0, 1}, {1, 1}};
  private int max = 0;//这里在网页尽量不要用static，要不只能本地通过，网页通过不了，或者使用一个一维数组带着改变这个值，不要定义在方法外面，不好处理
  public  int maxMoves(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    int[][] visited = new int[m][n];
      for(int i = 0; i < m; i++){
          dfs(grid, visited, i, 0);
          if(max == n - 1){
            break;
        }
    }
    return max ;
  }
 //技巧:这道题是一条道走到黑，所以用dfs
  public  void dfs(int[][] grid, int[][] visited, int x, int y) {
    int m = grid.length;
    int n = grid[0].length;
    if (y == n - 1) {
      max = n - 1;
      return ;
    }
    visited[x][y] = 1;
    for(int[] dir : DIR){
      int dirx = dir[0];
      int diry = dir[1];
      int new_x = x + dirx;
      int new_y = y + diry;
      if(new_x >= 0 && new_x < m && new_y >= 0 && new_y < n && visited[new_x][new_y] == 0 && grid[new_x][new_y] > grid[x][y]){
        dfs(grid, visited, new_x, new_y);
      }
    }
    max = Math.max(max, y);
  }
}
