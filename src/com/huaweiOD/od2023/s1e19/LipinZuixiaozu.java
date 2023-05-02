package com.huaweiOD.od2023.s1e19;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-26 16:29
 * @Description:
 */
public class LipinZuixiaozu {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      int max = Integer.valueOf(in.nextLine());
      String[] str = in.nextLine().split(" ");
      int[] nums = new int[str.length];
      for(int i = 0; i < nums.length; i++){
        nums[i] = Integer.valueOf(str[i]);
      }
      Arrays.sort(nums);
      int l = 0;
      int r = nums.length - 1;
      int cnt  = 0;
      while(l < r){
        int lval = nums[l];
        int rval = nums[r];
        if(lval + rval > max){
          cnt++;
          r--;
        } else if(lval + rval <= max){
          cnt++;
          l++;
          r--;
        }
      }
      if(l == r){
        cnt++;
      }
      System.out.println(cnt);
    }
  }
}
