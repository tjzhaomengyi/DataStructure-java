package com.mikemyzhao.TwoPntAndBSearch.twopnts;

import java.util.Arrays;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-30 7:50
 * @Description:
 */
public class ReverseArr_189 {
  public void rotate(int[] nums, int k) {
    int n = nums.length;
    reverse(nums,0,n-1);
    reverse(nums,0,k-1);
    reverse(nums,k,n-1);

  }

  private void reverse(int[] nums,int start,int end){
    while(start<=end){
      int tmp = nums[end];
      nums[end]=nums[start];
      nums[start]=tmp;
      start++;
      end--;
    }
  }
  public static void main(String[] args) {
    int[] nums = new int[]{1,2,3,4,5,6,7,8};
    new ReverseArr_189().rotate(nums,3);
    System.out.println(Arrays.toString(nums));
  }
}
