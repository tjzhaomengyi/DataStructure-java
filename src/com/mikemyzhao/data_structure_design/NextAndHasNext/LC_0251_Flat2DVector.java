package com.mikemyzhao.data_structure_design.NextAndHasNext;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-18 15:20
 * @Description:
 */
public class LC_0251_Flat2DVector {
  public static class Vector2D {
    private int[][] matrix;
    private int row;
    private int col;
    private boolean curUse;//标记当前是否使用

    public Vector2D(int[][] v) {
      matrix = v;
      row = 0;
      col = -1;
      curUse = true;
      hasNext();
    }

    public boolean hasNext(){
      if(row == matrix.length){
        return false;
      }
      if(!curUse){//技巧:光标没有用就是有下一个
        return true;
      }
      //[row,col]这个数用过了
      if(col < matrix[row].length - 1){//技巧:
        col++;
      } else {
        col = 0;
        do{
          row++;
        }while(row < matrix.length & matrix[row].length == 0);
      }
      //新的(row,col)
      if(row != matrix.length){
        curUse = false;
        return true;
      } else {
        return false;
      }
    }
    public int next(){
      int ans = matrix[row][col];
      curUse = true;
      hasNext();
      return ans;
    }
  }
}
