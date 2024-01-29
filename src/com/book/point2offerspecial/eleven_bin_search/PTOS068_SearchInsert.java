package com.book.point2offerspecial.eleven_bin_search;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-13 17:08
 * @Description:
 */
public class PTOS068_SearchInsert {
  public int searchInsert(int[] nums, int target) {
    if(nums == null) return 0;
    int l = 0;
    int r = nums.length - 1;
    while(l <= r){
      int mid = (l + r) / 2;
      if(nums[mid] == target){
        return mid;
      }
      if(nums[mid] > target){
        r = mid - 1;
      } else {
        l = mid + 1;
      }
    }
    return l;//技巧：跳出位置就是插入的位置
  }
}
