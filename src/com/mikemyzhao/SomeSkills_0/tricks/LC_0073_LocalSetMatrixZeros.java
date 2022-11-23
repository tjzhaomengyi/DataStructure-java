package com.mikemyzhao.SomeSkills_0.tricks;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-16 21:10
 * @Description:
 */
public class LC_0073_LocalSetMatrixZeros {
  //技巧:原地将矩阵置为0，不用额外的空间
  public static void setZeroes(int[][] matrix){
    boolean col0 = false;
    int i = 0;
    int j = 0;
    //技巧:用第一列表示这一行是否要全部刷为0，然后用一个变量col0表示第0列，是否要刷成0.如果不是就用第0行标记同样要刷成0的列
    for(i = 0; i < matrix.length; i++){
      for(j = 0; j < matrix[0].length; j++){
        if(matrix[i][j] == 0){
          matrix[i][0] = 0;
          if(j == 0){//如果0列出现刷0，标记col0变量
            col0 = true;
          } else{//对应列标记
            matrix[0][j] = 0;
          }
        }
      }
    }
    //刷0，技巧:不要刷第0列！！！，看下col标记
    for(i = matrix.length - 1; i >=0; i--){
      for(j = 1; j < matrix[0].length;j++){
        if(matrix[i][0] == 0 || matrix[0][j] == 0){
          matrix[i][j] = 0;
        }
      }
    }
    if(col0){
      for(i = 0; i < matrix.length;i++){
        matrix[i][0] = 0;
      }
    }
  }

}
