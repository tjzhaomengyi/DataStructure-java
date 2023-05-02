package com.huaweiOD.od2023.s80e99;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-19 15:14
 * @Description:
 */
public class ZucheQidao {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String[] info = in.nextLine().split(" ");
      int max_w = Integer.valueOf(info[0]);
      int n = Integer.valueOf(info[1]);
      int[] nums = new int[n];
      String[] str_nums = in.nextLine().split(" ");
      for(int i = 0; i < n; i++){
        nums[i] = Integer.valueOf(str_nums[i]);
      }
      Arrays.sort(nums);
      int ans = 0;
      int l = 0;
      int r = n - 1;
      while(l < r){
        if(nums[l] + nums[r] <= max_w){
          ans++;
          l++;
          r--;
        } else if(nums[l] + nums[r] > max_w){
          ans++;
          r--;
        }
      }
      //讨论下情况
      if(l == 0){
        ans++;
      } else if(l == r){
        ans++;
      }
      System.out.println(ans);
    }
  }


}
