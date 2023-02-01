package com.point2offerspecial.two_array.PreSum;

/**
 * @Author: zhaomengyi
 * @Date: 2023-01-26 13:52
 * @Description:
 */
public class PTOS013_SumRegion {
  private int[][] sums;
  public PTOS013_SumRegion(int[][] matrix) {
    if(matrix == null || matrix[0].length == 0){
      return;
    }
    sums = new int[matrix.length + 1][matrix[0].length + 1];//上边和左边多顶一层就能防止后续计算溢出
    for(int i = 0; i < matrix.length; i++){
      int rowSum = 0;
      for(int j = 0; j < matrix[0].length; j++){
        rowSum += matrix[i][j];
        sums[i + 1][j + 1] = sums[i][j + 1] + rowSum;
      }
    }

  }

  public int sumRegion(int row1, int col1, int row2, int col2) {
    return sums[row2 + 1][col2 + 1] - sums[row1 ][col2 + 1] - sums[row2 +1][col1] + sums[row1 ][col1 ];

//    if(col1 > 0 && row1 > 0) {
//      return sums[row2][col2] - sums[row1 - 1][col2] - sums[row2][col1 - 1] + sums[row1 - 1][col1 - 1];
//    } else if(row1 == 0 && col1 != 0){
//      return sums[row2][col2] - sums[row1][col2 - 1] ;
//    } else if(row1 != 0 && col1 == 0){
//      return sums[row2][col2] - sums[row1 - 1][col2];
//    } else {
//      return sums[row2][col2];
//    }
  }
}
