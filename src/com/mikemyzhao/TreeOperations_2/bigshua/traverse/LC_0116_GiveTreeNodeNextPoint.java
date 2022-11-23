package com.mikemyzhao.TreeOperations_2.bigshua.traverse;

import com.MCAAlgorithm.bigshua.class30.Problem_0116_PopulatingNextRightPointersInEachNode;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-17 11:31
 * @Description:
 * 技巧:使用O(1)的空间复杂度在原有的树上根据next节点，进行树的操作
 */
public class LC_0116_GiveTreeNodeNextPoint {
  public static class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;
  }
  //技巧:这题要求空间复杂度是O(1),不使用系统数据结构，自己在给定的树上通过next节点构造一个队列
  public static class MyQueue{
    public Node head;
    public Node tail;
    public int size;

    public MyQueue(){
      head = null;
      tail = null;
      size = 0;
    }

    public boolean isEmpty(){
      return size == 0;
    }
    public void offer(Node cur){
      size++;
      if(head == null) {
        head = cur;
        tail = cur;
      } else {
        tail.next = cur;
        tail = cur;
      }
    }

    public Node poll(){
      size--;
      Node ans = head;
      head = head.next;
      ans.next = null;//断连
      return ans;
    }
  }

  public static Node connect(Node root){
    if(root == null){
      return root;
    }
    MyQueue q = new MyQueue();
    q.offer(root);
    while(!q.isEmpty()){
      //第一个弹出节点
      Node pre = null;//技巧:BFS一般有记录前一个节点的时候就不需要set了！！！！！！记录前一个节点
      int size = q.size;
      for(int i = 0; i < size; i++){//BFS连接即可
        Node cur = q.poll();
        if(cur.left != null){
          q.offer(cur.left);
        }
        if(cur.right != null){
          q.offer(cur.right);
        }
        if(pre != null){
          pre.next = cur;//开始连！
        }
        pre = cur;
      }
    }
    return root;
  }
}
