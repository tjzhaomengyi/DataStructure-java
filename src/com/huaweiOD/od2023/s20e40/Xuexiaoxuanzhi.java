package com.huaweiOD.od2023.s20e40;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-24 18:06
 * @Description:
 */
public class Xuexiaoxuanzhi {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNext()) {
      int n = in.nextInt();
      int[] nums = new int[n];
      for(int i = 0; i < n; i++){
        nums[i] = in.nextInt();
      }
      Arrays.sort(nums);
      if(n % 2 == 1){
        System.out.println(nums[n / 2]);
      } else {
        System.out.println(nums[(n - 1) / 2]);
      }
    }
  }
}
