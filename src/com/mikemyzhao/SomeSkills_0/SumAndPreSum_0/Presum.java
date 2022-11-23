package com.mikemyzhao.SomeSkills_0.SumAndPreSum_0;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-13 19:55
 * @Description:前缀和问题，给数组nums=[1,1,1,2],k=2，返回3
 */
public class Presum {
  /**记住使用哈希表可以有效解决for循环多层嵌套的问题**/
  int subArraySum(int[] nums,int k){
    int n = nums.length;
    //map前缀和->该前缀出现的次数
    Map<Integer,Integer> preSum = new HashMap<>();
    preSum.put(0,1);//base case
    int ans =0,sum0_i = 0;
    for(int i=0;i<n;i++){
      sum0_i+=nums[i];
      //我们要找的前缀和sum0_j
      int sum0_j = sum0_i - k;
      //如果前面有这个前缀和，直接更新答案
      if(preSum.containsKey(sum0_j)){
        ans+=preSum.get(sum0_j);
      }
      preSum.put(sum0_i,preSum.getOrDefault(sum0_i,0)+1);
    }
    return ans;
  }
}
