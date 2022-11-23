package com.mikemyzhao.data_structure_design;

import java.util.HashMap;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-18 22:19
 * @Description:
 */
public class LC_0380_InsertDeleteGetRandom {
  class RandomizedSet {

    private HashMap<Integer, Integer> keyIndexMap;//技巧:val对应index
    private HashMap<Integer, Integer> indexKeyMap;
    private int size;

    public RandomizedSet() {
      keyIndexMap = new HashMap<Integer, Integer>();
      indexKeyMap = new HashMap<Integer, Integer>();
      size = 0;
    }

    public boolean insert(int val) {
      if(!keyIndexMap.containsKey(val)){
        keyIndexMap.put(val, size);
        indexKeyMap.put(size++, val);
        return true;
      }
      return false;
    }

    public boolean remove(int val) {
      //技巧:这里找到待删除的元素，这样连续序列会出现空洞，用最后一个元素把indexKeyMap的空洞堵上，让index还保持连续即可
      if(keyIndexMap.containsKey(val)){
        int deleteIndex = keyIndexMap.get(val);
        int lastIndex = --size ;//技巧:这里size自己要减掉
        int lastKey = indexKeyMap.get(lastIndex);
        keyIndexMap.put(lastKey,deleteIndex);//用最后一个元素，把keyindexmap的洞堵上
        indexKeyMap.put(deleteIndex,lastKey);//相反的map再映射一下
        keyIndexMap.remove(val);
        indexKeyMap.remove(lastIndex);//删除最后元素即可，因为最后的元素已经在deleteIndex上填充了
        return true;
      }
      return false;
    }

    //保证每个元素相同概率返回
    public int getRandom() {
      if(size == 0){
        return -1;
      }
      int randomIndex = (int)(Math.random() * size);
      return indexKeyMap.get(randomIndex);

    }
  }

}
