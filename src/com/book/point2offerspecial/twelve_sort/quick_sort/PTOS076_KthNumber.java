package com.book.point2offerspecial.twelve_sort.quick_sort;

import java.util.PriorityQueue;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-14 12:33
 * @Description:
 */
public class PTOS076_KthNumber {
  public int findKthLargest(int[] nums, int k) {
    //小根堆
    PriorityQueue<Integer> minHeap = new PriorityQueue();
    for(int n : nums){
      if(minHeap.size() < k){
        minHeap.offer(n);
      } else if(minHeap.peek() < n ){
        minHeap.poll();
        minHeap.offer(n);
      }
    }
    return minHeap.peek();
  }



  //使用荷兰国旗求解

  public int findKthLargestByQS(int[] nums, int k){
    int L = 0;
    int R = nums.length - 1;
    int p = 0;
    int[] range = null;
    while(L < R){
      p = nums[L + (int)(Math.random() * (R - L + 1))];//技巧：如何在数组中取随机数
      range = partition(nums, L, R, p);
    }
    if(k < range[0]){
      R = range[0] - 1;
    } else if(k > range[1]){
      L = range[1] + 1;
    } else {
      return p;
    }
    return nums[L];
  }
  private int[] partition(int[] nums, int L, int R, int p){
    int less = L - 1;
    int more = R + 1;
    int cur = L;
    if(nums[cur] < p){
      swap(nums, cur++, ++less);
    } else if(nums[cur] > p){
      swap(nums, cur, --more);
    } else{
      cur++;
    }
    return new int[]{less + 1, more - 1};//
  }

  private void swap(int[] nums, int i, int j){
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
  }
}
