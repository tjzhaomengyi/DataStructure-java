package com.book.zuoshen.InterviewGuide.chpt3.MorrisRecur;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/29 14:28
 * @Description:利用morris遍历看是否是
 */
public class MorrisIsBST {
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

    public boolean isBST(Node head){
        if(head == null){
            return true;
        }
        boolean ans = true;
        Node pre = null;
        Node cur1 = head;
        Node cur2 = null;
        while(cur1 != null){
            cur2 = cur1.left;
            if(cur2 != null){
                //morris遍历找左子树的最右节点
                while(cur2.right != null && cur2.right != cur1){
                    cur2 = cur2.right;
                }
                if(cur2.right == null){
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                } else {
                    cur2.right = null;
                }
            }
            if(pre != null && pre.val > cur1.val){
                ans = false;
            }
            pre = cur1;
            cur1 = cur1.right;
        }
        return ans;
    }
}
