package com.hots100.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2022-12-02 10:19 下午
 * @Description:
 */
public class Code0094_InorderTravel {
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
  List<Integer> res = new ArrayList<Integer>();
  public List<Integer> inorderTraversal(TreeNode root) {
    if(root == null) return res;
    inorderTraversal(root.left);
    res.add(root.val);
    inorderTraversal(root.right);
    return res;
  }
}
