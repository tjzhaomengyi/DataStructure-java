package com.hotinterview.erfenchazhao;

/**
 * @Author: zhaomengyi
 * @Date: 2022-12-26 6:07 下午
 * @Description:
 */
public class Code0162_FindPeakElement {
  public int findPeakElement(int[] nums) {
    if(nums == null || nums.length == 0) return -1;
    if(nums.length == 1 || nums[0] > nums[1]){
      return 0;
    }
    if(nums[nums.length - 1] > nums[nums.length - 2]){
      return nums.length - 1;
    }

    int left = 0;
    int right = nums.length - 1;
    int mid = 0;
    while(left < right){
      mid = (left + right) >> 1;
      if(nums[mid] > nums[mid + 1]) {
        //如果[mid] > [mid +1] ，则 l 到 mid 有峰值
        right = mid;
      } else {
        //否则的话就是在mid + 1右侧有峰值
        left = mid + 1;
      }
    }
    return mid;
  }

  public static void main(String[] args) {
    int ans = new Code0162_FindPeakElement().findPeakElement(new int[]{1,2,3,1});
    System.out.println(ans);
  }
}
