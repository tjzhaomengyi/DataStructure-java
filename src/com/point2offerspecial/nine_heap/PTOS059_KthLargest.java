package com.point2offerspecial.nine_heap;

import java.util.PriorityQueue;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-12 15:37
 * @Description:
 */
public class PTOS059_KthLargest {
  private PriorityQueue<Integer> minHeap;//小根堆每次返回堆顶就是第k小
  int size;
  public PTOS059_KthLargest(int k, int[] nums) {
    size = k;
    minHeap = new PriorityQueue<>();
    for(int n : nums){
      add(n);
    }
  }

  public int add(int val) {//每次返回第K大的数，即minHeap的栈顶
    if(minHeap.size() < size){
      minHeap.offer(val);
    } else if(val > minHeap.peek()){
      minHeap.poll();
      minHeap.offer(val);
    }
    return minHeap.peek();
  }
}
