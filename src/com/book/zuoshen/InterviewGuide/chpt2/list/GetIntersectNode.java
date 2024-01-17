package com.book.zuoshen.InterviewGuide.chpt2.list;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/16 16:40
 * @Description:找两个链表的公共部分的题是一道综合题，可以将问题拆分为几个问题：
 * （1）如何判断一个链表是否有环，如果有，则返回第一个进入环的节点，没有返回null；
 * （2）如何判断两个无环链表是否相交，相交则返回第一个相交节点，没有返回null；
 * （3）如何判断两个有环链表是否相交，相交则返回第一个相交节点，没有返回null；
 * （4）如果一个链表有环，一个链表无环，它们是不能相交的，直接返回null【因为两个链表相交必有环】
 */
public class GetIntersectNode {
    public class Node{
        private int value;
        private Node next;
        public Node(int value){
            this.value = value;
        }
    }

    //获得环形链表的入环节点
    public Node getLoopNode(Node head){
        if(head == null || head.next == null || head.next.next == null){
            return null;
        }
        Node n1 = head.next;
        Node n2 = head.next.next;
        while(n1 != n2){
            if(n2.next == null || n2.next.next == null){
                return null;
            }
            n2 = n2.next.next;
            n1 = n1.next;
        }
        n2 = head; //n2再次从头走
        while(n1 != n2){
            n2 = n2.next;
        }
        return n1; //环里的第一个节点
    }

    //如果两个链表相交，那么它们的结尾一定是相同节点
    public Node noLoop(Node head1, Node head2){
        if(head1 == null || head2 == null){
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;
        while(cur1.next != null){
            n++;
            cur1 = cur1.next;
        }
        while(cur2.next != null){
            n--;
            cur2 = cur2.next;
        }
        if(cur1 != cur2){
            return null;
        }
        cur1 = n > 0 ? head1 : head2;//找出长的
        cur2 = cur1 == head1 ? head2 : head1;
        n = Math.abs(n);
        while(n != 0){
            n--;
            cur1 = cur1.next;
        }
        while(cur1 != cur2){
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    //两个链都有环，如果1转了一圈每遇上2说明，这俩没有相交；如果1在环中转半截遇上了2，说明在环内；
    // 如果每进环遇上，说明和两个单链表的情况一致
    public Node bothLoop(Node head1 , Node loop1, Node head2, Node loop2){
        Node cur1 = null;
        Node cur2 = null;
        //
        if(loop1 == loop2){
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while(cur1 != loop1){
                n++;
                cur1 = cur1.next;
            }
            while(cur2 != loop2){
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while(n != 0){
                n--;
                cur1 = cur1.next;
            }
            while(cur1 != cur2){
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            //在环里
            cur1 = loop1.next;
            while(cur1 != loop1){
                if(cur1 == loop2){
                    return loop1;
                }
                cur1 = cur1.next;
            }
            return null;//没有出现环
        }
    }

    public Node getIntersectNode(Node head1, Node head2){
        if(head1 == null || head2 == null){
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        if(loop1 == null && loop2 == null){
            return noLoop(head1, head2);
        }
        if(loop1 != null && loop2 != null){
            return bothLoop(head1, loop1, head2, loop2);
        }
        return null;
    }

}
