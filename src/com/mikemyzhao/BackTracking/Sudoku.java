package com.mikemyzhao.BackTracking;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-13 9:23
 * @Description:数独问题，
 */
public class Sudoku {
  boolean backtrack(char[][] board,int i,int j){
    int m =9,n=9;
    if(j==n){
      //穷举到最后一行，换到下一行从新开始
      return backtrack(board, i + 1, 0);
    }
    if(i==m){
      //找到一个可行解，触发base case
      return true;
    }
    if(board[i][j]=='.'){
      //已经填写数字继续
      return backtrack(board,i,j+1);
    }
    //回溯关键模板在这里
    for(char ch='1';ch<='9';ch++){
      //如果遇到不合法的数字跳过
      if (!isValid(board,i,j,ch)){
        continue;
      }
      board[i][j]=ch;//回溯模板：做选择
      //如果找到一个可行解，立即结束
      if(backtrack(board,i,j+1)){//回溯模板：继续穷举下一个位置
        return true;
      }
      board[i][j]='.';//回溯模板：撤销选择
    }
    //穷举完1-9没有可行解，需要前面的各自换个数字穷举
    return false;

  }

  //判断board[r][c]是否可以填入数字n
  private boolean isValid(char[][] board, int r, int c, char n) {
    for(int i=0;i<9;i++){
      //判断行是否存在重复
      if(board[r][i]==n) return false;
      //判断是否存在重复
      if(board[i][c]==n) return false;
      //判断3*3方格是否存在重复
      if(board[(r/3)*3+i/3][(c/3)*3+i%3]==n){
        return false;
      }
    }
    return true;
  }
}
