package com.mikemyzhao.data_structure_design.LFU;

import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-10 18:18
 * @Description:最多使用Cache实现
 */
public class LFUCache {
  /**
   * 需要的数据结构：
   * (1)使用一个HashMap存储Key到val的映射
   * (2)使用一个HashMap存储key到freq的映射
   * (3)LFU算法核心：
   * 3a.freq到key的映射
   * 3b.将freq最小的key删除，应该得到所有key最小的freq，需要使用一个minFreq记录最小值
   * 3c.可能有个多个key有相同的freq，一个freq要有一个key对应列表
   * 3d.系统freq对应的key的列表是存在时序的，便于快速查找并删除最旧的key
   * 3e.希望能够快速删除key列表中任意一个key，因为如果频次为freq的某个key被访问，那么它的频次就会变成freq+1,就应该从freq对应的key列表删除，
   *  再加入到freq+1对应的key列表中
   *
   *  使用LinkedHashSet可以满足上面(3)的需求
   * **/

  //key到val的映射，后面称为kv表
  HashMap<Integer,Integer> keyToVal;
  //key到freq的映射，后面称为KF表
  HashMap<Integer,Integer> keyToFreq;
  //freq到key列表映射，后面称为FK表
  HashMap<Integer, LinkedHashSet<Integer>> freqToKeys;
  //记录最小的频次
  int minFreq;
  //记录LFU缓存的最大容量
  int cap;

  public LFUCache(int capacity){
    keyToVal = new HashMap<>();
    keyToFreq = new HashMap<>();
    freqToKeys = new HashMap<>();
    this.minFreq = 0;
  }

  public int get(int key){
    if(!keyToVal.containsKey(key)){
      return -1;
    }
    increaseFreq(key);
    return keyToVal.get(key);
  }

  /**增加key的使用频率
  * put增加，get增加
   * 若在keyToVal中存在该key，更新(不需要考虑容量)；否则添加(考虑容量)
   * (1)更新的话后将KF表更新：key对应的freq+1；将FK表更新，将原有Freq的key集合中的该key删除并在freq+1中添加该key
   * (2)添加的话就直接在这两个里面加(考虑容量！)
   * */
  public void put(int key,int val){
    if(this.cap<=0) return;
    /**若key已经存在，修改对应的val即可**/
    if(keyToVal.containsKey(key)){
      keyToVal.put(key,val);
      increaseFreq(key);//这里集体做更新操作
      return;
    }
    /**key不存在，需要插入
     * 容量满的话需要淘汰一个freq最小的key
     * **/
    if(this.cap<=keyToVal.size()){
      removeMinFreqKey();
    }
    /**因为是新的值插入key和val，对应的freq为1**/
    //插入KV表
    keyToVal.put(key,val);
    //插入KF表
    keyToFreq.put(key,1);
    //插入FK表
    freqToKeys.putIfAbsent(1,new LinkedHashSet<>());
    freqToKeys.get(1).add(key);
    this.minFreq=1;//当前
  }

  /**LFU核心逻辑，找到最小freq的key列表，进行删除**/
  private void removeMinFreqKey() {
    //freq最小的key里列表
    LinkedHashSet<Integer> keyList = freqToKeys.get(this.minFreq);
    int deleltedKey = keyList.iterator().next();
    /**更新FK表**/
    keyList.remove(deleltedKey);
    if(keyList.isEmpty()){
      freqToKeys.remove(this.minFreq);
      //因为这里是插入不够用了，所以在增加新键值对的时候会把值默认添加为1，自然就还是最小的
    }
    /**更新KV表**/
    keyToVal.remove(deleltedKey);
    keyToFreq.remove(deleltedKey);
  }

  private void increaseFreq(int key) {
    int freq = keyToFreq.get(key);
    //更新KF表
    keyToFreq.put(key,freq+1);
    //更新FK表，将key从freq中删除
    freqToKeys.get(freq).remove(key);
    //将key添加到freq+1中
    freqToKeys.putIfAbsent(freq+1,new LinkedHashSet<>());
    freqToKeys.get(freq+1).add(key);
    //如果freq对应的里诶博爱空了，移除这个freq
    if (freqToKeys.get(freq).isEmpty()){
      freqToKeys.remove(freq);
      //如果这个freq是minFreq,更新minFreq
      if(freq==this.minFreq){
        this.minFreq++;
      }
    }
  }
}
