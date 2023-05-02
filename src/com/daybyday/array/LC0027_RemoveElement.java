package com.daybyday.array;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-28 18:12
 * @Description:
 */
public class LC0027_RemoveElement {

  public int removeElement(int[] nums, int val) {
    int len = nums.length;
    int more = len;
    int i = 0;
    while(i != more && i < len){
      if(nums[i] == val){
        swap(nums, i, --more);
      }
      i++;
    }
    return i + 1;
  }

  public void swap(int[] nums, int i, int j){
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
  }
}
