package com.point2offer;

import com.mikemyzhao.TreeOperations_2.trees.TreeNode;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-06 19:06
 * @Description:判断是否是对称树
 */
public class IsSymmetricTree_28 {
  public boolean isSymmetric(TreeNode root) {
    if(root==null) return true;
    return dfs(root.left,root.right);
  }
  private boolean dfs(TreeNode r1,TreeNode r2){
    if(r1==null && r2==null) return true;//说明已经全是一样的了
    if(r1==null || r2==null) return false;//说明有剩下的
    return r1.val==r2.val && dfs(r1.left,r2.right) && dfs(r1.right,r2.left);
  }
}
