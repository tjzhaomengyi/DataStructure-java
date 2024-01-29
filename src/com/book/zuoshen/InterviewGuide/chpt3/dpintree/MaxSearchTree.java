package com.book.zuoshen.InterviewGuide.chpt3.dpintree;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/25 17:22
 * @Description:二叉树中的最大搜索二叉子树
 */
public class MaxSearchTree {
    public static class Node{
        private int value;
        private Node left;
        private Node right;

        public Node(int data){
            this.value = data;
        }
    }

    public class ReturnType {
        public Node maxBSTHead;
        public int maxBSTSize;
        public int min;
        public int max;

        public ReturnType(Node maxBSTHead, int maxBSTSize, int min, int max){
            this.maxBSTHead = maxBSTHead;
            this.maxBSTSize = maxBSTSize;
            this.min = min;
            this.max = max;
        }
    }

    public ReturnType process(Node x){
        //base case:如果子树是空树，最小值为系统值最大，最大值为系统最小
        if(x == null){
            return new ReturnType(null, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }
        //默认直接得到左子树全部信息
        ReturnType lData = process(x.left);
        ReturnType rData = process(x.right);
        //整合信息：同时以x为头结点的子树做同样的要求，也需要返回如ReturnType描述的全部信息
        //以x为头结点的子树的最小值是左树最小、右树最小及x的值三者最小的。前两种情况左树大于右树，或者右树大于左树
        int min = Math.min(x.value, Math.min(lData.min, rData.min));
        int max = Math.max(x.value, Math.max(lData.max, rData.max));
        //x的子树的最大搜索二叉树大小
        int maxBSTSize = Math.max(lData.maxBSTSize, rData.maxBSTSize);
        Node maxBSTHead = lData.maxBSTSize >= rData.maxBSTSize ? lData.maxBSTHead : rData.maxBSTHead;
        //利用搜集的信息判断是否有第三种可能,左右深度一样
        if(lData.maxBSTHead == x.left && rData.maxBSTHead == x.right && x.value > lData.max && x.value < rData.min){
            maxBSTSize = lData.maxBSTSize + rData.maxBSTSize + 1;
            maxBSTHead = x;
        }
        return new ReturnType(maxBSTHead, maxBSTSize, min, max);
    }

    public Node getMaxBST(Node head){
        return process(head).maxBSTHead;
    }

}
