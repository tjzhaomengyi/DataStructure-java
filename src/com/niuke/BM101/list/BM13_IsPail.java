package com.niuke.BM101.list;

public class BM13_IsPail {
    public class ListNode {
        int val;
        ListNode next = null;
        public ListNode(int val) {
            this.val = val;
        }
    }

    public boolean isPail(ListNode head){
        if(head == null || head.next == null) return true;
        ListNode slow = head;
        ListNode fast = head;
        while(fast!= null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        //上面已经把slow推到了反转
        ListNode pre = null;
        while(slow != null){
            ListNode next = slow.next;
            slow.next = pre;
            pre = slow;
            slow = next;
        }

        // 比较的时候如果是奇数 ，slow卡在3上，后面反转正好和前面比较，整体链表不需要断开。
        // 1 2 3 2 1
        // 1 2 3
        ListNode p1 = head;//p1还是整个链表
        ListNode p2 = pre;
        while(p2 != null){
            if(p1.val != p2.val) return false;
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;

    }
}
