package com.mikemyzhao.SomeSkills_0.bigshua;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-15 10:41
 * @Description:找到数组中的无序子数组长度
 * @解法：从左往右找到右边界，从右往左找到左边界
 */
public class FindUnsortedArray_02 {
  public static int findUnsortedArray(int[] nums){
    if(nums == null || nums.length < 2){
      return 0;
    }
    int N = nums.length;
    int right = -1;
    //解法：从左边找到最大在的位置，然后在从右边开始往左找到
    int max = Integer.MIN_VALUE;
    for(int i = 0; i < nums.length; i++){
      if(max > nums[i]){
        right = i;
      }
      max = Math.max(max,nums[i]);
    }

    int min = Integer.MAX_VALUE;
    int left = N;
    for(int i = N - 1; i >= 0; i--){
      if(min < nums[i]){
        left = i;
      }
      min = Math.min(min,nums[i]);
    }
    return Math.max(0,right-left+1);
  }
}
