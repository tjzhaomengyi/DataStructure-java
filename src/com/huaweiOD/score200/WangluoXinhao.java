package com.huaweiOD.score200;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-05-22 20:40
 * @Description:
 */
public class WangluoXinhao {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextInt()) {
      int m = in.nextInt();
      int n = in.nextInt();
      int[][] arr = new int[m][n];
      int start_x = 0;
      int start_y = 0;
      for(int i = 0; i < m; i++){
        for(int j = 0; j < n; j++){
          arr[i][j] = in.nextInt();
          if(arr[i][j] > 0){
            start_x = i;
            start_y = j;
          }
        }
      }
      chuanboXinhao(arr, start_x, start_y);
      int ans_x = in.nextInt();
      int ans_y = in.nextInt();
      System.out.println(arr[ans_x][ans_y]);
      for(int i = 0; i < m; i++){
        for(int j = 0; j < n; j++){
         System.out.print(arr[i][j] + " ");
        }
        System.out.println();
      }
    }
  }
  static int[][] DIR = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
  public static void chuanboXinhao(int[][] arr, int start_x, int start_y){
    LinkedList<int[]> queue = new LinkedList<>();
    queue.offer(new int[]{start_x, start_y});
    while(!queue.isEmpty()){
      int[] pos = queue.poll();
      int x = pos[0];
      int y = pos[1];
      int cur_val = arr[x][y];
      if(cur_val > 0) {
        for (int i = 0; i < DIR.length; i++) {
          int[] dir = DIR[i];
          int dir_x = dir[0];
          int dir_y = dir[1];
          int new_x = x + dir_x;
          int new_y = y + dir_y;
          if (new_x >= 0 && new_x < arr.length && new_y >= 0 && new_y < arr[0].length && arr[new_x][new_y] == 0) {
            arr[new_x][new_y] = cur_val - 1;
            queue.offer(new int[]{new_x, new_y});
          }
        }
      } else {
        continue;
      }
    }
  }
}
