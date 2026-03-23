package com.niuke.BM101.tree;

public class BM32_mergeTrees {

    public class TreeNode {
      int val = 0;
      TreeNode left = null;
      TreeNode right = null;
      public TreeNode(int val) {
        this.val = val;
      }
    }

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2){
        if(t1 == null) return t2;
        if(t2 == null) return t1;
        t1.val += t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;

    }
}
