package com.mikemyzhao.TreeOperations;

import com.mikemyzhao.trees.TreeNode;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-12 10:05
 * @Description:两个节点的公共祖先
 */
public class CommonAncestor {
  /**
   * 1、所有树的问题都可以用遍历递归模板，写出两行代码
   * 2、这个函数干什么的，参数变量干什么的，得到函数的结果应该再干什么
   *  (1)下面的递归就是：如果p和q都在以root为根的树中，root是公共根节点
   *  (2)p和q都不在，返回null
   *  (3)如果p和q有一个在root根节点，函数返回那个节点
   *    一旦确定了这个函数递归的思路就要确定他的争取性，不怀疑；
   *  (4)通过p,q参数作为状态转移,
   *  (5)base case 如果root就是p或者q，显然root就是公共祖先。
   *  比如root就是p节点，如果q存在于以root为根的树中，显然root就是最近公共祖先，
   *  即使q不存在于以root为根的树中，
   *  这里从后往前找说明正好用的是后续遍历
   * **/
  TreeNode lowestCommonAncestor(TreeNode root,TreeNode p,TreeNode q){
    //base case
    if(root==null)return null;
    if(root==p||root==q) return root;
    TreeNode left = lowestCommonAncestor(root.left,p,q);
    TreeNode right = lowestCommonAncestor(root.right,p,q);
    //递归要处理的
    //情况1、如果p和q都在以root为根的树中，那么left和right一定分别是p和q
    if(left!=null && right!=null){
      return root;
    }
    //情况2、如果p和q都不在以root为根的树中，直接返回null
    if(left==null&&right==null){
      return null;
    }
    //情况3、如果p和q只有一个存在于以root为根的树中，直接返回该节点
    return left == null?right:left;
  }
}
