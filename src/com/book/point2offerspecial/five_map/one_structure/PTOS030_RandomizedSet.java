package com.book.point2offerspecial.five_map.one_structure;

import java.util.HashMap;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-06 18:20
 * @Description:
 */
public class PTOS030_RandomizedSet {
  private HashMap<Integer, Integer> keyIndexMap;//val对应index
  private HashMap<Integer, Integer> indexKeyMap;//index对应val
  private int size;
  /** Initialize your data structure here. */
  public PTOS030_RandomizedSet() {
    keyIndexMap = new HashMap<Integer, Integer>();
    indexKeyMap = new HashMap<Integer, Integer>();
    size = 0;
  }

  /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
  public boolean insert(int val) {
    if(!keyIndexMap.containsKey(val)){
      keyIndexMap.put(val, size);
      indexKeyMap.put(size++, val);
      return true;
    }
    return false;
  }

  /** Removes a value from the set. Returns true if the set contained the specified element. */
  public boolean remove(int val) {
    if(keyIndexMap.containsKey(val)){
      int deleteIndex = keyIndexMap.get(val);
      int lastIndex = --size;//删掉的前一个 技巧：size-1 即是最后一个元素的下标，也是size的大小
      int lastKey = indexKeyMap.get(lastIndex);
      //技巧：用最后的元素把当前要删除元素的位置堵上
      keyIndexMap.put(lastKey, deleteIndex);//用最后一个元素吧keyindexmap堵上
      indexKeyMap.put(deleteIndex, lastKey);
      keyIndexMap.remove(val);
      indexKeyMap.remove(lastIndex);
      return true;
    }
    return false;
  }

  /** Get a random element from the set. */
  public int getRandom() {
    if(size == 0) return -1;
    int randomIndex = (int)(Math.random() * size);
    return indexKeyMap.get(randomIndex);
  }
}
