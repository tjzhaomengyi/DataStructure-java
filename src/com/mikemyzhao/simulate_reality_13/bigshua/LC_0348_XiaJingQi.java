package com.mikemyzhao.simulate_reality_13.bigshua;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-18 22:01
 * @Description:
 */
public class LC_0348_XiaJingQi {
  class TicTacToe {
    private int[][] rows;
    private int[][] cols;
    private int[] leftUp;
    private int[] rightUp;
    private boolean[][] matrix;
    private int N;

    public TicTacToe(int n) {
      //两个人下棋，0不用，1表示1号小人，2表示2号小人
      rows = new int[n][3];
      cols = new int[n][3];
      //左对角线下了几个
      leftUp = new int[3];
      //右对角线下了几个
      rightUp = new int[3];
      matrix = new boolean[n][n];
      N = n;
    }
    //走完这步谁赢
    public int move(int row, int col, int player){
      if(matrix[row][col]){
        return 0;//【row，col】位置有棋子，没人赢
      }
      matrix[row][col] = true;
      rows[row][player]++;
      cols[col][player]++;
      if(row == col){
        leftUp[player]++;
      }
      if(row + col == N - 1){
        rightUp[player]++;
      }
      if(rows[row][player] == N || cols[col][player] == N || leftUp[player] == N || rightUp[player] == N){
        return player;//当前这个玩家已经有一条线连成功了
      }
      return 0;
    }
  }

}
