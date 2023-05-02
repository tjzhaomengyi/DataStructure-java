package com.huaweiOD.od2023.s40e59;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-21 16:38
 * @Description:
 */
public class TaiyyangnengZuidamianji {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String[] str = in.nextLine().split(",");
      int len = str.length;
      int[] nums = new int[len];
      for(int i = 0; i < len; i++){
        nums[i] = Integer.valueOf(str[i]);
      }
      System.out.println(cal(nums));
    }
  }

  public static int cal(int[] nums){
    int max = 0;
    for(int i = 0; i < nums.length; i++){
      for(int j = i + 1; j < nums.length; j++){
        int area = (j - i) * Math.min(nums[i], nums[j]);
        max = Math.max(area, max);
      }
    }
    return max;
  }


}
