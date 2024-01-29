package com.book.point2offerspecial.two_array.PreSum;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhaomengyi
 * @Date: 2023-01-25 15:58
 * @Description:
 */
public class PTOS010_SubarraySum {
  public int subarraySum(int[] nums, int k) {
    int[] presum = presum(nums);
    int ans = 0;
    for(int i = 0; i < presum.length; i++){
      if(presum[i] == k){
        ans++;
      }
      for(int j = 0; j < i; j++){
        if(presum[i] - presum[j] == k){
          ans++;
        }
      }
    }
    return ans;
  }

  public int[] presum(int[] nums){
    int[] presum = new int[nums.length];
    for(int i = 0; i < presum.length; i++){
      presum[i] = i == 0 ? nums[i] : presum[i - 1] + nums[i];
    }
    return presum;
  }

  //方法二:借助Map一个O(n)的空间复杂度，达到O(n)的时间复杂度
  public int subArraySum2(int[] nums, int k){
    Map<Integer, Integer> sumtoCount = new HashMap<>();//前i个数字之和
    sumtoCount.put(0 , 1);//这个位置没意义
    int sum = 0;
    int count = 0;
    for(int num : nums){
      sum += num;
      count += sumtoCount.getOrDefault(sum - k, 0);
      sumtoCount.put(sum, sumtoCount.getOrDefault(sum, 0) + 1);
    }
    return count;
  }
}
