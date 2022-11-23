package com.mikemyzhao.waters;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-16 10:26
 * @Description:
 */
public class LC_0026_RemoveDuplicatesFromSortedArray {
  public int removeDuplicates(int[] nums) {
    if(nums == null){
      return 0;
    }
    if(nums.length < 2){
      return nums.length;
    }
    int done = 0;
    for(int i = 1; i < nums.length; i++){
      if(nums[i] != nums[done]){
        nums[++done] = nums[i];
      }
    }
    return done + 1;
  }
}
