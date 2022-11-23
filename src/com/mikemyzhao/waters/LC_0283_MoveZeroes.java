package com.mikemyzhao.waters;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-18 17:24
 * @Description:
 */
public class LC_0283_MoveZeroes {
  public static void moveZeros(int[] nums){
    int to = 0;
    for(int i = 0; i < nums.length; i++){
      if(nums[i] != 0){//技巧:不等于0就换到前面
        swap(nums, to++, i);
      }
    }
  }
  public static  void swap(int[] arr, int i, int j){
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }
}
