package com.book.zuoshen.InterviewGuide.chpt3.MorrisRecur;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/22 09:34
 * @Description:通过树最小深度引出morris遍历
 */
public class MorrisMinHeight {
    public class Node{
        private int value;
        private Node left;
        private Node right;

        public Node(int data){
            this.value = data;
        }
    }
    public static int minDepth1(Node head){
        if(head == null){
            return 0;
        }
        return process(head, 1);
    }
    //当前节点的节点是cur，cur所在的深度是level
    public static int process(Node cur, int level){
        if(cur.left == null && cur.right == null){
            return level;
        }
        int ans = Integer.MAX_VALUE;
        //如果左子树存在，返回cur做书上的最矮叶子节点高度
        if(cur.left != null) {
            ans = Math.min(process(cur.left, level + 1), ans);
        }
        if(cur.right != null){
            ans = Math.min(process(cur.right, level + 1), ans);
        }
        return ans;
    }

    /**
     * 使用morris遍历来求解这道题面临的两个问题（1）如何通过已知pre的层级level来得到当前cur的层级level （2）如何处理叶子节点；出现这两个问题的
     * 根本原因是因为morris遍历会改变cur左子树的mostRight节点的right指针的指向。这两个问题的处理办法都是在第二次重复遍历的时候进行处理。
     * 1、第一个问题如何通过当前计算出的pre level来确定cur的level。直接给结论：（1）如果pre节点是x已知其层级为7，当前节点是y，y=x.left,y左子树的最右节点不是x，
     * 那么y的level=8；（2）如果pre节点是x已知层级为7，当前节点是y，y=x.right,y左子树的最右节点不是x，那么y的level=8；
     * （3）y在之前已经计算过一次层级，并且在第二次访问的时候是通过已知的mostRight节点x到达的y，x的层级已知为7，那么y的层级需要重新计算7-左边界节点数量，重新
     *  调整得到y节点的层级。
     * 2、第二个如何确定叶子节点：在morris序中两次到达某点时将恢复到该节点的right位置置空时看看被置空right指针的节点是否为叶子节点。
     * 3、在遍历整体树后，整棵树的最右节点要单独拿出来判断一些是否为叶子节点.
     */
    public int morrisMinDepth(Node head){
        if(head == null){
            return 0;
        }
        Node cur = head;
        Node mostRight = null;
        int curLevel = 0;//即使从头开始也要从head开始算，所以就是0
        int minHeight = Integer.MAX_VALUE;
        while(cur != null){
            mostRight = cur.left;
            if(mostRight != null){//当前的cur节点有左子树，那么cur肯定！肯定！肯定！要被遍历两次，cur需要level在第二次访问时候进行层级调整
                //cur左子树上，右边界的节点个数，这里为了方便二次调整时候层级计算
                int leftTreeRightSize = 1;
                //找到cur左子树上的最右节点
                while(mostRight.right != null && mostRight.right != cur){
                    leftTreeRightSize++;//统计到达mostRight右侧节点个数
                    mostRight = mostRight.right;
                }
                if(mostRight.right == null){
                    //第一次叨叨cur，那么下一个节点的levle必然+1；
                    curLevel++;
                    mostRight.right = cur;//mostRight节点指针调整；
                    cur = cur.left; //移动cur指针
                    continue;
                } else {
                    //第二次到达cur，（1）调整cur的高度那么下一个节点的level = curLevel - leftTreeRightSize,
                    // （2）此时检查mostRight是不是叶子节点，记录答案
                    if(mostRight.left == null){
                        minHeight = Math.min(minHeight, curLevel);
                    }
                    curLevel = curLevel - leftTreeRightSize;
                    mostRight.right = null; //mostRight的right置空
                }
            } else {
                //没有左子树,只能到达一次cur节点，所以level+1
                curLevel++;
            }
            cur = cur.right;
        }
        //整棵树的最右节点
        int finalRight = 1;
        cur = head;
        while(cur.right != null){
            finalRight++;
            cur = cur.right;
        }
        //最后不要忘了单独看整棵树的最右节点是不是叶子节点
        if(cur.left == null && cur.right == null){
            minHeight = Math.min(minHeight, finalRight);
        }
        return minHeight;

    }
}
