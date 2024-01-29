package com.book.point2offer;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-06 15:27
 * @Description:
 */
public class ExchangeArr_21 {
  public int[] exchange(int[] nums) {
    int n = nums.length;
    int i=0, j=n-1,tmp;
    while(i<j){
      while(i<j && (nums[i] & 1)==1)i++;
      while(i<j && (nums[j]&1)==0)j--;
      tmp = nums[i];
      nums[i]=nums[j];
      nums[j]=tmp;
    }
    return nums;
  }
}
