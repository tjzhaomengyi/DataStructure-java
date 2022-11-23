package com.hots100;

/**
 * @Author: zhaomengyi
 * @Date: 2021-12-28 18:17
 * @Description:
 */
public class Code136_SingleNumber {
  public int singleNumber(int[] nums) {
    if(nums.length==0) return -1;
    int res = 0;
    for(int i=0;i<nums.length;i++){
      res = res ^ nums[i];
    }
    return res;
  }
}
