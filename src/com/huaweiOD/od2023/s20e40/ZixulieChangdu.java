package com.huaweiOD.od2023.s20e40;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-24 17:35
 * @Description:
 */
public class ZixulieChangdu {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String[] str = in.nextLine().split(",");
      int target = Integer.valueOf(in.nextLine());
      int[] nums = new int[str.length];
      for(int i = 0; i < str.length; i++){
        nums[i] = Integer.valueOf(str[i]);
      }

      int max = Integer.MIN_VALUE;
      int start = -1;
//      ArrayList<Integer> path = new ArrayList<>();
      for(int i = 0; i < nums.length; i++){
        int sum = 0;
        for(int j = i; j < nums.length; j++){
          sum += nums[j];
          if(sum == target && j - i + 1 > max){
            start = i;
            max = j - i + 1;
          } else if(sum > target){
            break;
          }
        }
      }
      if(start == -1){
        System.out.println(-1);
      } else {
       System.out.println(max);
      }
    }
  }


}
