package com.book.zuoshen.InterviewGuide.chpt2.list;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/16 12:51
 * @Description:
 */
public class ListPartition {
    public class Node{
        private int value;
        private Node next;
        public Node(int value){
            this.value = value;
        }
    }

    //借助数组的arrpartition方法,快排的辅助方法
    public Node listPartition(Node head, int pivot){
        if(head == null){
            return head;
        }
        Node cur = head;
        int i = 0;
        while(cur != null){
            i++;
            cur = cur.next;
        }
        Node[] nodeArr = new Node[i];
        i = 0;
        cur = head;
        for(i = 0; i != nodeArr.length; i++){
            nodeArr[i] = cur;
            cur = cur.next;
        }
        netherlandPartition(nodeArr, pivot);
        for(i = 1; i != nodeArr.length; i++){
            nodeArr[i - 1].next = nodeArr[i];
        }
        nodeArr[i - 1].next = null;
        return nodeArr[0];

    }
    //荷兰国旗分区
    public void netherlandPartition(Node[] arr, int pivot){
        //两个位置节点都在外面
        int small = -1;
        int big = arr.length;
        int index = 0;
        while(index != big){
            if(arr[index].value < pivot){
                swap(arr, ++small, index++);//这里稍微画出来就知道了，先扩充在交换值。
            } else if(arr[index].value == pivot){
                index++;
            } else {
                swap(arr, --big, index);
            }
        }
    }

    public void swap(Node[] arr, int a, int b){
        Node tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

}
