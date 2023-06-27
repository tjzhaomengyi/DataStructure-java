package com.huaweiOD.score200;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-05-12 16:04
 * @Description:
 */
public class JiqirenHuodongQuyu {
  private static int[][] DIR = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextInt()) {
      int m = in.nextInt();
      int n = in.nextInt();
      int[][] nums = new int[m][n];
      for(int i = 0; i < m; i++){
        for(int j = 0; j < n; j++){
          nums[i][j] = in.nextInt();
        }
      }
      int max = 1;
      int[][] visited = new int[m][n];
      for(int i = 0; i < m; i++){
        for(int j = 0; j < n; j++){
          if(visited[i][j] == 0){
            System.out.println("---------------------");
            int tmp = dfs(nums, visited, i, j);
            max = tmp > max ? tmp : max;
          }
        }
      }
    System.out.println(max);
    }
  }

  public static int dfs(int[][] nums, int[][] visited, int x, int y){
    int k = 1;
    Queue<int[]> queue = new LinkedList<>();
    queue.offer(new int[]{x, y});
    visited[x][y] = 1;
    while(!queue.isEmpty()){
      int[] cur = queue.poll();
      for(int[] d : DIR){
        int newX = d[0] + cur[0];
        int newY = d[1] + cur[1];
        if(newX >= 0 && newX <= nums.length - 1 && newY >= 0 && newY <= nums[0].length - 1){
          if(visited[newX][newY] != 1 && Math.abs(nums[cur[0]][cur[1]] - nums[newX][newY]) <= 1){//技巧:这里不能等于1！！！
           //Math.abs(nums[cur[0]][cur[1]] - nums[newX][newY]) <= 1
            k++;
            System.out.println(newX + "," + newY);
            queue.offer(new int[]{newX, newY});
            visited[newX][newY] = 1;
          }
        }
      }
    }

    return k;
  }
}
