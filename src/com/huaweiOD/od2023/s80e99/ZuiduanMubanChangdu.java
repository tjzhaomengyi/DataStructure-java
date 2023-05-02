package com.huaweiOD.od2023.s80e99;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-19 12:31
 * @Description:
 */
public class ZuiduanMubanChangdu {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String[] info = in.nextLine().split(" ");
      int n = Integer.valueOf(info[0]);
      int mutou = Integer.valueOf(info[1]);
      String[] line  = in.nextLine().split(" ");
      int[] nums = new int[n];
      for(int i = 0; i < n; i++){
        nums[i] = Integer.valueOf(line[i]);
      }
      Arrays.sort(nums);
      int maxLen = 0;
      for(int i = 1; i <= mutou; i++){
        nums[0] = nums[0] + 1;
        Arrays.sort(nums);
        maxLen = Math.max(maxLen, nums[0]);
      }
      System.out.println(maxLen);
    }
  }


  }



