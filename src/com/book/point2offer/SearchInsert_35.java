package com.book.point2offer;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-29 22:19
 * @Description:找到坏的版本
 */
public class SearchInsert_35 {
  public int searchInsert(int[] nums, int target) {
    if(nums.length==0){
      nums[0]=target;
    }
    int l=0,r=nums.length-1;
    while(l<=r){
      int mid = l+(r-l)/2;
      if(nums[mid]==target){
        return mid;
      }else if(nums[mid]>target){
        r=mid-1;
      }else if(nums[mid]<target){
        l=mid+1;
      }
    }
    return l;
  }
}
