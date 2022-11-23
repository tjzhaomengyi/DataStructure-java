package com.mikemyzhao.simulate_reality_13.bigshua;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-16 10:52
 * @Description:
 */
public class LC_0036_IsValidSudoku {
  //判断是否是合理的数独初始化
  public static boolean isValidSudoku(char[][] board) {

    boolean[][] row = new boolean[9][10];
    boolean[][] col = new boolean[9][10];
    boolean[][] bucket = new boolean[9][10];//每3*3看成一个桶
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        //技巧:计算桶号
        int bid = 3 * (i / 3) + (j / 3);//数学结论:通过i，j位置计算自己对应的桶号
        if(board[i][j] != '.'){
          int num = board[i][j] - '0';//当前棋盘中的数是多少
          if(row[i][num] || col[j][num] || bucket[bid][num]){
            return false;
          }
          row[i][num] = true;
          col[j][num] = true;
          bucket[bid][num] = true;
        }
      }
    }
    return true;
  }
}
