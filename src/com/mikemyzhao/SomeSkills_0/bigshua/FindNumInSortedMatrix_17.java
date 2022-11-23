package com.mikemyzhao.SomeSkills_0.bigshua;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-27 14:20
 * @Description:给定二维矩阵行列都有序，能否在矩阵中可以找到为K的
 */
public class FindNumInSortedMatrix_17 {
  public static boolean containsK(int[][] matrix, int K){
    //从矩阵的右上角或者左下角开始找K，当前数字num < K往下侧找，如果 num > K往左侧找
    int row = 0;
    int col = matrix[0].length - 1;
    while(row < matrix.length && col > -1){
      if(matrix[row][col] == K){
        return true;
      } else if(matrix[row][col] < K){
        row++;
      } else {
        col--;
      }
    }
    return false;
  }
}
