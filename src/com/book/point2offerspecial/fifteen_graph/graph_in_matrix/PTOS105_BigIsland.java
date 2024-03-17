package com.book.point2offerspecial.fifteen_graph.graph_in_matrix;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-20 15:18
 * @Description:网格中的深度优先遍历和广度优先遍历模板
 */
public class PTOS105_BigIsland {
  //技巧：因为这个题的图是一个标准的矩形，所以用二维数组求解即可
  public int maxAreaOfIsland(int[][] grid) {
    int rows = grid.length;
    int cols = grid[0].length;
    boolean[][] visited = new boolean[rows][cols];
    int maxArea = 0;
    for(int i = 0; i < rows; i++){
      for(int j = 0; j < cols; j++){
        if(grid[i][j] == 1 && !visited[i][j]){
          int area = getAreaByBFS(grid, visited, i, j);
          //int area = getAreaByDFS(grid, visited, i, j);
          maxArea = Math.max(maxArea, area);
        }
      }
    }
    return maxArea;
  }

  //广度优先遍历使用
  private int getAreaByBFS(int[][] grid, boolean[][] visited, int i, int j){
    Queue<int[]> queue = new LinkedList<>();
    queue.add(new int[]{i, j});
    visited[i][j] = true;

    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int area = 0;
    while(!queue.isEmpty()){
      int[] pos = queue.remove();
      area++;

      for(int[] dir : dirs){
        int r = pos[0] + dir[0];
        int c = pos[1] + dir[1];
        if(r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && grid[r][c] == 1 && !visited[r][c]){
          queue.add(new int[]{r, c});
          visited[r][c] = true;
        }
      }
    }
    return area;
  }

  private int getAreaByDFS(int[][] grid, boolean[][] visited, int i, int j){
    Stack<int[]> stack = new Stack<>();
    stack.push(new int[]{i, j});
    visited[i][j] = true;

    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int area = 0;
    while(!stack.isEmpty()){
      int[] pos = stack.pop();
      area++;

      for(int[] dir : dirs){
        int r = pos[0] + dir[0];
        int c = pos[1] + dir[1];
        if(r >= 0 && r < grid.length
        && c >= 0 && c < grid[0].length
        && grid[r][c] == 1 && !visited[r][c]){
          stack.push(new int[]{r, c});
          visited[r][c] = true;
        }
      }
    }
    return area;
  }
}
