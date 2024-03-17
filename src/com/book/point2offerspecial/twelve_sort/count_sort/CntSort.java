package com.book.point2offerspecial.twelve_sort.count_sort;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-14 12:07
 * @Description:计数排序用于范围比较固定的排序，比如员工年龄
 */
public class CntSort {
  public int[] sortArra(int[] nums){
    int min = Integer.MAX_VALUE;
    int max= Integer.MIN_VALUE;
    for(int n : nums){
      min = Math.min(min, n);
      max = Math.max(max, n);
    }

    int[] counts = new int[max - min + 1];
    for(int num : nums){
      counts[num - min] ++;//计数
    }
    int i = 0;
    for(int num = min; num <= max; num++){
      while(counts[num - min] > 0){
        nums[i++] = num;
        counts[num - min]--;
      }
    }
    return nums;
  }
}
