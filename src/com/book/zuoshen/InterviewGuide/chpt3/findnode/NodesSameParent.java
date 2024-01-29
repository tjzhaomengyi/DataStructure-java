package com.book.zuoshen.InterviewGuide.chpt3.findnode;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/29 18:56
 * @Description:
 */
public class NodesSameParent {
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
    public Node lowestParent(Node head, Node o1, Node o2){
        //如果满足条件，把结果往上返回即可
        if(head == null || head == o1 || head == o2){
            return head;
        }
        Node left = lowestParent(head.left, o1, o2);
        Node right = lowestParent(head.right, o1, o2);
        if(left != null && right != null){
            return head;
        }
        return left != null ? left : right;
    }

    //使用node节点的map记录最近的parent节点
    public class Record1 {
        private HashMap<Node, Node> map;

        public Record1(Node head) {
            map = new HashMap<Node, Node>();
            if(head != null){
                map.put(head, null);
            }
            setMap(head);
        }

        private void setMap(Node head){
            if(head == null){
                return;
            }
            if(head.left != null){
                map.put(head.left, head);
            }
            if(head.right != null){
                map.put(head.right, head);
            }
            setMap(head.left);
            setMap(head.right);
        }

        public Node query(Node o1, Node o2){
            HashSet<Node> path = new HashSet<Node>();
            while(map.containsKey(o1)){
                path.add(o1);
                o1 = map.get(o1);//找o1的父节点
            }
            while(!path.contains(o2)){
                o2 = map.get(o2);
            }
            return o2;
        }
    }
}
