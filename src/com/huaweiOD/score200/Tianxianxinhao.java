package com.huaweiOD.score200;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-06-01 14:58
 * @Description:
 */
public class Tianxianxinhao {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextInt()) {
      int m = in.nextInt();
      int n = in.nextInt();
      int[][] anth = new int[m][n];
      for(int i = 0; i < m; i++){
        for(int j = 0; j < n; j++){
          anth[i][j] = in.nextInt();
        }
      }

      String ans = solve_method(anth, m, n);
      System.out.println(ans);
    }
  }

  public static String solve_method(int[][] anth, int m, int n){
    int[][] ret = new int[m][n];
    for(int j = 0; j < n; j++){
      for(int i = 0; i < m; i++){
        for(int k = i + 1; k < m; k++){
          if(k - i > 1 && anth[k][j] <= anth[k - 1][j]){
            continue;
          }
          ret[k][j]++;
          if(anth[i][j] <= anth[k][j]){
            break;
          }
        }
      }


    }
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < m; i++){
      for(int j = 0; j < n; j++){
        for(int k = j + 1; k < n; k++){
          if(k - j > 1 && anth[i][k] <= anth[i][k - 1]){
            continue;
          }
          ret[i][k]++;
          if(anth[i][j] <= anth[i][k]){
            break;
          }
        }
        sb.append(ret[i][j]).append(" ");
      }
    }
    return m + " " + n + "\n" + sb.toString().trim();
  }
}
