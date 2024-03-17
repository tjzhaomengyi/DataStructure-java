package com.book.zuoshen.InterviewGuide.chpt3.findnode;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/29 17:39
 * @Description:在一个带有parent指针的二叉树中，以中序遍历为标准找到一个节点的后继节点
 */
public class GetNextNode {
    //看当前节点是否有right子树
    public class Node {
        int val;
        Node left;
        Node right;
        Node parent;

        Node(int val) {
            this.val = val;
        }

        Node(int val, Node left, Node right, Node parent) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    public Node getNextNode(Node node){
        if(node == null){
            return node;
        }
        //右侧子树不为空，返回最左侧节点
        if(node.right != null){
            return getLeftMost(node.right);
        } else {
            //右侧子树为空，找到上级节点
            Node parent = node.parent;
            //该节点可以是上级节点的左右孩子两种情况
            while(parent != null && parent.left != node){
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    public Node getLeftMost(Node node){
        if(node == null){
            return null;
        }
        while(node.left != null){
            node = node.left;
        }
        return node;
    }
}
