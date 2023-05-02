package com.huaweiOD.od2023.s40e59;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-21 19:18
 * @Description:
 */
public class ShuzuZuixiaoShuzi {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String[] strs = in.nextLine().split(",");
      int[] nums = new int[strs.length];
      for(int i = 0; i < nums.length; i++){
        nums[i] = Integer.valueOf(strs[i]);
      }
      Arrays.sort(nums);
      System.out.println(makeSmall(nums));
    }
  }

  public static String makeSmall(int[] nums){
    String[] ans = new String[3];
    int len = nums.length < 3 ? nums.length : 3;
    for(int i = 0; i < len; i++){
      ans[i] = String.valueOf(nums[i]);
    }
    Arrays.sort(ans);
    String str = "";
    for(String s : ans){
      str += s;
    }
    return str;
  }
}
