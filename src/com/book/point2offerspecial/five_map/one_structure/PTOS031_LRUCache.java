package com.book.point2offerspecial.five_map.one_structure;

import java.util.HashMap;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-06 19:24
 * @Description:
 */
//技巧：1、一定要实现两个类
public class PTOS031_LRUCache {
  private MyCache cache;
  public PTOS031_LRUCache(int capacity){
    cache = new MyCache(capacity);
  }
  public int get(int key){
    return cache.get(key);
  }

  public void put(int key, int value){
    cache.set(key, value);
  }
  //两个重要的类
  //1、LRUNode
  public  class LRUNode{
    public int key, val;
    public LRUNode next, prev;

    public LRUNode(int k, int v){
      this.key = k;
      this.val = v;
    }
  }

  //2、记录使用的双向链表
  public  class NodeDoubleLinkedList{
    private LRUNode head;
    private LRUNode tail;

    public NodeDoubleLinkedList(){
      head = null;
      tail = null;
    }
    //技巧：一定要实现三个函数，添加节点，把某个节点移动到末尾，删除头结点【如果map比设置的cap大了，就要删除head】

    //新加入LRU的结点放到tail
    public void addNode(LRUNode newNode){
      if(newNode == null){
        return;
      }
      if(head == null){
        head = newNode;
        tail = newNode;
      } else {
        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
      }
    }

    //调整某个node在双向链表中的位置
    public void moveNodeToTail(LRUNode node){
      if(tail == node) return;
      if(head == node){
        head = head.next;
        head.prev = null;
      } else {
        node.prev.next = node.next;
        node.next.prev = node.prev;
      }
      node.prev = tail;
      node.next = null;
      tail.next = node;
      tail = node;
    }

    public LRUNode removeHead(){
      if(head == null) return null;
      LRUNode ans = head;
      if(head == tail){
        head = null;
        tail = null;
      } else {
        head = ans.next;
        ans.next = null;
        ans.prev = null;
      }
      return ans;
    }
  }

  public  class MyCache{
    private HashMap<Integer, LRUNode> keyNodeMap;
    private NodeDoubleLinkedList nodeList;
    private final int capacity;

    public MyCache(int cap){
      keyNodeMap = new HashMap<Integer, LRUNode>();
      nodeList = new NodeDoubleLinkedList();
      capacity = cap;
    }

    public int get(int key){
      if(keyNodeMap.containsKey(key)){
        LRUNode ans = keyNodeMap.get(key);
        nodeList.moveNodeToTail(ans);
        return ans.val;
      }
      return -1;
    }

    public void set(int key, int val){
      if(keyNodeMap.containsKey(key)){
        LRUNode node = keyNodeMap.get(key);
        node.val = val;
        nodeList.moveNodeToTail(node);
      } else {//新增一个结点
        LRUNode newNode = new LRUNode(key, val);
        keyNodeMap.put(key, newNode);
        nodeList.addNode(newNode);
        if(keyNodeMap.size() == capacity + 1){
          LRUNode r = nodeList.removeHead();
          keyNodeMap.remove(r.key);
        }
      }
    }
  }
}
