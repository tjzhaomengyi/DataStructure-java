package com.point2offer;

import com.mikemyzhao.TreeOperations_2.trees.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-08 11:12
 * @Description:
 */
public class TreeCodec_37 {
  // Encodes a tree to a single string.
  String NULL = "null";
  String SEP = ",";
  //层级遍历BFS
  public String serialize(TreeNode root) {
    if(root==null) return "[]";
    StringBuilder tmp = new StringBuilder("[");
    Queue<TreeNode> q = new LinkedList();
    q.offer(root);

    while(!q.isEmpty()){
      TreeNode cur = q.poll();
      if(cur!=null) {
        tmp.append(cur.val).append(SEP);
        q.offer(cur.left);
        q.offer(cur.right);
      }else if(cur==null){
        tmp.append(NULL).append(SEP);
      }
    }
    tmp.deleteCharAt(tmp.length()-1);
    tmp.append("]");
    return tmp.toString();
  }

  // Decodes your encoded data to tree.

  public TreeNode deserialize(String data) {
    if(data.equals("[]")) return null;
    String[] nodes = data.substring(1,data.length()-1).split(SEP);
    TreeNode root = new TreeNode(Integer.valueOf(nodes[0]));

    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);
    int i=1;
    while(!q.isEmpty()){
      TreeNode parent = q.poll();
      if(!nodes[i].equals(NULL)){
        parent.left = new TreeNode(Integer.parseInt(nodes[i]));
        q.offer(parent.left);
      }
      i++;
      if(!nodes[i].equals(NULL)){
        parent.right = new TreeNode(Integer.parseInt(nodes[i]));
        q.offer(parent.right);
      }
      i++;
    }
  return root;
  }
}
