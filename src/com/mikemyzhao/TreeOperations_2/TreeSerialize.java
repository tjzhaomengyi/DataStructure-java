package com.mikemyzhao.TreeOperations_2;

import com.mikemyzhao.TreeOperations_2.trees.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-10 23:01
 * @Description:
 */
public class TreeSerialize {
  String SEP = ",";
  String NULL = "#";
  //用于结果字符串拼接
  StringBuilder sb = new StringBuilder();
  //把一棵二叉树序列化成字符串,序列化就是把树打平，二叉树的几种遍历方式
  public String serialize(TreeNode root) {
    //前序遍历
    StringBuilder sb = new StringBuilder();
    traverseFront(root,sb);
    return sb.toString();
  }
  //前序遍历
  void traverseFront(TreeNode root,StringBuilder sb){
    if(root == null){
      sb.append(NULL).append(SEP);
    }
    //前序遍历位置
    sb.append(root.val).append(SEP);
    traverseFront(root.left,sb);
    traverseFront(root.right,sb);
  }

  //后续遍历序列化
  void traverseBack(TreeNode root,StringBuilder sb){
    if(root==null){
      sb.append(NULL).append(SEP);
    }
    traverseBack(root.left,sb);
    traverseBack(root.right,sb);
    sb.append(root.val).append(SEP);
  }

  //中序遍历
  void traverseMid(TreeNode root,StringBuilder sb){
    if(root==null){
      sb.append(NULL).append(SEP);
      return;
    }
    traverseMid(root.left,sb);
    sb.append(root.val).append(SEP);
    traverseMid(root.right,sb);
  }


  //层级遍历法
  String travseLevel(TreeNode root){
    if(root==null) return "";
    //初始化队列将root加入
    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);
    while(!q.isEmpty()){
      TreeNode cur = q.poll();
      //层级遍历代码位置
      if(cur == null) {
        sb.append(NULL).append(SEP);
        continue;
      }
      sb.append(cur.val).append(SEP);
      System.out.println(cur.val);

      q.offer(cur.left);
      q.offer(cur.right);
    }
    return sb.toString();
  }

  //前序遍历反序列化把字符串反序列化成二叉树
  public TreeNode deserializeFront(String data){
    LinkedList<String> nodes = new LinkedList<>();
    for(String s:data.split(SEP)){
      nodes.addLast(s);
    }
    return deserializeFront(nodes);
  }

  //前序反序列化：参数为链表的反序列化辅助函数
  public TreeNode deserializeFront(LinkedList<String> nodes){
    if(nodes.isEmpty()) return null;
    /**前序遍历位置**/
    //列表左侧是根节点
    String first = nodes.removeFirst();
    if(first.equals(NULL)) return null;
    TreeNode root = new TreeNode(Integer.parseInt(first));
    root.left = deserializeFront(nodes);
    root.right = deserializeFront(nodes);
    return root;
  }

  //后续遍历反序列化，因为根节点在最后面，所以要从后向前重新生成树
  TreeNode deserializeBack(String data){
    LinkedList<String> nodes = new LinkedList<>();
    for(String s:data.split(SEP)){
      nodes.addLast(s);
    }
    return deserializeBack(nodes);
  }

  //后续遍历反序列化辅助函数
  TreeNode deserializeBack(LinkedList<String> nodes){
    if(nodes.isEmpty()) return null;
    //从后往前取元素
    String last = nodes.removeLast();
    if(last.equals(NULL))return null;
    TreeNode root = new TreeNode(Integer.parseInt(last));
    //先构造右子树再构造左子树
    root.right = deserializeBack(nodes);
    root.left = deserializeBack(nodes);
    return root;
  }

  //层级遍历反序列化，这个不对！
  TreeNode deserializeLevel(String data){
    if(data.isEmpty()) return null;
    String[] nodes = data.split(SEP);
    //第一个元素就是root
    TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));

    //队列q记录父节点，将root加入队列
    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);

    int i=1;
    while(!q.isEmpty()){
      //队列中存在的都是父节点
      TreeNode parent =  q.poll();
      //父节点对应的左侧节点
      String left = nodes[i];//这就有问题
      if(!left.equals(NULL)) {
        parent.left = new TreeNode(Integer.parseInt(left));
        q.offer(parent.left);
      }
      i++;
      //父节点对应的右侧子节点的值
      String right = nodes[i];
      if(!right.equals(NULL)){
        parent.right = new TreeNode(Integer.parseInt(right));
        q.offer(parent.right);
      }
      i++;
    }
    return root;
  }

  //标准层级模板代码
  /**
   * while(!q.isEmpty()){
   *    TreeNode cur = q.pool;
   *    if(cur.left!=null){
   *      q.offer(cur.left)
   *    }
   *    if(cur.right!=null){
   *      q.offer(cur.right)
   *    }
   *
   * }
   *
   *
   *
   * ***/

}
