package com.huaweiOD.od2023.s1e19;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-26 10:04
 * @Description:
 */
public class GPURenwu {
  //思路:没有就是把有的挨个往里放，然后看剩下多少，如果剩下的正好能被平分掉做好，不能的话就+1
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      int maxTask = Integer.valueOf(in.nextLine());
      int len = Integer.valueOf(in.nextLine());
      String[] strs = in.nextLine().split(" ");
      int[] nums = new int[len];
      for(int i = 0; i < strs.length; i++){
        nums[i] = Integer.valueOf(strs[i]);
      }
      solve(maxTask, len, nums);
    }
  }

  public static void solve(int maxTask, int len, int[] nums){
    int leftVal = 0;
    for(int i = 0; i < len; i++){
      int val = nums[i];
      if(val < 1 || val > 10000){
        System.out.println("input error");
      }
      leftVal = Math.max(0, leftVal + val - maxTask); //并行算一台机器最终剩下多少
    }
    int result = len;
    if(leftVal % maxTask != 0){
      result += leftVal / maxTask + 1;
    } else {
      result += leftVal / maxTask;
    }
    System.out.print(result);
  }


}
