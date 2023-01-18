package com.hots100.daxiaogendui;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author: zhaomengyi
 * @Date: 2022-12-15 8:03 下午
 * @Description:
 */
public class Code0215_KthMinNumber {
  public static class MinHeapComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
      return o1 - o2;
    }

  }
  public int findKthLargest(int[] nums, int k) {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>(new MinHeapComparator());
    for(int i = 0; i < k; i++){
      minHeap.add(nums[i]);
    }
    for(int i = k; i < nums.length; i++){
      if(nums[i] > minHeap.peek()){
        minHeap.poll();
        minHeap.add(nums[i]);
      }
    }
    return minHeap.peek();
  }
}
