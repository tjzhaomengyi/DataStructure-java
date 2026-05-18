package com.niuke.BM101.cachedesign;


import java.util.*;

/**
 * 使用链表的思路，自己构造节点的 pre、 next 指针即可。
 */
public class LRU {
    private int capacity;
    private Map<Integer, Node> map;
    private Node head;
    private Node tail;
    private int used;
    class Node {
        int key;
        int val;
        Node prev;
        Node next;
        public Node(int key, int val, Node prev, Node next) {
            this.key = key;
            this.val = val;
            this.prev = prev;
            this.next = next;
        }
    }


    public LRU(int capacity) {
// write code here
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.used = 0;
    }

    public int get(int key) {
// write code here
        if(!map.containsKey(key)){
            return -1;
        }
        makeRecently(key);
        return map.get(key).val;
    }

    public void set(int key, int value) {
// write code here
        if(map.containsKey(key)){
            map.get(key).val = value;
            makeRecently(key);
            return;
        }
        //如果容量达到上限，移除尾部节点
        if(used == capacity){
            map.remove(tail.key);
            tail = tail.prev;
            tail.next = null;
            used--;
        }
        //头节点为空
        if(head == null){
            head = new Node(key, value, null, null);
            tail = head;
        } else { //因为是新设置的设置成最新的
            Node t = new Node(key, value, null, head);
            head.prev = t;
            head = t;
        }
        map.put(key, head);
        used++;
    }

    //把 key 对应的节点移动到链表头部
    private void makeRecently(int key){
        Node t = map.get(key);
        if(t != head){
            if(t == tail){
                tail = tail.prev;
                tail.next = null;
            } else {
                t.prev.next = t.next;
                t.next.prev = t.prev;
            }
            t.prev = null; //自己是第一个节点
            t.next = head;
            head.prev = t;
            head = t;
        }
    }



}
