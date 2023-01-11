package com.hotinterview.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2022-12-27 5:25 下午
 * @Description:
 */
public class Code0230_KSmallestElement {
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

  //思路：搜索二叉树中序遍历
  public int kthSmallest(TreeNode root, int k) {
    List<Integer> res = new ArrayList<>();
    process(root, res, k);
    return res.get(res.size() - 1);
  }

  public void process(TreeNode root,List<Integer> res, int k){
    if(root == null) return;
    process(root.left, res, k);
    if(res.size() == k){
      return;
    }
    res.add(root.val);
    process(root.right, res, k);
  }


}
