package com.mikemyzhao.BFS;

import com.mikemyzhao.trees.TreeNode;

import java.util.Queue;
import java.util.Set;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-13 11:30
 * @Description:类华龙道问题，滑动拼图问题，给一组2*3数字,最后变为[1,2,3][4,5,0]为胜利,麻烦，简单看看
 */
public class Hualongdao {
  /**
   * 1、先从0挪动好位置即可
   * 2、将二维数组转换成一维度数组
   *
   *    2     4     1
   *    5     0     3
   *
   *    转换为一维数组：2 4 1 5 0 3
   *
   * **/
  int slidingPuzzle(int[][] board){
    /**直接搬用BFS模板代码**/
    int m=2,n=3;
    StringBuilder start = new StringBuilder();
    String target = "123450";
    //将2*3的数组转换成字符串
    for (int i=0;i<m;i++) {
      for (int j = 0; j < n; j++) {
        start.append(board[i][j] + '0');
      }
    }
      int[][] neighbor = new int[][]{
          {1,3},
          {0,4,2},
          {1,5},
          {0,4},
          {3,1,5},
          {4,2}
      };

    /****/
    Queue<String> q=null;//遍历树的核心数据结构
    Set<String> visited=null;//记录遍历的节点，避免回头路

    q.offer(start.toString());//起点计入队列
    visited.add(start.toString());
    int step = 0;//记录扩散步数

    while(!q.isEmpty()){
      int sz = q.size();
      /**将当前队列中所有节点向四周扩散**/
      for(int i=0;i<sz;i++){
        String curr = q.poll();
        /**这里是重点判断是否到达条件**/
        if(curr == target)
          return step;
        //找到数字0索引
        int idx =0;
        for(;curr.charAt(idx)!='0';idx++){
          for(int adj :neighbor[idx]){
            String new_board = curr;
            swapChar(new_board,adj,idx);
            if(!visited.contains(new_board)){
              q.add(new_board);
              visited.add(new_board);
            }
          }
        }
      }
      step++;
    }
    return -1;
  }
  String swapChar(String str,int a,int b){
    char[] newStr = str.toCharArray();
    newStr[a] = str.charAt(b);
    newStr[b]= str.charAt(a);
    return new String(newStr);
  }
}
