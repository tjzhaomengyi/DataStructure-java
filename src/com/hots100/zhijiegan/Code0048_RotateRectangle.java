package com.hots100.zhijiegan;

/**
 * @Author: zhaomengyi
 * @Date: 2022-12-19 6:26 下午
 * @Description:
 */
public class Code0048_RotateRectangle {
  //儿童趣味益智：使用分组，每组四个值之间进行旋转，然后每个正方向两个角坐标确定一个正方形
  public void rotate(int[][] matrix) {
    if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0){
      return;
    }
    int a = 0;
    int b = 0;
    int c = matrix.length - 1;
    int d = matrix[0].length - 1;
    while(a < c){
      rotateEdge(matrix, a++, b++, c--, d--);
    }
  }
  public void rotateEdge(int[][] m, int a, int b, int c, int d){
    int tmp = 0;
    for(int i = 0; i < d - b; i++){
      tmp = m[a][b + i];
      m[a][b + i] = m[c - i][b];
      m[c - i][b] = m[c][d - i];
      m[c][d - i] = m[a + i][d];
      m[a + i][d] = tmp;
    }
  }
}
