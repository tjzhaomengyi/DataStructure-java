package com.mikemyzhao.LRU;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-10 14:43
 * @Description:方法一(1)使用DoubleLinkList+HashMap方法(2)直接使用Java的LinkedHashMap
 */
public class LRUCache {
  private HashMap<Integer,LRUNode> map;
  private DoubleList cache;
  private int cap;//最大容量

  public  LRUCache(int capacity){
    this.cap = capacity;
    map = new HashMap<>();
    cache = new DoubleList();//cache链表存元素
  }
  //由于同时维护一个双链表的cache和一个哈希表map，很容易漏掉一些操作，比如删除一个key时，在cache中删除了对应的node，但是没有在map中删除
  //解决这种问题的办法是在两个数据结构上一层提供一层抽象的API,让LRU的主方法，避免直接操作map和cache的细节
  /**
   * 将某个key提升为最近使用的
   */
  private void  makeRecently(int key){
    LRUNode x = map.get(key);
    cache.remove(x);//从链表中删除节点，然后再插入到末尾
    cache.addLast(x);
  }

  /**添加最近使用的元素**/
  private void addRecently(int key,int val){
    LRUNode x = new LRUNode(key,val);
    //链表尾部就是最近使用的元素
    cache.addLast(x);
    map.put(key,x);//在map中添加元素
  }

  /**删除某一个key**/
  private void deleteKey(int key){
    LRUNode x = map.get(key);
    //从链表中删除
    cache.remove(x);
    //从map中删除
    cache.remove(x);
  }
  /**删除最久未使用的元素**/
  private void removeLastRecently(){
    //链表头部第一个元素就是最久未使用的
    LRUNode deleteNode = cache.removeFirst();
    //将其从map中删除
    int deleteKey = deleteNode.key;
    map.remove(deleteKey);
  }

  //从LRU中获取数据
  public int get(int key){
    if(!map.containsKey(key)){
      return  -1;
    }
    //将数据提升为最近使用的
    makeRecently(key);
    return  map.get(key).val;
  }
  //从LRU中放入数据
  public void put(int key,int val){
    if(map.containsKey(key)){
      //删除旧的数据
      deleteKey(key);
      //新插入的数据为最近使用的数据
      addRecently(key,val);
    }
    if(cap == cache.size()){
      removeLastRecently();
    }
    addRecently(key, val);
  }


  /***
   * (2)直接使用LinkedHashMap
   */

}
