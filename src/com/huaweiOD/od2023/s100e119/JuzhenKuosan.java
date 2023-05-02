package com.huaweiOD.od2023.s100e119;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-18 14:29
 * @Description:
 */
public class JuzhenKuosan {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str = in.nextLine();
      String[] splits = str.split(",");
      int m = Integer.parseInt(splits[0]);
      int n = Integer.parseInt(splits[1]);
      int[][] arr = new int[m][n];
      arr[Integer.parseInt(splits[2])][Integer.parseInt(splits[3])] = 1;
      arr[Integer.parseInt(splits[4])][Integer.parseInt(splits[5])] = 1;
      int ans = getTime(arr);
      System.out.println(ans);
    }
  }


  public static int getTime(int[][] arr){
    int ans = 0;
    int m = arr.length;
    int n = arr[0].length;
    while(isContainZero(arr)){
      int[][] tmp = new int[m][n];
      for(int i = 0; i < m; i++){
        for(int j = 0; j < n; j++){
          if(arr[i][j] == 1){
            tmp[i][j] = 1;
            if(i - 1 >= 0){
              tmp[i - 1][j] = 1;
            }
            if(i + 1 < m){
              tmp[i + 1][j] = 1;
            }
            if(j - 1 >= 0){
              tmp[i][j - 1] = 1;
            }
            if(j + 1 < n){
              tmp[i][j + 1] = 1;
            }
          }
        }
      }
      arr = tmp;
      ans++;
    }
    return ans;
  }

  public static boolean isContainZero(int[][] arr){
    for(int i = 0; i < arr.length; i++){
      for(int j = 0; j < arr[0].length; j++){
        if(arr[i][j] == 0){
          return true;
        }
      }
    }
    return false;
  }


}
