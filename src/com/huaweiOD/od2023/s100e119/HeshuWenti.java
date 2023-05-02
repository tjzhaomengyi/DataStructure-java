package com.huaweiOD.od2023.s100e119;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-17 19:53
 * @Description:  https://dream.blog.csdn.net/article/details/129191321
 */
public class HeshuWenti {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNext()) {
      int len = in.nextInt();
      int[] nums = new int[len];
      for(int i = 0; i < len; i++){
        nums[i] = in.nextInt();
      }
      System.out.println(solveMethods(nums));
    }
  }


  public static String solveMethods(int[] nums){
    Arrays.sort(nums);
    String result = "0";
    for(int i = nums.length - 1; i >= 0; i--){
      for(int j = 0; j < i; j++){
        for(int k = 0; k < i; k++){
          int a = nums[i];
          int b = nums[j];
          int c = nums[k];
          if(b + 2 * c == a){
            result = a + " " + b + " " + c;
            break;
          }
        }
      }
    }
    return result;
  }


}
