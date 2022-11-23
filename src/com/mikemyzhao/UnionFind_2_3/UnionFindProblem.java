package com.mikemyzhao.UnionFind_2_3;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-14 16:29
 * @Description:使用unionfind可以解决的问题
 */
public class UnionFindProblem {
  /**1、连通棋问题
   * 找到M*N矩阵中四面被X围住的O，并将它们替换成X
   * **/
  //DFS解法
  void dfs(char[][] board,int i,int j){
    int m=board.length,n=board[0].length;
    //y越界返回
    if(i<0||i>=m||j<0||j>=n){
      return;
    }
    if(board[i][j]!='O'){
      return;
    }
    //进行替换
    board[i][j]='X';
    dfs(board,i+1,j);
    dfs(board,i,j+1);
    dfs(board,i-1,j);
    dfs(board,i,j-1);
  }

  void solve(char[][] board){
    if(board.length==0) return;
    int m = board.length,n=board[0].length;
    //把第一行和最后一行关联的O变成X
    for(int i=0;i<m;i++){
      dfs(board,i,0);
      dfs(board,i,n-1);
    }
    //把第一列和最后一列关联的0变成X
    for(int j=0;j<n;j++){
      dfs(board,0,j);
      dfs(board,m-1,0);
    }
    //剩下的O这样替换
    for (int i=1;i<m-1;i++){
      for(int j=1;j<n-1;j++){
        if(board[i][j]=='O'){
          board[i][j]='X';
        }
      }
    }
    //把所有字符X恢复成O
    for (int i=1;i<m-1;i++){
      for(int j=1;j<n-1;j++){
        if(board[i][j]=='X'){
          board[i][j]='O';
        }
      }
    }
  }

  //使用unionfind解决，起始没什么用
  void solveUF(char[][] board) {
    if (board.length==0) return;
    int m= board.length;
    int n = board.length;
    //给dummy预留位置
    UnionFind uf =  new UnionFind(m*n+1);
    int dummy = m*n;
    //将首列和末列的0与dummy相连
    for(int i=0;i<m;i++){
      if(board[i][0]=='0'){
        uf.union(i*n,dummy);
      }
      if(board[i][n-1]=='0'){
        uf.union(i*n+n-1,dummy);
      }
    }
    //将首行和末行的0与dummy相连
    for(int j=0;j<n;j++){
      if(board[0][j]=='O'){
        uf.union(j,dummy);
      }
      if(board[m-1][j]=='O'){
        uf.union(n*(m-1)+j,dummy);
      }
    }

    //方向数组d是搜富哦上下左右常用的手法
    int[][] d=new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
    for(int i=1;i<m-1;i++){
      for(int j=1;j<n-1;j++){
        if(board[i][j]=='O'){
          //将此O与上下左右O连通
          for (int k=0;k<4;k++){
            int x = i+d[k][0];
            int y = j+d[k][1];
            if(board[x][y]=='O'){
              uf.union(x*n+y,i*n+j);
            }
          }
        }
      }
    }

    //现在没有被X包围的O都和Dummy连通了，所有不和dummy连通的O都要被替换
    for (int i=1;i<m-1;i++){
      for(int j=1;j<n-1;j++){
        if(!uf.connected(dummy,i*n+j)){
          board[i][j]='x';
        }
      }
    }
  }

  /**例子3、判断等式是否合法
   * [a==b,b!=c,c==a] fasle
   * [c==c,b==d,x!=z] true
   * **/
  boolean equantValid(String[] equs){
    UnionFind uf = new UnionFind(26);
    for(String eq:equs){
      if(eq.charAt(1)=='='){
        char x = eq.charAt(0);
        char y = eq.charAt(3);
        uf.union(x-'a',y='a');
      }
    }
    for(String eq:equs){
      if(eq.charAt(1)=='!'){
        char x = eq.charAt(0);
        char y = eq.charAt(3);
        //如果相等关系成立，就是逻辑冲突
        if(uf.connected(x-'a',y-'a')){
          return false;
        }
      }
    }
    return true;
  }


}
