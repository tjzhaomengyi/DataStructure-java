package com.hots100.binarytree;

import java.util.ArrayList;

/**
 * @Author: zhaomengyi
 * @Date: 2022-12-05 8:03 下午
 * @Description:
 */
public class Code0114_BinaryTreeToList {
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


  public void flatten(TreeNode root) {
    if(root == null) return;
    ArrayList<TreeNode> tmp = new ArrayList<>();
    process(root, tmp);

    for(int i = 1; i < tmp.size(); i++){
      TreeNode pre = tmp.get(i - 1);
      pre.left = null;
      pre.right = tmp.get(i);
    }
  }


  public void process(TreeNode root, ArrayList<TreeNode> tmp){
    if(root == null) return;
    tmp.add(root);
    process(root.left, tmp);
    process(root.right, tmp);
  }

}
