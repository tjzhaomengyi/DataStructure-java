package com.hots100.zhijiegan;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2022-12-18 3:25 下午
 * @Description:
 */
public class Code0560_SubarraySum {
  //技巧：前缀和，还可以使用Map解决前缀和循环遍历的问题，参考PTOS010_SubarraySum:subArraySum2()
  public int subarraySum(int[] nums, int k) {
    int ans = 0;
    if(nums == null || nums.length == 0) return 0;
    int[] presum = presum(nums);
    for(int i = 0; i < presum.length; i++){
      if(presum[i] == k) ans++;
      for(int j = 0; j < i; j++){
        if(presum[i] - presum[j] == k) ans++;
      }
    }
    return ans;
  }


  public int[] presum(int[] nums){
    int[] presum = new int[nums.length];
    presum[0] = nums[0];
    for(int i = 1; i < nums.length; i++){
      presum[i] = presum[i - 1] + nums[i];
    }
    return presum;
  }

}
