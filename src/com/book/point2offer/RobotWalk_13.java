package com.book.point2offer;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-05 10:24
 * @Description:地上有方格m*n个，机器人每次只能上下左右移动一个格子，但是也不能进入行列之和大于k的格子。
 * 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子
 *
 */
public class RobotWalk_13 {
  /**模板数位求和公式，只在1到100中使用
   *当(x+1)个位等于0，s(x+1)=s(x)-8,当(x+1)个位不等于0，s(x+1)=s(x)+1
   * **/
  int m,n,k;
  boolean[][] visited;
  public int movingCount(int m, int n, int k) {
    this.m=m;this.n=n;this.k=k;
    this.visited=new boolean[m][n];
    return dfs(0,0,0,0);
  }


  private int dfs(int i,int j,int si,int sj){
    if(i>=m || j>=n || k<si+sj || visited[i][j]) return 0;
    visited[i][j]=true;
    return 1 + dfs(i + 1, j, (i + 1) % 10 != 0 ? si + 1 : si - 8, sj) + dfs(i, j + 1, si, (j + 1) % 10 != 0 ? sj + 1 : sj - 8);

  }



  /**模板：求格子每位数字之和**/
  private int getCeilSum(int x){
    int s=0;
    while(x!=0){
      s+=x%10;
      x=x/10;
    }
    return s;
  }

}
