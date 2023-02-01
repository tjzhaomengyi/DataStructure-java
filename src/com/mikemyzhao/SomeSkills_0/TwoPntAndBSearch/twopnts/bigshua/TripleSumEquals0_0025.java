package com.mikemyzhao.SomeSkills_0.TwoPntAndBSearch.twopnts.bigshua;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-07 15:35
 * @Description:在有序数组中找到所有和为0的三元组
 */
public class TripleSumEquals0_0025 {
  //技巧：先给出二元组的解法，然后用二元组代替三元组的解法
  //技巧：二元组和为0,在0到end上找到和为0的二元组
  public static List<List<Integer>> twoSum(int[] nums, int end, int target){
    int L = 0;
    int R = end;
    List<List<Integer>> ans = new ArrayList<>();
    while(L < R){
      if(nums[L] + nums[R] > target){
        R--;
      } else if(nums[L] + nums[R] < target){
        L++;
      } else{
        if(L == 0 || nums[L - 1] != nums[L]){//技巧：L=0左边没有数，所以也是不一样
          List<Integer> cur = new ArrayList<>();
          cur.add(nums[L]);
          cur.add(nums[R]);
          ans.add(cur);
        }
        L++;
      }
    }
    return ans;
  }

  public static List<List<Integer>> threeSum(int[] nums){
    Arrays.sort(nums);
    int N = nums.length;
    List<List<Integer>> ans = new ArrayList<>();
    for(int i = N - 1; i > 1; i--){
      if(i == N - 1 || nums[i] != nums[i + 1]){//技巧：从后往前找，这样当前这个[i]可以顺利塞到list的结尾，和元数组保持一致，对于ArrayList把数塞到最后
        List<List<Integer>> nexts = twoSum(nums,i - 1, -nums[i]);
        for(List<Integer> cur : nexts){
          cur.add(nums[i]);
          ans.add(cur);
        }
      }
    }
    return ans;
  }
}
