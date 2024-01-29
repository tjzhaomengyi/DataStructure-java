package com.book.point2offerspecial.eight_tree.three_binary_search_tree;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-10 19:43
 * @Description:
 */
public class PTOS053_InorderSuccessor {
  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
    //使用二叉搜索树的性质
    if (root == null) return null;
    TreeNode cur = root;
    TreeNode ans = null;
    while (cur != null) {
      //这种没脑子又简单的逼题最烦
      if (cur.val > p.val) {
        ans = cur;
        cur = cur.left;
      } else {
        cur = cur.right;
      }
    }
    return ans;
  }
}
