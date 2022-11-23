package com.mikemyzhao.SomeSkills_0.TwoPntAndBSearch.bsearch.bigshua;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-16 10:31
 * @Description:
 * 技巧:找刚好小于这个数的最右边，然后+1看是不是target目标数;同理找target+1小的最右边，就是target的最右边
 */
public class LC_0034_FindFirstAndLastPositionOfElementSortedArray {
  public int[] searchRange(int[] nums, int target) {
    if(nums == null || nums.length == 0){
      return new int[]{-1, -1};
    }
    int L = lessMostRight(nums, target) + 1;
    if(L == nums.length || nums[L] != target){
      return new int[]{-1,-1};
    }
    return new int[] {L, lessMostRight(nums, target + 1)};
  }

  //找小于target数的最右位置，如果找到这个数的下一位是target就成功
  public static int lessMostRight(int[] arr, int num){
    int L = 0;
    int R = arr.length - 1;
    int M = 0;
    int ans = -1;
    while(L <= R){
      M = L + ((R - L) >> 1);
      if(arr[M] < num){
        ans = M;
        L = M + 1;
      } else {
        R = M - 1;
      }
    }
    return ans;
  }
}
