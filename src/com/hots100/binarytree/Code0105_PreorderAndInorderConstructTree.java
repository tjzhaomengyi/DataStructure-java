package com.hots100.binarytree;

/**
 * @Author: zhaomengyi
 * @Date: 2022-12-05 3:07 下午
 * @Description:
 */
public class Code0105_PreorderAndInorderConstructTree {
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
  public TreeNode buildTree(int[] preorder, int[] inorder) {
    if(preorder == null | inorder == null || preorder.length != inorder.length){
      return null;
    }

    return f(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
  }

  public TreeNode f(int[] pre, int L1, int R1, int[] in, int L2, int R2){
    if(L1 > R1){return null;}//扣边界
    TreeNode head = new TreeNode(pre[L1]);
    if(L1 == R1){
      return new TreeNode(pre[L1]);
    }
    int find = L2;
    while(pre[L1] != in[find]){
      find++;
    }
    head.left = f(pre,L1 + 1, L1 + find - L2,in, L2, find -1);
    head.right = f(pre, L1 + find - L2 + 1, R1, in, find + 1, R2);
    return head;
  }
}
