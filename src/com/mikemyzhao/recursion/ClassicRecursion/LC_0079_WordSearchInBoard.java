package com.mikemyzhao.recursion.ClassicRecursion;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-16 21:42
 * @Description:
 */
public class LC_0079_WordSearchInBoard {
  //DFS深度优先遍历，Path记录
  public boolean exist(char[][] board, String word) {
    char[] w = word.toCharArray();
    for(int i = 0; i < board.length;i++){
      for(int j = 0; j < board[0].length; j++){
        if(f(board,i,j,w,0)){
          return true;
        }
      }
    }
    return false;
  }

  //技巧:当前在b[i][j]位置，从该位置能不能搞定word[k..]出发到结束的字符串
  public static boolean f(char[][] b, int i, int j, char[] w, int k){
    if (k == w.length){
      return true;
    }
    if(i < 0 || i == b.length || j < 0 || j == b[0].length){
      return false;
    }
    if(b[i][j] != w[k]){
      return false;
    }
    //上下左右去查吧
    //dfs标记出来
    char tmp = b[i][j];
    b[i][j] = 0;
    boolean ans = f(b,i-1,j,w,k+1) || f(b,i+1,j,w,k+1)||f(b,i,j-1,w,k+1)||f(b,i,j+1,w,k+1);
    //dfs恢复现场
    b[i][j] = tmp;
    return ans;
  }
}
