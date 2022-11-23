package com.mikemyzhao.SomeSkills_0.TwoPntAndBSearch.twopnts.bigshua;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-19 12:59
 * @Description:
 */
public class LC_0448_FindDisappearNumber_Like0287 {
  public static List<Integer> findDisappearedNumbers(int[] nums) {
    List<Integer> ans = new ArrayList<>();
    if(nums == null || nums.length == 0){
      return ans;
    }
    int N = nums.length;
    for(int i = 0; i < N; i++){
      //从i开始，循环怼
      walk(nums ,i);
    }
    for(int i = 0; i < N; i++){
      if(nums[i] != i + 1){
        ans.add(i + 1);
      }
    }
    return ans;
  }

  public static void walk(int[] nums, int i){
    while(nums[i] != i + 1){//i位置不是i+1
      int nexti = nums[i] - 1;//找当前这个位置的对应下标
      if(nums[nexti] == nexti + 1){
        break;
      }
      swap(nums, i, nexti);
    }
  }
  public static void swap(int[] nums, int i, int j){
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
  }
}
