package com.huaweiOD.score200;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-05-23 10:22
 * @Description:
 */
public class TansuokuaiJianli {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextInt()) {
      int m = in.nextInt();
      int n = in.nextInt();
      int xianzhi = in.nextInt();
      int dianliang = in.nextInt();
      if(xianzhi > m || xianzhi > n){
        System.out.println(-1);
      }
      int[][] arr = new int[m][n];
      for(int i = 0; i < m; i++){
        for(int j = 0; j < n; j++){
          arr[i][j] = in.nextInt();
        }
      }
      int ans = getJikuaidi(arr, xianzhi, dianliang);
      System.out.println(ans);
    }
  }

  public static int getJikuaidi(int[][] arr, int xianzhi, int dianliang){
    int cnt = 0;
    for(int i = 0; i < arr.length; i++){
      for(int j = 0; j < arr[0].length; j++){
        if(i + xianzhi > arr.length || j + xianzhi > arr[0].length){
          continue;
        }
        int ans = 0;
        for(int l = 0; l < xianzhi; l++){
          for(int k = 0; k < xianzhi; k++) {
            if (ans >= dianliang) {
              cnt++;
              break;
            } else {
              ans += arr[i + l][j + k];
            }
          }
        }
      }
    }
    return cnt;
  }


}
