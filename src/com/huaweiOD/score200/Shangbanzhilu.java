package com.huaweiOD.score200;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-05-24 12:12
 * @Description:
 */
public class Shangbanzhilu {
  static int t = -1;
  static int c = -1;
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String tc = in.nextLine();
      t = Integer.valueOf(tc.split(" ")[0]);//拐弯次数
      c = Integer.valueOf(tc.split(" ")[1]);//路障数

      String mn = in.nextLine();
      int m = Integer.valueOf(mn.split(" ")[0]);
      int n = Integer.valueOf(mn.split(" ")[1]);
      char[][] matrix = new char[m][n];
      int start_x = 0;
      int start_y = 0;
      for (int i = 0; i < m; i++) {
        String line = in.nextLine();
        for (int j = 0; j < n; j++) {
          matrix[i][j] = line.charAt(j);
          if (matrix[i][j] == 'S') {
            start_x = i;
            start_y = j;
          }
        }
      }
      //pre_dir记录上次移动方向，开始记录为-1，根据当前移动进行下标变换
      int[][] visited = new int[m][n];
      boolean ans = dfs(matrix, visited, 0, 0, start_x, start_y, -1);
      System.out.println(ans);
    }
  }

  static int[][] DIR = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

  public static boolean dfs(char[][] matrix, int[][] visited, int t_used, int c_used, int x, int y, int pre_dir) {
    if (matrix[x][y] == 'T') {
      return true;
    }
    visited[x][y] = 1;
//    boolean p1 = false;
    for (int i = 0; i < DIR.length; i++) {
      int[] dir = DIR[i];
      int new_x = x + dir[0];
      int new_y = y + dir[1];
      boolean is_turn = false;
      boolean is_curry = false;
      if (new_x >= 0 && new_x < matrix.length && new_y >= 0 && new_y < matrix[0].length && visited[new_x][new_y] == 0){
        if(pre_dir != -1 && pre_dir != i){
          if(t_used + 1 > t){
            continue;
          }
          is_turn = true;
        }
        if('*' == matrix[new_x][new_y]){
          if(c_used + 1 > c_used){
            continue;
          }
          is_curry = true;
        }
        if(dfs(matrix, visited, t_used + (is_turn ? 1 : 0), c_used + (is_curry ? 1 : 0), new_x, new_y, i)){
          return true;
        }
      }
    }
    return false;
  }
}
