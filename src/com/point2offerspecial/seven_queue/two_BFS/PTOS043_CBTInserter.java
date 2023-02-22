package com.point2offerspecial.seven_queue.two_BFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-07 18:18
 * @Description:
 */
public class PTOS043_CBTInserter {
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
  private Queue<TreeNode> queue;
  private TreeNode root;
  public PTOS043_CBTInserter(TreeNode root) {
    this.root = root;
    this.queue = new LinkedList<>();
    //技巧：【这个倒是挺恶心的，先把满的都塞进去】构建的时候把有左右结点的结点都放进去
    queue.offer(root);
    //技巧：原来[BFS遍历while(!queue.isEmpty())的变种]，不全满的不加到队列
    while(queue.peek().left != null && queue.peek().right != null){ //太难了~直接记住例子吧
      TreeNode node = queue.poll();//如果满了给他踢走
      queue.offer(node.left);
      queue.offer(node.right);
    }
  }

  public int insert(int v) {
    //要返回父节点
    TreeNode parent = queue.peek();//找到最前面的元素
    TreeNode node = new TreeNode(v);
    if(parent.left == null){
      parent.left = node;
    } else { //满了就要操作一下，把parent清除掉，放入left 和 right
      parent.right = node;

      queue.poll();
      queue.offer(parent.left);
      queue.offer(parent.right);
    }
    return parent.val;
  }

  public TreeNode get_root() {
    return this.root;
  }
}
