package com.point2offer;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-04 12:12
 * @Description:
 */
public class FindNumberIn2DArray_04 {
  public boolean findNumberIn2DArray(int[][] matrix, int target) {
    //使用二分查找法，把二维数组转换成一维数组
    if(matrix == null || matrix.length==0){
      return false;
    }
    int m = matrix.length;//行数
    int n = matrix[0].length;//列数
    int row=0,col=n-1;
    //从右上角开始走
    while(row<m && col>=0){
      if(matrix[row][col]>target){
        col--;
      }else if(matrix[row][col]<target) {
        row++;
      }else{
        return true;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    int[][] martix = {{1,4},{2,5}};
    System.out.println(new FindNumberIn2DArray_04().findNumberIn2DArray(martix,2));
  }
}
