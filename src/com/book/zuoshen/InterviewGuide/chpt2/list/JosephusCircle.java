package com.book.zuoshen.InterviewGuide.chpt2.list;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/15 22:13
 * @Description:
 */
public class JosephusCircle {
    //约瑟夫换问题：在环中的人报数，报数到m的人，从环中删除，直到最后环里剩下一个人
    public class Node{
        public int value;
        public Node next;

        public Node(int data){
            this.value = data;
        }
    }

    public Node josephusKill(Node head, int m){
        if(head == null || head.next == head || m < 1){
            return head;
        }
        Node last = head;
        while(last.next != head){
            last = last.next;//找到head的前序节点
        }
        int count = 0;
        while(head != last){
            if(++count == m){
                last.next = head.next;
                count = 0;
            } else {
                last = last.next;
            }
            head= last.next;
        }
        return head;
    }

    //数学结论解法：约瑟夫环公式1：在一个环中，节点编号=(报数-1) % i + 1，i是环的大小。
    // 约瑟夫环公式2： 在删除掉一个节点S后，老编号=（新编号+s-1）%i+1，s是被删除掉节点的编号，推出s=(m-1)%i+1,m表示喊到的数就退出的那个数
    // 约瑟夫环公式3：老编号=（新编号+（m-1）%i） +1，其中可以假设m-1=k*i+b(k≥0)，把m-1认为是k个i再加上一个余数b。那么
    // （新编号+(m-1)%i)%i = （新编号+b）%i+1，因为b=m-1-k*i=m-1，（新编号+m-1）%i+1=(新编号+b)%i+1.老编号的公式可以推导为：
    // 老编号=（新编号+m-1）%i+1
    public Node josephusKillByFormula(Node head, int m){
        if(head == null || head.next == head || m < 1){
            return head;
        }
        Node cur = head.next;
        int tmp = 1;//统计环的大小
        while(cur != head){
            tmp++;
            cur = cur.next;
        }
        tmp = getLive(tmp, m); //tmp是环的大小，m是喊的数杀死，最终求得的tmp就是剩下的编号，这个编号是上面公式中的老编号
        while(--tmp != 0){
            head = head.next;
        }
        head.next = head;
        return head;
    }

    //使用递归反推出老编号
    public int getLive(int i, int m){
        if(i == 1){
            return 1;
        }
        return (getLive(i - 1, m) + m -1) % i + 1;
    }
}
