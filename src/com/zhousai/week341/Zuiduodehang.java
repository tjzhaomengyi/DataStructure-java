package com.zhousai.week341;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-22 10:34
 * @Description:
 */
public class Zuiduodehang {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while(in.hasNextLine()){

    }
  }
  public int[] rowAndMaximumOnes(int[][] mat) {
    int max = 0;
    int line = 0;
    for(int i = 0; i < mat.length; i++){
      int cnt = 0;
      for(int j = 0; j < mat[0].length; j++){
        if(mat[i][j] == 1){
          cnt++;
        }
      }
      if(cnt > max){
        max = cnt;
        line = i;
      }
    }
    return new int[]{line, max};
  }
}
