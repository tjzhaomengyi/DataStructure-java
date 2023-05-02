package com.huaweiOD.od2023.s120e138;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-16 14:34
 * @Description:
 */
public class LengthOfIcreateNums {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str = in.nextLine().toLowerCase();
      String nums = str.replaceAll("[a-z]", "");
      System.out.println(longest(nums));
    }
  }

  public static int longest(String nums){
    int[] arr = new int[nums.length()];
    for(int i = 0; i < nums.length(); i++){
      arr[i] = Character.getNumericValue(nums.charAt(i));
    }
    int[] dp = new int[arr.length];
    Arrays.fill(dp, 1);
    for(int i = 0; i < arr.length; i++){
      for(int j = 0; j <= i; j++){
        if(arr[i] > arr[j]){
          dp[i] = Math.max(dp[i] , dp[j] + 1);
        }
      }
    }
    return dp[arr.length - 1];
  }


}
