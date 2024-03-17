package com.book.point2offerspecial.nine_heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-12 18:06
 * @Description:
 */
public class PTOS061_KSmallestPairs {
  //因为是k组，并且两个数组分别递增，所以每个数组选择前k个数即可
  public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
    //技巧：根据下标，和对应数求和建立小根堆，每次把最小的放入结果集，然后在塞入新的
    //p1[0]表示第一组nums1中的值，p1[1]表示第一组nums2中的值
    PriorityQueue<int[]> minHeap = new PriorityQueue<>((p1, p2) -> nums1[p1[0]] + nums2[p1[1]] - nums1[p2[0]] - nums2[p2[1]]);
    if(nums2.length > 0){
      for(int i = 0; i < Math.min(k, nums1.length); i++){
        minHeap.offer(new int[]{i, 0});//从nums1数组中找k个和nums[0]组成小根堆
      }
    }
    List<List<Integer>> ans = new ArrayList<>();
    while(k != 0 && !minHeap.isEmpty()){
      int[] ids = minHeap.poll();
      ans.add(Arrays.asList(nums1[ids[0]], nums2[ids[1]]));
      k--;
      if(ids[1] < nums2.length - 1){
        minHeap.offer(new int[]{ids[0], ids[1] + 1});//往后挪一个
      }
    }
    return ans;
  }
}
