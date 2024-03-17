package com.book.zuoshen.InterviewGuide.chpt3.dpintree;


/**
 * @Author: zhaomengyi
 * @Date: 2024/1/29 12:44
 * @Description:判断是否是平衡二叉树，两侧树高度不能大于1
 */
public class IsBallanceTree {
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

    public class ReturnType{
        public boolean isBalanced;
        public int height;

        public ReturnType(boolean isBalanced, int height){
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    public ReturnType process(Node head){
        if(head == null){
            return new ReturnType(true, 0);
        }
        ReturnType leftData = process(head.left);
        ReturnType rightData = process(head.right);
        int height = Math.max(leftData.height, rightData.height) + 1;
        //左子树平衡，右子树平衡，左右子树高度差小于2
        boolean isBalanced = leftData.isBalanced && rightData.isBalanced
                && Math.abs(leftData.height - rightData.height) < 2;
        return new ReturnType(isBalanced, height);
    }

    public boolean isBallance(Node head){
        return process(head).isBalanced;
    }


}
