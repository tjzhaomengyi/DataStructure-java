package com.point2offerspecial.seven_queue.two_BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-07 18:44
 * @Description:
 */
public class PTOS044_MaximumInLevel {
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
  public List<Integer> largestValues(TreeNode root) {
    //技巧：使用current 和 next两个变量分别记录当前层节点的个数
    List<Integer> ans = new ArrayList<>();
    if(root == null) return ans;
    Queue<TreeNode> queue = new LinkedList<>();
    int current = 0;
    int next = 0;
    queue.offer(root);
    current = 1;
    int maxVal = Integer.MIN_VALUE;
    while(!queue.isEmpty()){
      TreeNode t = queue.poll();
      maxVal = Math.max(maxVal, t.val);
      current--;

      if(t.left != null){
        queue.offer(t.left);
        next++;
      }
      if(t.right != null){
        queue.offer(t.right);
        next++;
      }
      if(current == 0){
        ans.add(maxVal);
        maxVal = Integer.MIN_VALUE;
        current = next;
        next = 0;
      }
    }
    return ans;
  }
}
