package com.huaweiOD.nowcoder;

import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class SolveSoduku {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    // 注意 hasNext 和 hasNextLine 的区别
    while (in.hasNextInt()) { // 注意 while 处理多个 case
      int[][] nums = new int[9][9];
      for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
          nums[i][j] = in.nextInt();
        }
      }

      solveSudoku(nums);
      for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
          System.out.print(nums[i][j] + " ");
        }
        System.out.println();
      }
    }
  }

  public static boolean solveSudoku(int[][] board) {
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        if (board[i][j] != 0) {
          continue;
        }
        for (int k = 1; k <= 9; k++) {
          if (isValidSudukou(i, j, k, board)) {
            board[i][j] = k;
            if(solveSudoku(board)){
              return true;
            }
            board[i][j] = 0;
          }
        }
        return false;
      }

    }
    return true;
  }

  public static boolean isValidSudukou(int row, int col, int val, int[][] board){
    for(int i = 0; i < 9; i++){
      if(board[row][i] == val){
        return false;
      }
    }
    for(int j = 0; j < 9; j++){
      if(board[j][col] == val){
        return false;
      }
    }

    //3 * 3的九宫格是否有重复
    int startRow = (row / 3) * 3;
    int startCol = (col / 3) * 3;
    for(int i = startRow; i < startRow +3; i++){
      for(int j = startCol; j < startCol + 3; j++){
        if(board[i][j] == val){
          return false;
        }
      }
    }
    return true;
  }

}