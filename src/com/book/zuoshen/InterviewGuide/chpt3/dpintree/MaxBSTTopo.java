package com.book.zuoshen.InterviewGuide.chpt3.dpintree;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/29 09:31
 * @Description:
 */
public class MaxBSTTopo {
    public class Node{
        private int value;
        private Node left;
        private Node right;

        public Node(int data){
            this.value = data;
        }
    }

    /**
     * 方法1：使用递归方法
     * @param head
     * @return
     */
    public int bstTopoSize1(Node head){
        if(head == null){
            return 0;
        }
        int max = maxTopo(head, head);
        max = Math.max(bstTopoSize1(head.left), max);
        max = Math.max(bstTopoSize1(head.right), max);
        return max;
    }

    //都要和h比较
    public int maxTopo(Node h, Node n){
        if(h != null && n != null && isBSTNode(h, n, n.value)){
            return maxTopo(h, n.left) + maxTopo(h, n.right) +1;
        }
        return 0;
    }

    /**
     *
     * @param h
     * @param n
     * @param value,n的值
     * @return
     */
    public boolean isBSTNode(Node h, Node n, int value){
        if(h == null){
            return false;
        }
        if(h == n){
            return true;
        }
        //往下走
        return isBSTNode(h.value > value ? h.left : h.right, n, value);
    }

    public class Record{
        public int l;
        public int r;

        public Record(int left, int right){
            this.l = left;
            this.r = right;
        }
    }

    /**
     * 使用后序遍历生成每个节点的贡献值，通过贡献值得到结果，DPTree的方法
     * @param head
     * @return
     */
    public int bstTopSize2(Node head){
        Map<Node, Record> map = new HashMap<Node, Record>();
        return posOrder(head, map);
    }

    public int posOrder(Node h, Map<Node, Record> map){
        if(h == null){
            return 0;
        }
        int ls = posOrder(h.left, map);
        int rs = posOrder(h.right, map);
        modifyMap(h.left, h.value, map, true);
        modifyMap(h.right, h.value, map, false);
        Record lr = map.get(h.left);
        Record rr = map.get(h.right);
        int lbst = lr == null ? 0 : lr.l + lr.r + 1;
        int rbst = rr == null ? 0 : rr.l + rr.r + 1;
        map.put(h, new Record(lbst, rbst));
        return Math.max(lbst + rbst + 1, Math.max(ls, rs));
    }

    public int modifyMap(Node n, int v, Map<Node, Record> m, boolean s){
        if(n == null || (!m.containsKey(n))){
            return 0;
        }
        Record r = m.get(n);
        if((s && n.value > v) || ((!s) && n.value < v)){
            m.remove(n);
            return r.l + r.r + 1;
        } else {
            int minus = modifyMap(s ? n.right : n.left, v, m, s);
            if(s){
                r.r = r.r - minus;
            } else {
                r.l = r.l - minus;
            }
            m.put(n, r);
            return minus;
        }
    }
}
