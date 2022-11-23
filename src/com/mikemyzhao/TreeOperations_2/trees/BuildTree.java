package com.mikemyzhao.TreeOperations_2.trees;

import java.util.Map;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-02 17:27
 * @Description:给定一棵树的前序遍历 preorder 与中序遍历  inorder。请构造二叉树并返回其根节点。
 * @Solution:使用前序遍历
 */
public class BuildTree {
  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  class Solution {
    public TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, Map<Integer, Integer> inMap) {
      if (preStart > preEnd || inStart > inEnd) return null;
      //前序遍历
      TreeNode root = new TreeNode(preorder[preStart]);
      int inRoot = inMap.get(root.val);
      int numsLeft = inRoot - inStart;

      root.left = buildTree(preorder, preStart + 1, preStart + numsLeft, inorder, inStart, inRoot - 1, inMap);
      root.right = buildTree(preorder, preStart + numsLeft + 1, preEnd, inorder, inRoot + 1, inEnd, inMap);
      return root;
    }
  }
}
