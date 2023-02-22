package com.point2offerspecial.eight_tree.one_dfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-10 10:10
 * @Description:参考代码：Code02_SerializeAndReconstructTree
 */
public class PTOS048_CodecTree {
  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
  //序列化和反序列化一棵树可以使用前、中、后序三种遍历方式进行序列化和反序列化
  public String serialize(TreeNode root) {
    if(root == null){
      return "#";
    }
    String left = serialize(root.left);
    String right = serialize(root.right);
    return String.valueOf(root.val) + "," +
        left + "," +
        right;
  }

  public TreeNode deserialize(String data) {
    String[] splits = data.split(",");
    Queue<String> q = new LinkedList<>(); //这里建议弄一个Queue队列，如果用数组和下标反序列化的话需要对递增下标进行特殊处理
    //参考《剑指offer专项练习解释》：用数组int[] i = new int{0}；来代替i++的处理，因为在递归中i++无法进行正常递增操作
    for(String s : splits){
      q.offer(s);
    }
    return rebuildDFS(q);
  }

  public TreeNode rebuildDFS(Queue<String> q){
    String str = q.poll();
    if(str.equals("#")){
      return null;
    }
    TreeNode node = new TreeNode(Integer.parseInt(str));
    node.left = rebuildDFS(q);
    node.right = rebuildDFS(q);
    return node;
  }
}
