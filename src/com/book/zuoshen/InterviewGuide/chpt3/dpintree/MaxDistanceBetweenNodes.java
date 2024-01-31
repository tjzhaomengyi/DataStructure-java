package com.book.zuoshen.InterviewGuide.chpt3.dpintree;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/30 14:26
 * @Description:
 */
public class MaxDistanceBetweenNodes {
    public static class Node{
        private int value;
        private Node left;
        private Node right;

        public Node(int data){
            this.value = data;
        }
    }

    //使用DPTree方法处理
    // (1)以x为头结点的子树，最大距离可能是左子树上的最大距离
    // (2)以x为头结点的子树，最大距离可能是右子树上的最大距离
    // (3)以x为头结点的子树，最大距离可能是从x的左子树离x最远的节点，先走到x，然后再走到x的右子树离x最远的节点。也就是左子树的高度 + 右子树的高度 + 1
    public static class ReturnType{
        public int maxDistance;
        public int height;
        public ReturnType(int maxDistance, int height){
            this.maxDistance = maxDistance;
            this.height = height;
        }
    }

    public ReturnType process(Node head){
        if(head == null){
            return null;
        }
        ReturnType leftData = process(head.left);
        ReturnType rightData = process(head.right);
        int height = Math.max(leftData.height, rightData.height) + 1;
        int maxDistance = Math.max(leftData.height + rightData.height + 1,
                Math.max(leftData.maxDistance, rightData.maxDistance));
        return new ReturnType(maxDistance, height);
    }

    public int getMaxDistance(Node head){
        return process(head).maxDistance;
    }
}
