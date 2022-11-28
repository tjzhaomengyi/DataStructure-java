package com.hots100.sorts;

/**
 * @Author: zhaomengyi
 * @Date: 2022-11-24 6:31 下午
 * @Description:
 */
public class Code75_NetherlandFlag {

  public void sortColors(int[] nums) {
    netherLand(nums,0, nums.length-1);
  }
  //思路：就是一个荷兰国旗问题
  public void netherLand(int[] nums,int L, int R) {
    int less = L -1;
    int more = R;
    int index = L;
    int target = 1;
    if(nums.length == 0) return;
    if(L > R) return;
    if(L == R) return;
    while (index <= more){ //扣边界
      if(nums[index] == target){
        index++;
      } else if (nums[index] < target) {
        swap(nums, index++, ++less);
      } else {
        swap(nums, index, more--);
      }
    }
  }


  private void swap(int[] nums, int i, int j){
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
  }

  public static void main(String[] args) {
    int[] nums = new int[] {2,0,1};
    new Code75_NetherlandFlag().sortColors(nums);
    for(int i = 0; i < nums.length; i++){
      System.out.print(nums[i]);
    }
  }
}
