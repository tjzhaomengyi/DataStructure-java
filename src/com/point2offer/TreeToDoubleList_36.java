package com.point2offer;

import com.mikemyzhao.TreeOperations_2.trees.TreeNode;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-08 10:26
 * @Description:将二叉搜索树转换成循环链表，不能复制，只变指针
 * @Solve:二叉搜索树的中序遍历是一个有序的遍历【保证出来有序】+构建双向链表
 */
public class TreeToDoubleList_36 {

  TreeNode pre,head;//要从head开始走！
  public TreeNode treeToDoublyList(TreeNode root) {
    if(root==null) return null;
    dfs(root);
    //最后pre肯定指向的是尾部节点，所以
    pre.right=head;
    head.left=pre;
    return head;
  }

  //注意：中序遍历的一个实用
  private void dfs(TreeNode cur){
    if(cur==null) return;
    //递归左子树
    dfs(cur.left);
    //当前节点的操作
    if(pre!=null){
      pre.right=cur;//前一个指向当前的
    }else{
      head=cur;
    }
    cur.left=pre;
    pre=cur;//注意：一定要把pre指向当前节点
    dfs(cur.right);
  }
}

