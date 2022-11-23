package com.mikemyzhao.TreeOperations_2.bigshua;


/**
 * @Author: zhaomengyi
 * @Date: 2022-03-18 10:11
 * @Description:根据先序遍历重建搜索二叉树 // 本题测试链接 : https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
 */
public class ConstructBSTFromPreorder {
  public static class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
      this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  public static TreeNode bstFromPreorder(int[] pre) {
    if (pre == null || pre.length == 0) {
      return null;
    }
    return process(pre, 0, pre.length - 1);
  }

  public static TreeNode process(int[] pre, int L, int R) {
    if (L > R) {
      return null;//这个就是判断左右都为空的情况
    }
    //遍历数组找到比[L]大的第一个数
    int firstBig = -1;
    for (; firstBig <= R; firstBig++) {
      if (pre[firstBig] > pre[L]) {
        break;
      }
    }
    TreeNode head = new TreeNode(pre[L]);
    head.left = process(pre, L + 1, firstBig - 1);
    head.right = process(pre, firstBig,R);
    return head;
  }
}

