package com.mikemyzhao.SomeSkills_0.GanRanWenTi;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-17 15:43
 * @Description:如果o被八个方向被x包围，把o变成x
 */
public class LC_0130_ChangeSurroundedRegions {
  //注意:只有上下左右四个方向有影响
  //技巧:从四周开始把边界的o找出来，然后把这些o连接的内部o找出来，只要他们和边界的o是联通的，那么它们就不会变x。把这些o标记成f。然后把里面剩下的o变x即可
  public static void solve(char[][] board){
    if(board == null || board.length == 0 || board[0] == null || board[0].length == 0){
      return;
    }
    int N = board.length;
    int M = board[0].length;
    //技巧:1先搞定四个边和中间联通的
    for(int j = 0; j < M; j++){
      if(board[0][j] == 'O'){
        free(board,0, j);
      }
      if(board[N - 1][j] == 'O'){
        free(board, N - 1, j);
      }
    }
    for(int i = 1; i < N; i++){
      if(board[i][0] == 'O'){
        free(board, i, 0);
      }
      if(board[i][M - 1] == 'O'){
        free(board, i, M - 1);
      }
    }
    //技巧:把中间不是f和x的O给变了,把f再恢复成原来的O
    for(int i = 0; i < N; i++){
      for(int j = 0; j < M; j++){
        if(board[i][j] == 'O'){
          board[i][j] = 'X';
        }
        if(board[i][j] == 'F'){
          board[i][j] = 'O';
        }
      }
    }
  }

  public static void free(char[][] board, int i, int j){
    if(i < 0 || i == board.length || j < 0 || j == board[0].length || board[i][j] != 'O'){
      return;
    }
    board[i][j] = 'F';//边缘的
    //技巧:只有上下左右四个方向有影响
    free(board, i + 1, j);
    free(board, i - 1, j);
    free(board, i, j - 1);
    free(board, i, j + 1);
  }
}
