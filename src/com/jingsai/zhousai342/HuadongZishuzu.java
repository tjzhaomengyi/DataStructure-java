package com.jingsai.zhousai342;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-23 11:08
 * @Description:
 */
public class HuadongZishuzu {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
//    while (in.hasNext()) {
//      String str = in.next();
      int[] nums = new int[]{1, -1, -3, -2, 3};
      int k = 3;
      int x = 2;
      int[] ans = getSubarrayBeauty(nums, 3, 2);
      System.out.println(String.valueOf(ans));
//    }
  }
  public static int[] getSubarrayBeauty(int[] nums, int w_len, int x) {
    int n = nums.length;
    int[] heap = new int[101];//进行计数
    int[] arr = new int[n - w_len + 1];
    for(int i = 0; i < n; i++){
      heap[nums[i] + 50]++;
      if(i >= w_len - 1){
        //获取heap中第x小的数
        int xth = smallX(heap, x);
        arr[i - w_len + 1] = Math.min(xth, 0);
        --heap[nums[i - w_len + 1] + 50] ;
      }
    }
    return arr;
  }

  public static int smallX(int[] heap, int x){
    int sum = 0;
    for(int i = 0; i < heap.length; i++){
      sum += heap[i];
      if(sum < x) continue;
      return i - 50;
    }
    return 0;
  }





}
