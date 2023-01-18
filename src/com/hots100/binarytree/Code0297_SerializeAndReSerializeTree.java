package com.hots100.binarytree;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * @Author: zhaomengyi
 * @Date: 2022-12-05 4:10 下午
 * @Description:
 */
public class Code0297_SerializeAndReSerializeTree {

  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
  }

  //技巧：序列化和反序列化都使用queue作为辅助，序列化就是一个BFS的遍历，使用queue辅助。反序列化注意顺序即可，比较好理解。
  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
    ArrayList<String> ans = new ArrayList<String>();
    if(root == null){
      ans.add("null");
    } else {
      ans.add(String.valueOf(root.val));
      //技巧:因为使用level遍历，就是一个BFS所以借助一个Queue队列
      // 按照层级遍历就是一个BFS
      Queue<TreeNode> queue = new LinkedList<>();
      queue.add(root);
      while(!queue.isEmpty()){
        root = queue.poll();
        //左子树
        if(root.left != null){
          ans.add(String.valueOf(root.left.val));
          queue.add(root.left);
        } else {
          ans.add(null);
        }
        //右子树
        if (root.right != null){
          ans.add(String.valueOf(root.right.val));
          queue.add(root.right);
        } else{
          ans.add(null);
        }
      }
    }
    return "[" + ans.stream().collect(Collectors.joining(",")) + "]";
  }



  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
    if(data == null) return null;
    String raw = data.substring(1,data.length()-1);
    String[] arr = raw.split(",");
    TreeNode head = generateNode(arr[0]);
    Queue<TreeNode> q = new LinkedList<>();
    q.add(head);
    TreeNode node = null;
    int i = 1;
    while(!q.isEmpty()){
      node = q.poll();
      if(i<arr.length) {
        node.left = generateNode(arr[i++]);
        node.right = generateNode(arr[i++]);
        if (node.left != null) {
          q.add(node.left);
        }
        if (node.right != null) {
          q.add(node.right);
        }
      }
    }
    return head;
  }

  public TreeNode generateNode(String data){
    if(data.equals("null")) return null;
    return new TreeNode(Integer.parseInt(data.trim()));
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    TreeNode root3 = new TreeNode(3);
    root.right = root3;
    root3.left = new TreeNode(4);
    root3.right = new TreeNode(5);
    System.out.println(new Code0297_SerializeAndReSerializeTree().serialize(root));
    System.out.println("[1]".substring(1,"[1]".length()-1));
    String tmp = new Code0297_SerializeAndReSerializeTree().serialize(root);
    new Code0297_SerializeAndReSerializeTree().deserialize(tmp);
  }
}
