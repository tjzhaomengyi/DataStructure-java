package com.book.point2offerspecial.seven_queue.two_BFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-08 09:46
 * @Description:
 */
public class PTOS045_FindBottomLeftValue {
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
  public int findBottomLeftValue(TreeNode root) {
    //技巧：使用两个队列进行访问，当一个空了的时候，说明下一个进来的第一个就是最左侧的结点
    Queue<TreeNode> q1 = new LinkedList<>();
    Queue<TreeNode> q2 = new LinkedList<>();
    q1.offer(root);
    int bottomLeft = root.val;
    while(!q1.isEmpty()){
      TreeNode node = q1.poll();
      if(node.left != null){
        q2.offer(node.left);
      }
      if(node.right != null){
        q2.offer(node.right);
      }
      if(q1.isEmpty()){
        q1 = q2;
        q2 = new LinkedList<>();
        if(!q1.isEmpty()){
          bottomLeft = q1.peek().val;
        }
      }
    }
    return bottomLeft;
  }

}
