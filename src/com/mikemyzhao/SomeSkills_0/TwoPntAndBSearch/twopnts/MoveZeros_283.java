package com.mikemyzhao.SomeSkills_0.TwoPntAndBSearch.twopnts;

import java.util.Arrays;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-30 8:28
 * @Description:
 */
public class MoveZeros_283 {
  public void moveZeroes(int[] nums) {
    int n = nums.length;
    int l = 0,r=0;
    //条件就是如果两人都碰上0，就走r，l停
    while(r<n){
      if(nums[r]!=0){
        swap(nums,l,r);
        l++;
      }
      r++;
    }
  }
  void swap(int[] nums,int i,int j){
    int tmp = nums[j];
    nums[j]=nums[i];
    nums[i]=tmp;
  }

  public static void main(String[] args) {
    int[] nums = new int[]{1,0,1};
    new MoveZeros_283().moveZeroes(nums);
    System.out.println(Arrays.toString(nums));
  }
}
