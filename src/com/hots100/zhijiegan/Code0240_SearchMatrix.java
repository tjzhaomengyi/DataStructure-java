package com.hots100.zhijiegan;

/**
 * @Author: zhaomengyi
 * @Date: 2022-12-19 12:06 下午
 * @Description:
 */
public class Code0240_SearchMatrix {
  public boolean searchMatrix(int[][] matrix, int target) {
    if(matrix == null || matrix.length == 0 || matrix[0].length == 0 || matrix[0] == null){
      return false;
    }

    int N = matrix.length;
    int M = matrix[0].length;
    int row = 0;
    int col = M - 1;
    while(row >= 0 && row < N && col >= 0 && col < M){
      if(target > matrix[row][col]){
        row++;
      } else if(target < matrix[row][col]){
        col--;
      } else{
        return true;
      }
    }
    return false;
  }
}
