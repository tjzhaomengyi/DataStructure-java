package com.point2offerspecial.eight_tree.one_dfs;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-08 11:46
 * @Description:
 */
public class DFSByStack {
  public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
  public List<Integer> inorderTraversal(TreeNode root) {
    //技巧：不使用递归， 借助stack完成树的中序遍历
    List<Integer> ans = new LinkedList<>();
    Stack<TreeNode> stack = new Stack<>();
    TreeNode cur = root;
    while(cur != null || !stack.isEmpty()){
      while(cur != null){//如果cur不为空就把当前和左边的往里塞
        stack.push(cur);//先把当前节点塞进去
        cur = cur.left;
      }
      cur = stack.pop();
      ans.add(cur.val);
      cur = cur.right;
    }
    return ans;
  }
}
