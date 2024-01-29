package com.book.zuoshen.InterviewGuide.chpt3.fullbinarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/29 14:45
 * @Description:
 */
public class IsFullBinaryTree {
    public class Node {
        int val;
        Node left;
        Node right;


        Node(int val) {
            this.val = val;
        }

        Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //使用层级遍历判断是否为完全二叉树
    public boolean isCBT(Node head){
        if(head == null){
            return true;
        }
        Queue<Node> queue = new LinkedList<>();
        boolean leaf = false;
        Node l = null;
        Node r = null;
        queue.offer(head);
        while(!queue.isEmpty()){
            head = queue.poll();
            l = head.left;
            r = head.right;
            //是否为左右子节点不全的节点：（1）如果l为空，r不为空，false；如果上一个已经是叶子几点，但是当前节点还有左右子节点，那么也不行
            if((leaf && (l != null || r != null)) || (l == null && r!= null)){
                return false;
            }
            if (l != null){
                queue.offer(l);
            }
            //如果当前节点有右侧节点
            if(r != null) {
                queue.offer(r);
            }
            if(l == null || r == null){
                leaf = true;
            }
        }
        return true;
    }
}
