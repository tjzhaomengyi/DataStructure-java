package com.mikemyzhao.TreeOperations;

import com.mikemyzhao.trees.TreeNode;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-10 21:42
 * @Description:三种完美的二叉树结构
 * (1)Complete Binary Tree完全二叉树，结构是节点全靠近左子树
 *            0
 *       1        2
 *    3     4   5
 * (2)Perfect Binary Tree满二叉树，结构是全有左右子节点
 *          0
 *      1       2
 *   3    4   5   6
 *   (3)Full Binary Tree这个玩意不知道中文，就是一个节点要么没有左右子树，要么都有左右子节点
 *          0
 *     1        2
 *  3     4
 *
 */
public class CompletePerfectFullTree {
  /**求普通二叉树节点个数**/
  int countNodesBinTree(TreeNode root){
    if(root == null){
      return 0;
    }
    return 1+countNodesBinTree(root.left)+countNodesBinTree(root.right);
  }

  /**求满二叉树节点个数**/
  int countNodesPerfectTree(TreeNode root){
    int h=0;
    while(root!=null){
      root = root.left;
      h++;
    }
    return (int)Math.pow(2,h)-1;
  }
  /**求完全二叉树节点个数,完全二叉树可以缩短计算时间，如果两边相同，那么计算时间O(logn),
   * 普通树话就是高度计算时间O(logn)*每次递归的时间O(logn)，一共O(lognlogn)**/
  int countNodesCompleteTree(TreeNode root){
    TreeNode l = root,r=root;
    //记录左右子树的高度
    int hl = 0,hr=0;
    while(l!=null){
      l=l.left;
      hl++;
    }
    while(r!=null){
      r=r.right;
      hr++;
    }
    //如果左右子树高度相同，那么就是满二叉树
    if(hl==hr){
      return (int)Math.pow(2,hl)-1;
    }
    //如果不同按照一般计算
    return 1+countNodesBinTree(root.left)+countNodesBinTree(root.right);
  }
}
