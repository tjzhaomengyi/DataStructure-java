package com.book.point2offerspecial.eight_tree.three_binary_search_tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-10 17:24
 * @Description:
 */
public class PTOS052_IncreasingTravse {
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
  public TreeNode increasingBST(TreeNode root) {
    List<Integer> ans = new ArrayList<>();
    inorder(root, ans);
    TreeNode dummpy = new TreeNode(-1);
    TreeNode cur = dummpy;
    for(Integer val : ans){
      TreeNode n = new TreeNode(val);
      cur.right = n;
      cur = n;
    }
    return dummpy.right;
  }

  public void inorder(TreeNode x, List<Integer> ans){
    if(x == null) return;
    inorder(x.left, ans);
    ans.add(x.val);
    inorder(x.right, ans);
  }
}
