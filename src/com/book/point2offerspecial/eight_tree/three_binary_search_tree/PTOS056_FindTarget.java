package com.book.point2offerspecial.eight_tree.three_binary_search_tree;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-12 10:29
 * @Description:
 */
public class PTOS056_FindTarget {
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
  public boolean findTarget(TreeNode root, int k) {
    //技巧：用一下借助stack代替递归的中序遍历
    Stack<TreeNode> stack = new Stack<>();
    TreeNode cur = root;
    Set<Integer> set = new HashSet<>();
    //技巧：下面几行就是模板
    while(cur != null || !stack.isEmpty()){
      while(cur != null){
        stack.push(cur);//注意：stack的push操作只对cur节点，到左侧和右侧节点只移动cur，不做push操作
        cur = cur.left;//dfs
      }
      //技巧：【模板】处理当前节点
      cur = stack.pop();//弹出左边一直到中间
      if (set.contains(k - cur.val)){
        return true;
      }
      set.add(cur.val);
      cur = cur.right;//技巧：【模板】右边走
    }
    return false;
  }
}
