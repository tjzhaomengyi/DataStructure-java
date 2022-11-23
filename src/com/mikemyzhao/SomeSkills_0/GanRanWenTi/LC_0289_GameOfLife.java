package com.mikemyzhao.SomeSkills_0.GanRanWenTi;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-18 17:56
 * @Description:
 */
public class LC_0289_GameOfLife {
  //技巧:用二进制位的最优一位表示当前和之前的状态
  public static void gameOfLife(int[][] board){
    int N = board.length;
    int M = board[0].length;
    for(int i = 0; i < N; i++){
      for(int j = 0; j < M; j++){
        int neighbors = neighbors(board, i, j);
        if(neighbors == 3 || (board[i][j] == 1 && neighbors == 2)){
          board[i][j] |= 2;//当前位置把bit为的倒数第2位记作当前状态
        }
      }
    }
    for(int i = 0; i < N; i++){
      for(int j = 0; j < M; j++){
        board[i][j] >>= 1;//所有状态右移1位
      }
    }
  }

  //判断当前位置的数周围有几个1
  public static int neighbors(int[][] b,int i, int j){
    return f(b, i - 1, j - 1)
        + f(b, i - 1, j)
        + f(b, i - 1, j + 1)
        + f(b, i, j - 1)
        + f(b, i, j + 1)
        + f(b, i + 1, j - 1)
        + f(b, i + 1, j)
        + f(b, i + 1, j + 1);
  }

  public static int f(int[][] b, int i, int j){
    return (i >= 0 && i < b.length && j >= 0 && j < b[0].length &&(b[i][j] & 1) == 1) ? 1 : 0;
  }
}
