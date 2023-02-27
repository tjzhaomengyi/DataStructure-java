package com.point2offerspecial.seven_queue.two_BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-08 09:56
 * @Description:
 */
public class PTOS046_RightSideView {
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
  public List<Integer> rightSideView(TreeNode root) {
    Queue<TreeNode> q1 = new LinkedList<>();
    Queue<TreeNode> q2 = new LinkedList<>();
    List<Integer> ans = new ArrayList<>();
    if(root == null) return ans;
    q1.offer(root);
    while(!q1.isEmpty()){
      TreeNode node = q1.poll();
      if(node.left != null){
        q2.offer(node.left);
      }
      if(node.right != null){
        q2.offer(node.right);
      }

      if(q1.isEmpty()){
        ans.add(node.val);
        q1 = q2;
        q2 = new LinkedList<>();
      }
    }
    return ans;
  }

}
