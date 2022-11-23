package com.mikemyzhao.TreeOperations_2.bigshua.traverse;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-17 10:08
 * @Description:
 */
public class LC_0103_ZigZagTree {
  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  //技巧:使用一个BFS，每批进入queue记录一个size，到size就开始zig横向打，然后再zag转到下一行
  //技巧:2双端队列，一边出另一边加，保证取的时候和加的时候互不干扰。。。。
  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> ans = new ArrayList<>();
    if(root == null){
      return ans;
    }
    LinkedList<TreeNode> dq = new LinkedList<>();
    dq.add(root);
    int size = 0;
    boolean isHeadPoll = true;
    while(!dq.isEmpty()){
      size = dq.size();//技巧:进去记录队列大小
      List<Integer> curLevel = new ArrayList<>();
      for(int i = 0; i < size; i++){
        TreeNode cur = isHeadPoll ? dq.pollFirst() : dq.pollLast();
        curLevel.add(cur.val);
        if(isHeadPoll){
          if(cur.left != null){
            dq.addLast(cur.left);
          }
          if(cur.right != null){
            dq.addLast(cur.right);
          }
        } else{
          if(cur.left != null){
            dq.addFirst(cur.left);
          }
          if(cur.right != null){
            dq.addFirst(cur.right);//技巧:这一层要把先进来的往后顶，所以是从头部进往队列的后推
          }

        }
      }
      ans.add(curLevel);
      isHeadPoll = !isHeadPoll;
    }
    return ans;
  }
}
