package com.point2offer;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-05 9:48
 * @Description:在board中是否能找出word
 * @Solve:两种方法(1)DFS+剪枝(2)回溯
 */
public class MatrixRoad_12 {
  /**使用递归+剪枝
   * 1、终止条件：
   * (1)返回：false
   * 递归参数当前元素在矩阵中行列索引i和j，当前目标字符在word中的索引k
   * 终止条件：(1)行或列越界(2)当前矩阵元素与目标字符不同(3)当前矩阵元素访问过
   * (2)返回:true
   * k=len(word)-1,即字符串word全部匹配
   * 2、递推工作：
   *  (1)将board[i][j]修改为空字符‘’，代表访问过。
   *  (2)按照上下左右搜索
   *  (3)还原当前矩阵元素，当board[i][j]还原至初始值，word[k]
   *
   * **/
  public boolean exist(char[][] board, String word) {
    char[] words = word.toCharArray();
    for(int i=0;i<board.length;i++){
      for(int j=0;j<board[0].length;j++){
        if(dfs(board,words,i,j,0)) return true;
      }
    }
    return false;
  }
  //辅助dfs函数,只针对矩阵中当前数字进行递归，所以在执行的时候需要两层遍历一下！
  private boolean dfs(char[][] board,char[] word,int i,int j,int k){
    //false条件
    if(i<0 || i>=board.length || j<0 || j>=board[0].length || board[i][j]!=word[k]) return false;
    if(k==word.length-1) return true;
    board[i][j]='\0';
    boolean res = dfs(board,word,i-1,j,k+1)||dfs(board,word,i+1,j,k+1)||dfs(board,word,i,j-1,k+1)||dfs(board,word,i,j+1,k+1);
    board[i][j]=word[k];//????因为上面都是没有找到的条件，所以只要没有就返回，递归到这一步肯定是找到了元素，所以可以用word[k]直接进行还原！！！
    return res;
  }
}
