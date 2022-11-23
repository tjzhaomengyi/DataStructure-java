package com.mikemyzhao.Hash_10.bigshua;

import java.util.HashMap;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-14 21:58
 * @Description:设计一个Hash接口可以在O(1)的时间复杂度内完成setAll的操作，同时其他的操作扔维持O(1)
 */
public class HashSetAll_2 {
  //这个内部类记录setall的全局结果，有setall的值和设置时间
  public static class MyValue<V>{
    public V value;
    public long time;

    public MyValue(V v,long t){
      value = v;
      time = t;
    }
  }

  public static class  MyHashMap<K,V>{
    private HashMap<K,MyValue<V>> map;
    private long time ;
    private MyValue<V> setAll;

    public MyHashMap(){
      map = new HashMap<>();
      time = 0;
      setAll = new MyValue<V>(null,-1);
    }
    public void put(K key,V value){
      map.put(key,new MyValue<V>(value,time++));
    }
    public void setAll(V value) {
      setAll = new MyValue<V>(value,time++);
    }

    public V get(K key){
      if(!map.containsKey(key)){
        return null;
      }
      if(map.get(key).time > setAll.time){
        return map.get(key).value;
      }else{
        return setAll.value;
      }
    }
  }
}
