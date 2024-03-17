package com.book.zuoshen.InterviewGuide.chpt2.list;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/15 10:01
 * @Description:这个方法使用一个变量进行记录，如果这条记录大于0说明不存在删除的点，如果等于0说明是删除队头，
 * 如果是小于0说明在队中，重新遍历一次n-k就是待删除节点的前一个节点
 */
public class DelLastKthOneWayList {
    public class Node{
        private int value;
        private Node next;

        public Node(int data){
            this.value = data;
        }
    }

    public Node removeLastKthNode(Node head, int lastK){
        if(head == null || lastK < 1){
            return null;
        }
        Node cur = head;
        while(cur != null){
            lastK--;//只要是节点就减少lastK的值
            cur = cur.next;
        }
        if(lastK == 0){
            head = head.next;
        }
        if(lastK < 0){
            cur = head;
            //技巧：先++再动节点，就是看当前节点的下一个位置的值,这个一旦是0，说明就是那个待删除的节点了
            while(++lastK != 0) {
                cur = cur.next;
            }
            cur.next = cur.next.next;
        }
        return head;
    }
}
