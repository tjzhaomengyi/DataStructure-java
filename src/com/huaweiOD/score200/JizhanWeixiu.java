package com.huaweiOD.score200;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-05-26 14:06
 * @Description:
 */
public class JizhanWeixiu {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextInt()) {
      int n = in.nextInt();
      int [][] grids = new int[n][n];
      for(int i = 0; i < n; i++){
        for(int j = 0; j < n; j++){
          grids[i][j] = in.nextInt();
        }
      }

      int shortest = Integer.MAX_VALUE;
      for(int i = 1; i < n; i++){
        shortest = Math.min(shortest, grids[0][i] + grids[i][0]);
      }
      System.out.println(shortest);
    }
  }


}
