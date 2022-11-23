package com.mikemyzhao.TreeOperations_2.bigshua;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-17 9:10
 * @Description:判断是否是搜索二叉树
 */
public class LC_0098_ValidBinarySearchTree {
  //技巧:Info类包装技巧
  public static class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
  }

  public boolean isValidBST(TreeNode root){
    return isValidBST(root);
  }

  TreeNode last = null;//技巧:用last记录上一个root节点
  //利用中序遍历判断是否是BST
  public  boolean isValid(TreeNode root){
    if(root == null){
      return true;
    }
    //左子树
    boolean left = isValid(root.left);
    //中路部分，技巧:根节点的事情都处理完再处理右节点！
    if(last != null){
      if(last.val >= root.val){
        return false;
      }
    }
    last = root;//技巧:此时要把root给到last，才能让right部分和当前的root比较，操！！
    //右子树
    boolean right = isValid(root.right);
    return left && right;
  }
}
