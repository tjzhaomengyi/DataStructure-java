package com.mikemyzhao.SomeSkills_0.SumAndPreSum_0;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-18 13:21
 * @Description:
 */
public class LC_0238_ProductExceptSelf {
  //计算得到除了当前位置所有数的乘积
  //数学结论:前缀乘积 然后 从后往前填写
  public int[] productExceptSelf(int[] nums) {
    int n = nums.length;
    int[] ans = new int[n];
    ans[0] = nums[0];
    for(int i = 1; i < n; i++){
      ans[i] = ans[i - 1] * nums[i];
    }
    int right = 1;
    for(int i = n -1; i > 0; i--){
      ans[i] = ans[i - 1] * right;
      right *= nums[i];
    }
    ans[0] = right;
    return ans;
  }
}
