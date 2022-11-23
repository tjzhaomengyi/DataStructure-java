package com.mikemyzhao.TreeOperations;

import com.mikemyzhao.trees.TreeNode;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-10 19:23
 * @Description:热身例子
 */
public class TreeForWarmUp {
  /**
   * 1、将树的元素加1
   **/
  void plusOneTree(TreeNode root) {
    if (root == null) return;
    root.val += 1;
    plusOneTree(root.left);
    plusOneTree(root.right);
  }

  /**2、判断两棵二叉树是否相同**/
  boolean isSame(TreeNode root1,TreeNode root2){
    //都为空的话，相同
    if(root1==null && root2==null) return true;
    if(root1==null || root2==null) return false;
    //值不相同不行
    if(root1.val != root2.val) return false;
    //递归比子节点
    return isSame(root1.left,root2.left) && isSame(root1.right,root2.right);
  }

}
