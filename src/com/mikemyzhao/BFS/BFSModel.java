package com.mikemyzhao.BFS;

import com.mikemyzhao.list.Node;
import com.mikemyzhao.trees.TreeNode;

import java.util.Queue;
import java.util.Set;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-03 9:52
 * @Description:BFS模板
 */
public class BFSModel {
  int BFS(TreeNode start,TreeNode target){
    Queue<TreeNode> q=null;//遍历树的核心数据结构
    Set<TreeNode> visited=null;//记录遍历的节点，避免回头路

    q.offer(start);//起点计入队列
    visited.add(start);
    int step = 0;//记录扩散步数

    while(!q.isEmpty()){
      int sz = q.size();
      /**将当前队列中所有节点向四周扩散**/
      for(int i=0;i<sz;i++){
        TreeNode curr = q.poll();
        /**这里是重点判断是否到达条件**/
        if(curr == target)
          return step;
        for(TreeNode x:curr.getNeighbhood()){
          if(!visited.contains(x)){
            q.offer(x);
            visited.add(x);
          }
        }
      }
      step++;
    }
    return step;
  }
}
