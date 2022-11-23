package com.point2offer;

import com.mikemyzhao.TreeOperations_2.trees.TreeNode;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-12 15:44
 * @Description:
 */
public class LowestCommonAncestor_68_1 {
  //二叉搜索树
  public TreeNode lowestCommonAncestorBinSearch(TreeNode root, TreeNode p, TreeNode q) {
    if(root.val < p.val && root.val < q.val)
      return lowestCommonAncestor(root.right, p, q);
    if(root.val > p.val && root.val > q.val)
      return lowestCommonAncestor(root.left, p, q);
    return root;
  }

  //重温经典：二叉树的
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null || root == p || root == q) return root;
    TreeNode left = lowestCommonAncestor(root.left, p, q);
    TreeNode right = lowestCommonAncestor(root.right, p, q);
    if (left == null && right == null) return null; // 1.
    if (left == null) return right; // 3.
    if (right == null) return left; // 4.
    return root; // 2. if(left != null and right != null)
  }


}
