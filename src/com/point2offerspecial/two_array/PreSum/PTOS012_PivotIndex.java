package com.point2offerspecial.two_array.PreSum;

/**
 * @Author: zhaomengyi
 * @Date: 2023-01-26 13:08
 * @Description:
 */
public class PTOS012_PivotIndex {

  public int pivotIndex(int[] nums){
    int total = 0;
    for(int i = 0; i < nums.length; i++){
      total += nums[i];
    }
    int sum = 0;
    for(int i = 0; i < nums.length; i++){
      sum += nums[i];
      if(sum - nums[i] == total - sum){//技巧:关键在这里，思路转一下，反正不要中间的，加到[pivot]再减掉它，右侧就是全和减去[pivot]
        return i;
      }
    }
    return -1;
  }
}
