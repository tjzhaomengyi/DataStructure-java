package com.daybyday.heap;

import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-28 10:12
 * @Description:
 */
public class LC2363_MergeSimilarItems {


  public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
    int N = items1.length;
    int M = items2.length;
    TreeMap<Integer, Integer> minHeap = new TreeMap<Integer, Integer>((o1, o2) -> o1 - o2);
    for(int i = 0; i < N; i++){
      minHeap.put(items1[i][0], items1[i][1]);
    }
    for(int i = 0; i < M; i++){
      if(minHeap.containsKey(items2[i][0])){
        minHeap.put(items2[i][0], minHeap.get(items2[i][0]) + items2[i][1]);
      } else {
        minHeap.put(items2[i][0], items2[i][1]);
      }
    }

    List<List<Integer>> ans = new ArrayList<>();
    for(Integer key : minHeap.keySet()){
      ans.add(new ArrayList<Integer>(Arrays.asList(key, minHeap.get(key))));
    }
    return ans;
  }

}
