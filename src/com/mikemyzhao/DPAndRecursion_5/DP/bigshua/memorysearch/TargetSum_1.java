package com.mikemyzhao.DP_5.bigshua.memorysearch;

import java.util.HashMap;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-13 9:56
 * @Description:给定数组，每个数可以进行加减，给定target求多少种方法
 * @LC494
 * @优化：可以先把数组都变成整数，然后通过两个数组p-n=t推导出p=(t+sum)/2，给出前缀和查找即可
 */
public class TargetSum_1 {
  public static int findTargetSumWays(int[] nums, int i, int target, HashMap<Integer, Integer> integerIntegerHashMap) {
    return process(nums,0,target,new HashMap<>());//使用傻缓存即可
  }
  public static int process(int[] nums, int index, int rest, HashMap<Integer, HashMap<Integer, Integer>> dp){
    if(dp.containsKey(index) && dp.get(index).containsKey(rest)){
      return dp.get(index).get(rest);
    }
    int ans = 0;
    if(index == nums.length){
      ans = rest==0?1:0;
    }else{
      //这个值不是加-号就是加+号肯定要用
      ans = process(nums,index+1,rest+nums[index],dp)+process(nums,index+1,rest-nums[index],dp);
    }
    if(!dp.containsKey(index)){
      dp.put(index,new HashMap<>());
    }
    dp.get(index).put(rest,ans);
    return ans;
  }

  public static void main(String[] args) {
    System.out.println(findTargetSumWays(new int[]{1,1,1,1,1},0,3,new HashMap<Integer,Integer>()));
  }
}
