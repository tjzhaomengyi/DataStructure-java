package com.book.zuoshen.InterviewGuide.chpt9.datastructure;

import java.util.HashMap;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/28 17:06
 * @Description:
 */
public class HashMapSetAll {
    public class MyValue<V>{
        private V value;
        private long time;

        public MyValue(V value, long time){
            this.value = value;
            this.time = time;
        }

        public V getValue() {
            return this.value;
        }

        public long getTime(){
            return this.time;
        }
    }

    public class MyHashMap<K, V>{
        private HashMap<K, MyValue<V>> baseMap;
        private long time;
        private MyValue<V> setAll;

        public MyHashMap() {
            this.baseMap = new HashMap<K, MyValue<V>>();
            this.time = 0;
            this.setAll = new MyValue<V>(null, -1);
        }

        public boolean containsKey(K key){
            return this.baseMap.containsKey(key);
        }

        public void put(K key, V value){
            this.baseMap.put(key, new MyValue<V>(value, this.time++));
        }

        public void setAll(V value){
            this.setAll = new MyValue<V>(value, this.time++);
        }

        public V get(K key){
            if(this.containsKey(key)){
                if(this.baseMap.get(key).getTime() > this.setAll.getTime()){
                    return this.baseMap.get(key).getValue();
                } else {
                    return this.setAll.getValue();
                }
            } else {
                return null;
            }
        }
    }
}
