package com.book.point2offerspecial.two_array.PreSum;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhaomengyi
 * @Date: 2023-01-26 10:48
 * @Description:
 */
public class PTOS011_FindMax01Length {
  public int findMaxLength(int[] nums) {
//    int[] trans = new int[nums.length];
//    for(int i = 0; i < nums.length; i++){
//      trans[i] = nums[i] == 0 ? -1 : 1;
//    }
//    return subarraySum(trans,0);
    //技巧:使用map减少遍历的查询时间
    Map<Integer, Integer> sumToIndex = new HashMap();
    sumToIndex.put(0, -1);//
    int sum = 0;
    int maxLength = 0;
    for(int i = 0; i < nums.length; ++i){
      sum += nums[i] == 0 ? -1 : 1;
      if(sumToIndex.containsKey(sum)){
        maxLength = Math.max(maxLength, i - sumToIndex.get(sum));
      } else {
        sumToIndex.put(sum, i);
      }
    }
    return maxLength;
  }
//  public int subarraySum(int[] nums, int k) {
//    int[] presum = presum(nums);
//    int ans = 0;
//    for(int i = 0; i < presum.length; i++){
//      if(presum[i] == k){
//        ans = Math.max(ans, i + 1);
//      }
//      for(int j = 0; j < i; j++){
//        if(presum[i] - presum[j] == k){
//          ans = Math.max(ans, i - j );
//        }
//      }
//    }
//    return ans;
//  }
//
//  public int[] presum(int[] nums){
//    int[] presum = new int[nums.length];
//    for(int i = 0; i < presum.length; i++){
//      presum[i] = i == 0 ? nums[i] : presum[i - 1] + nums[i];
//    }
//    return presum;
//  }

  public static void main(String[] args) {
    int ans = new PTOS011_FindMax01Length().findMaxLength(new int[]{0,1,0});
    System.out.print(ans);
  }
}
