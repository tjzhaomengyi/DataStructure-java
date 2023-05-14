package com.zhousai.week341;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-22 10:42
 * @Description:
 */
public class Kezhengchudefenzuida {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while(in.hasNextLine()){

    }
  }

  public int maxDivScore(int[] nums, int[] divisors) {
    Arrays.sort(nums);
    Arrays.sort(divisors);
    int[] tmp_ans = new int[divisors.length];
    int max_cnt = 0;
    int ans = -1;
    for(int i = 0; i < nums.length; i++){
      for(int j = 0; j < divisors.length; j++){
        int n = nums[i];
        int d = divisors[j];
        if(d > n){
          continue;
        }
        if(n % d == 0){
          tmp_ans[j]++;
        }
      }
    }
    for(int i = 0; i < tmp_ans.length; i++){
      if(max_cnt < tmp_ans[i]){
        ans = divisors[i];
        max_cnt = tmp_ans[i];
      }
    }
    return ans == -1 ? divisors[0] : ans;
  }

}
