package com.book.point2offer;

import com.mikemyzhao.TreeOperations_2.trees.TreeNode;

import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-07 12:10
 * @Description:从上到下按照层级打印树,这个明显的广度优先遍历BFS
 * @Solve：BFS模板题
 */
public class LevelOrder_32 {
  //打印在一个一维数组
  public int[] levelOrder(TreeNode root) {
    if (root == null) return new int[0];
    Queue<TreeNode> q = new LinkedList<>();//使用队列保存遍历树的节点
    q.offer(root);
    ArrayList<Integer> visited = new ArrayList<>();//保存已经走过的节点,这个就是最终的结果
//      visited.add(root.val);
    while (!q.isEmpty()) {
      TreeNode cur = q.poll();
      visited.add(cur.val);
      if (cur.left != null) {
        q.offer(cur.left);
      }
      if (cur.right != null) {
        q.offer(cur.right);
      }
    }
    int res[] = new int[visited.size()];
    for (int i = 0; i < visited.size(); i++) {
      res[i] = visited.get(i);
    }
    return res;
  }

  //打印在一个多维数组中
  public List<List<Integer>> levelOrder_II(TreeNode root) {
      if(root==null) return new ArrayList<>();
      //模板第一部分：背下来
      Queue<TreeNode> q=new LinkedList<>();//注意：临时队列放要访问的根节点，注意这个队列的特性就是每次都存该层级的节点，然后在打印或者其他操作再弹出q.poll()
      q.offer(root);
      List<List<Integer>> visited = new ArrayList<>();

      while(!q.isEmpty()){
        List tmp = new ArrayList();
        int qsize = q.size();//注意：在for循环中动态变化的集合，在取大小的时候要先取，遍历当前集合的大小！
        for(int i=0;i<qsize;i++) {//这个时候q队列中存的就是该行的元素，所以挨个弹出遍历即可
          TreeNode cur = q.poll();
          tmp.add(cur.val);
          //添加下一层的节点
          if(cur.left!=null){
            q.add(cur.left);
          }
          if(cur.right!=null){
            q.add(cur.right);
          }
        }
        visited.add(tmp);
      }
    return visited;

  }

  //之字打印，无聊
  public List<List<Integer>> levelOrder_III(TreeNode root) {
    Queue<TreeNode> queue = new LinkedList<>();
    List<List<Integer>> res = new ArrayList<>();
    if(root != null) queue.add(root);
    while(!queue.isEmpty()) {
      LinkedList<Integer> tmp = new LinkedList<>();
      for(int i = queue.size(); i > 0; i--) {
        TreeNode node = queue.poll();
        if(res.size() % 2 == 0) tmp.addLast(node.val); // 偶数层 -> 队列头部
        else tmp.addFirst(node.val); // 奇数层 -> 队列尾部
        if(node.left != null) queue.add(node.left);
        if(node.right != null) queue.add(node.right);
      }
      res.add(tmp);
    }
    return res;
  }


  /**
   * BFS板子
   **/
  int BFS(TreeNode start, TreeNode target) {
    Queue<TreeNode> q = null;//遍历树的核心数据结构
    Set<TreeNode> visited = null;//记录遍历的节点，避免回头路

    q.offer(start);//起点计入队列
    visited.add(start);
    int step = 0;//记录扩散步数

    while (!q.isEmpty()) {
      int sz = q.size();
      /**将当前队列中所有节点向四周扩散**/
      for (int i = 0; i < sz; i++) {
        TreeNode curr = q.poll();
        /**这里是重点判断是否到达条件**/
        if (curr == target)
          return step;
        for (TreeNode x : curr.getNeighbhood()) {
          if (!visited.contains(x)) {
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
