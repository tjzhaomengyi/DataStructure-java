package com.hotinterview.math;

/**
 * @Author: zhaomengyi
 * @Date: 2022-12-20 11:38 上午
 * @Description:
 */
public class Code0013_RomanToInteger {
  public int romanToInt(String s) {
    if(s == null || s.length() == 0) return -1;
    char[] str = s.toCharArray();
    int[] nums = new int[str.length];
    int sum = 0;
    for(int i = 0; i < str.length; i++){
      if(str[i] == 'M'){
        nums[i] = 1000;
      } else if (str[i] == 'D'){
        nums[i] = 500;
      } else if(str[i] == 'C'){
        nums[i] = 100;
      } else if(str[i] == 'L'){
        nums[i] = 50;
      } else if(str[i] == 'X'){
        nums[i] = 10;
      } else if(str[i] == 'V'){
        nums[i] = 5;
      } else if(str[i] == 'I'){
        nums[i] = 1;
      }
    }
    for(int i = 0; i < nums.length - 1; i++) {
        if (nums[i] < nums[i + 1]) {
          sum = sum - nums[i];
        } else {
          sum = sum + nums[i];
        }
      }

    return sum + nums[nums.length - 1];
  }
}
