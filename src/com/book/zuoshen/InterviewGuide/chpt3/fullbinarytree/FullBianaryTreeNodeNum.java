package com.book.zuoshen.InterviewGuide.chpt3.fullbinarytree;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/31 15:44
 * @Description:
 */
public class FullBianaryTreeNodeNum {
    public static class Node{
        private int value;
        private Node left;
        private Node right;

        public Node(int data){
            this.value = data;
        }
    }

    public int mostLeftLevel(Node node, int level){
        while(node != null){
            level++;
            node = node.left;
        }
        return level - 1;
    }

    public int nodeNum(Node head){
        if(head == null){
            return 0;
        }
        return bs(head, 1, mostLeftLevel(head, 1));
    }

    public int bs(Node node, int l, int h){
        if(l == h){
            return 1;
        }
        if(mostLeftLevel(node.right, l + 1) == h){
            return (1 << (h - l)) + bs(node.right, l + 1, h);
        } else {
            return (1 << (h - l - 1)) + bs(node.left, l + 1, h);
        }
    }
}
