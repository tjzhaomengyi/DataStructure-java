package com.point2offerspecial.two_array.ArrAndTwoPnt;

/**
 * @Author: zhaomengyi
 * @Date: 2023-01-22 12:02
 * @Description:
 */
public class PTOS0009_NumSubarrayLessThanK {
  //滑动窗口
  public int numSubarrayProductLessThanK(int[] nums, int k) {
    int L = 0;
    int product = 1;
    int N = nums.length;
    int ans = 0;
    for(int R = 0; R < N; R++){
      product = product * nums[R];
      while(L <= R && product >= k){//只要不符合要求就缩减范围
        product = product / nums[L];
        L++;
      }
      //符合要求的
      ans += R >= L ? R - L + 1 : 0;
    }
    return ans;
  }
}
