package com.book.point2offer;

import com.mikemyzhao.TreeOperations_2.trees.TreeNode;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-09 16:41
 * @Description:
 */
public class KthLargthestInBinaryTree_54 {
  //使用自定义中序遍历的倒序方法
  int k,res;
  public int kthLargest(TreeNode root, int k) {
    this.k=k;
    dfs(root);
    return res;
  }

  private void dfs(TreeNode node){
    if(node==null) return;
    dfs(node.right);
    if(k==0) return;
    k=k-1;
    if(k==0) res = node.val;
    dfs(node.left);

  }

}
