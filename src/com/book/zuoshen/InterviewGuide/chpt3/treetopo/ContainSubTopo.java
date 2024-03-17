package com.book.zuoshen.InterviewGuide.chpt3.treetopo;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/29 11:29
 * @Description:Tree1包含Tree2子拓扑
 */
public class ContainSubTopo {
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

    public boolean contains(Node t1, Node t2) {
        if (t2 == null) {
            return true;
        }
        if (t1 == null) {
            return false;
        }
        return check(t1, t2) || contains(t1.left, t2) || contains(t1.right, t2);
    }

    public boolean check(Node h, Node t2){
        if(t2 == null){
            return true;
        }
        if(h == null || h.val != t2.val){
            return false;
        }
        return check(h.left, t2.left) && check(h.right, t2.right);
    }
}
