package com.weeklyproblems.week2021_11_4;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2024/3/6 12:30
 * @Description:保留黑色点组成的树【树的套路遍历】
 */
public class RetainTree {
    public static class Node{
        public int value;
        public boolean retain;
        public List<Node> nexts;
        public Node(int v, boolean r){
            value = v;
            retain = r;
            nexts = new ArrayList<>();
        }
    }

    //给定一棵树的头结点，按照描述保留节点，没有保留的节点删除,调整完成后返回头结点
    public static Node retain(Node x){
        if (x.nexts.isEmpty()){
            return x.retain ? x : null;
        }
        List<Node> newNexts = new ArrayList<>();
        for(Node next : x.nexts){
            Node newNext = retain(next);
            if(newNext != null){
                newNexts.add(newNext);
            }
        }
        //x.nexts 老的链表，下级节点；newNexts新的链表，值保留的在里面
        if(!newNexts.isEmpty() || x.retain){
            x.nexts = newNexts;
            return x;
        }
        return null;
    }

    //先序打印
    public static void preOrderPrint(Node head){
        System.out.println(head.value);
        for(Node next : head.nexts){
            preOrderPrint(next);
        }
    }

}
