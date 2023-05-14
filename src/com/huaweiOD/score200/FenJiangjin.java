package com.huaweiOD.score200;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-05-12 16:53
 * @Description:
 */
public class FenJiangjin {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextInt()) {
      int length = in.nextInt();
      int[] nums = new int[length];
      for(int i = 0; i < length; i++){
        nums[i] = in.nextInt();
      }
      //int[] moneys = new int[length];
      for(int i = 0; i < length; i++){
        System.out.print(getMoney(nums, i) + " ");
      }
    }
  }

  public static int getMoney(int[] nums, int i){
    int money = nums[i];
    int cur = nums[i];
    if(i == nums.length - 1){
      return cur;
    }
    for(int c = i + 1; c < nums.length; c++){
      int next = nums[c];
      if(next > cur){
        money = (c - i) * (next - cur);
        return money;
      }
    }
    return money;
  }

}
