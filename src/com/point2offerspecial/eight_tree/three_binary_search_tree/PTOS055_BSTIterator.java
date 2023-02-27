package com.point2offerspecial.eight_tree.three_binary_search_tree;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-11 18:14
 * @Description: 这题就是直接遍历完把结果放在队列中
 */
public class PTOS055_BSTIterator {
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

  LinkedList<TreeNode> ans;
  public PTOS055_BSTIterator(TreeNode root) {
    ans = new LinkedList<>();
    dfs(root, ans);
  }

  public void dfs(TreeNode x, LinkedList<TreeNode> ans){
    if(x == null) return;
    dfs(x.left, ans);
    ans.add(x);
    dfs(x.right, ans);
  }

  public int next() {
    return ans.poll().val;
  }

  public boolean hasNext() {
    return !ans.isEmpty() ;
  }
}
