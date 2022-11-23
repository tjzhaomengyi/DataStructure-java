package com.mikemyzhao.UnionFind_2_3.bigshua;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-02 16:15
 * @Description:一个矩阵中有两块分割的1，求两块区域连通起来最短的距离，只能横竖连
 */
public class TwoBlockShortestBridge_18 {
  //把已知的联通区域向外进行扩散，然后在相交的地方求得距离
  //扩散的时候把自己周围的1标记为2，自己本身为1，然后再从2扩散的时候将外围的1设置为3以此类推，然后矩阵中两个位置相加减3就是最小距离

  public static int shortestBridge(int[][] m){
    //将二维数组变成一维数组
    int N = m.length;
    int M = m[0].length;
    int all = N * M;
    int island = 0;
    int[] curs = new int[all];
    int[] nexts = new int[all];
    int[]
  }

  public static int getIndex(int i, int j, int col){
    return i * col + j;
  }
}
