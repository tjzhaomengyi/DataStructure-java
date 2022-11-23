package com.mikemyzhao.data_structure_design.LRU;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-10 14:13
 * @Description:定义一个双指针链表辅助LRU操作
 */
public class DoubleList {
  //头尾虚节点
  private LRUNode head,tail;
  //链表元素个数
  private int size;

  public DoubleList(){
    //初始化双向链表的数据
    head = new LRUNode(0,0);
    tail = new LRUNode(0,0);
    head.next =tail;
    tail.prev = head;
    size=0;
  }

  //在链表尾部添加节点x，时间复杂度O(1)
  public void addLast(LRUNode x){
    x.prev = tail.prev;
    x.next = tail;
    tail.prev.next=x;
    tail.prev = x;
    size++;
  }
  //删除链表中x节点
  public void remove(LRUNode x){
    x.prev.next=x.next;
    x.next.prev = x.prev;
    size--;
  }

  //删除链表第一个节点
  public LRUNode removeFirst(){
    if(head.next==tail){
      return null;
    }
    LRUNode first = head.next;
    remove(first);
    return first;
  }

  //返回链表长度
  public int size(){
    return size;
  }


}
