package com.mikemyzhao.SomeSkills_0.tricks;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-18 8:58
 * @Description:
 */
public class LC_0189_ThreeReverseToRotate {
  //技巧:三逆序
  public void rotate(int[] nums, int k){
    int N = nums.length;
    k = k % N;
    reverse(nums, 0, N - k -1);
    reverse(nums, N - k, N - 1);
    reverse(nums, 0, N - 1);
  }
  public static void reverse(int[] nums, int L, int R){
    while(L < R){
      int tmp = nums[L];
      nums[L++] = nums[R];
      nums[R--] = tmp;
    }
  }
}
