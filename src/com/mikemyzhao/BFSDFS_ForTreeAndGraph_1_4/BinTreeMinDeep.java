package com.mikemyzhao.BFS;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-03 10:55
 * @Description:求二叉树最小深度
 * @NO:LC111
 */
public class BinTreeMinDeep {
  public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }


    public int minDepth(TreeNode root) {
      if (root == null) return 0;
      Queue<TreeNode> q = new LinkedList<>();
      Set<TreeNode> visited = new HashSet<>();
      int deep = 1;
      q.offer(root);
      visited.add(root);
      while(!q.isEmpty()){
        int sz = q.size();
        for(int i=0;i<sz;i++){
          TreeNode cur = q.poll();
          if(cur.left == null && cur.right == null){
            return deep;
          }
          if(cur.left!=null) q.offer(cur.left);
          if(cur.right!=null) q.offer(cur.right);
        }
        deep++;
      }
      return deep;
    }
  }
