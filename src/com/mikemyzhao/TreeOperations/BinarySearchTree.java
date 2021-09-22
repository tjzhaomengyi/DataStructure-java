package com.mikemyzhao.TreeOperations;

import com.mikemyzhao.trees.TreeNode;
import sun.reflect.generics.tree.Tree;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-10 19:29
 * @Description:二叉搜索树，任意节点的值大于左子树的值，小于等于右子树的值
 * 注意：因为节点需要大于左子树的所有节点，小于右子树的所有节点，不能有特殊情况,比如
 *            10
 *       5           15
 *               6       20
 * 这个时候6小于10，应该在10的左边，这种情况就是错误的
 * 所以要添加辅助节点max和min来存储当前节点的最大节点和最小节点
 */
public class BinarySearchTree {
  /**BST遍历框架**/
  void BST(TreeNode root,int target){
    if(root.val == target){}
    if(root.val<target){BST(root.right,target);}
    if(root.val>target){BST(root.left,target);}
  }


  /**判断是否是一个BST**/
  boolean isValidBST(TreeNode root){
    return isValidBST(root,null,null);
  }
  boolean isValidBST(TreeNode root,TreeNode min, TreeNode max){
    if(root == null) return true;
    if(min != null && root.val<min.val) return false;
    if(min != null && root.val>max.val) return  false;
    return isValidBST(root.left,min,root) && isValidBST(root.right,root,max);
  }

  /**在BST中查找一个数是否存在**/
  boolean isInBST(TreeNode root,int target){
    if(root==null) return  false;
    if(root.val==target) return true;
//    return isInBST(root.left,target) || isInBST(root.right,target);//递归
    if(root.val<target){
      return isInBST(root.right,target);
    }
    if(root.val>target){
      return isInBST(root.left,target);
    }
    return false;
  }

  /**在bst插入一个数，函数要返回treenode类型**/
  TreeNode insertIntoBST(TreeNode root,int val){
    if(root == null){return new TreeNode(val);}
    if(root.val == val){return root;}
    if(root.val<val){
      //插入的值大于root.val的值，往右边插
      root.right = insertIntoBST(root.right,val);
    }
    if(root.val>val){
      //插入的值小于root.val往左边插
      root.left = insertIntoBST(root.left,val);
    }
    return root;
  }

  /**在BST中删除一个数**/
  TreeNode deleteNode(TreeNode root,int key){
    if(root.val == key){
      //找到啦删除
      /**1、删除节点在最后，后面没有了
       * 2、删除后面有左子树或者右子树，往上提一下，直接返回
       * 3、删除的节点左右子树全有，需要找到右子树的最小节点，返回**/
      if(root.left==null) return root.right;
      if(root.right==null) return root.left;
      TreeNode minNode = getMin(root.right);//从右子树中找最小的
      root.val = minNode.val;//该节点替换成右子树最小值
      root.right = deleteNode(root.right,minNode.val);//删除右子树中最小值的节点
    }else if(root.val>key){
      root.left = deleteNode(root.left,key);
    }else if(root.val<key){
      root.right = deleteNode(root.right,key);
    }
    return root;
  }

  private TreeNode getMin(TreeNode node) {
    while(node.left!=null) node=node.left;
    return node;
  }

}
