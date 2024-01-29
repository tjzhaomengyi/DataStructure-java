package com.book.point2offer;


import com.mikemyzhao.TreeOperations_2.trees.TreeNode;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-06 16:07
 * @Description:判断B是否是A的子树
 */
public class IsSubTree_26 {
  public boolean isSubStructure(TreeNode A, TreeNode B) {
    if(A==null||B==null) return false;
    if(A.val==B.val && (helper(A.left,B.left)&&helper(A.right,B.right))){
      return true;
    }
    return(isSubStructure(A.left,B)||isSubStructure(A.right,B));

  }

  //判断A中的子树是否包含B
  private boolean helper(TreeNode r1,TreeNode r2){
    //终止条件：(1)当节点B为空说明B已经遍历完成(2)A为空了，说明B超过了A的限制(3)A和B的值不同
    if(r2==null) return true;
    if(r1==null) return false;
    if(r1.val==r2.val){
      return helper(r1.left,r2.left) && helper(r1.right,r2.right);
    }else{
      return false;
    }
  }
}
