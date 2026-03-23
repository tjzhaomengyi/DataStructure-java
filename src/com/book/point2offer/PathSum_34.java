package com.book.point2offer;

import com.mikemyzhao.TreeOperations_2.trees.TreeNode;

import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-07 15:51
 * @Description:树的目标路径和,必须到叶子节点，中间的不可以
 */
public class PathSum_34 {
  //todo:值得重刷
  List<List<Integer>> res = new ArrayList();
  LinkedList<Integer> path = new LinkedList<>();

  public List<List<Integer>> pathSum(TreeNode root, int target) {
    if(root==null) return new ArrayList<>();
    recur(root,target);
    return res;
  }

  /**思路就是每遍历一个节点tar-节点的值**/
  private void recur(TreeNode root,int tar){
    if(root==null) return;
    path.add(root.val);
    tar=tar-root.val;
    if(tar==0 && root.left==null && root.right==null){
      res.add(new ArrayList<>(path));//注意：这里做一下深拷贝，因为这个path一直在变！
    }
    //注意：下面是不符合条件的情况，所以要把刚加进path的删除掉，不能带着这个错误值继续迭代！
    recur(root.left,tar);
    recur(root.right,tar);
    path.removeLast();
  }
}
