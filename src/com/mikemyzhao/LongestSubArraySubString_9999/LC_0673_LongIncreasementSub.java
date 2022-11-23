package com.mikemyzhao.LongestSubArraySubString_9999;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-19 10:10
 * @Description:
 */
public class LC_0673_LongIncreasementSub {
  public static int findNumberOfLIS(int[] nums){
    if(nums == null || nums.length == 0){
      return 0;
    }
    int n = nums.length;
    int[] lens = new int[n];
    int[] cnts = new int[n];
    lens[0] = 1;
    cnts[0] =1;
    int maxLen = 1;
    int allCnt  =1;
    for(int i = 1; i < n; i++){
      int preLen = 0;
      int preCnt = 1;
      for(int j = 0; j < i; j++){
        if(nums[j] >= nums[i] || preLen > lens[j]){
          continue;
        }
        if(preLen < lens[j]){
          preLen = lens[j];
          preCnt = cnts[j];
        } else {
          preCnt += cnts[j];
        }
      }
      lens[i] = preLen + 1;
      cnts[i] = preCnt;
      if(maxLen < lens[i]){
        maxLen = lens[i];
        allCnt = cnts[i];
      } else if(maxLen == lens[i]){
        allCnt += cnts[i];
      }
    }
    return allCnt;
  }
}
