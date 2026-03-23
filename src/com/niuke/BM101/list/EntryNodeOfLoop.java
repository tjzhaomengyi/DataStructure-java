package com.niuke.BM101.list;
import com.mikemyzhao.list_1.ListNode;

public class EntryNodeOfLoop {
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if(pHead == null) return pHead;
        ListNode fast = pHead.next;
        ListNode slow = pHead;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow){ //这里只是两个指针相遇了，快的比慢的多走了一圈，但是不保证是开始节点
                //一个指针从头开始走，另一个指针从当前节点往后走就能找到环
                // 从开头到环口位置的距离=a
                // 从环口到相遇位置的距离=b
                // 从相遇位置再到环口的距离=c
                // 相遇时候 slow走了 a + b ，Fast对应走了 2*(a+b)，并且fast比slow多走了一圈
                // 2a + 2b = a + b + c + b
                // ∴ a = c
                fast = pHead;
                while(fast != slow){
                    fast = fast.next;
                    slow = slow.next;
                }
                return fast;
            }
        }
        return null;
    }
}
