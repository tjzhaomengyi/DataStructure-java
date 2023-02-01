package com.point2offerspecial.two_array.ArrAndTwoPnt;

/**
 * @Author: zhaomengyi
 * @Date: 2023-01-20 22:50
 * @Description:
 */
public class PTOS008_MinLengthSubArray {
  public int minSubArrayLen(int target, int[] nums) {
    int p1 = 0;
    int ans = Integer.MAX_VALUE;
    int sum = 0;
    for(int p2 = 0; p2 < nums.length; p2++){
      sum += nums[p2];
      while(p1 <= p2 && sum >= target){
        ans = Math.min(ans, p2 - p1 + 1);//已经满足条件，更新最短长度答案
        //把左侧指针向左移动
        sum -= nums[p1];
        p1++;
      }
    }
    return ans == Integer.MAX_VALUE ? 0 : ans;
  }
}
