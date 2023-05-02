package com.huaweiOD.od2023.s20e40;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-24 18:49
 * @Description:
 */
public class ZhaoChewei {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String[] str = in.nextLine().split(",");
      int[] nums = new int[str.length];
      for(int i = 0; i < str.length; i++){
        nums[i] = Integer.valueOf(str[i]);
      }
      System.out.println(getMaxDis(nums));
    }
  }

  //就是找最长的0
  public static int getMaxDis(int[] nums){
    int max = 0;
    for(int i = 0; i < nums.length;i++){
      if(nums[i] == 1){
        int[] tmp = getDistance(nums, i);
        max = Math.max(max, tmp[0]);
        i = tmp[1];//技巧:i的最后一个0的位置，下一个再++，千万不能到下一个1因为结尾有可能就没1了，进入死循环
      }
    }
    return max % 2 == 0 ? max / 2 : (max + 2 - 1)/ 2;
  }


  public static int[] getDistance(int[] nums, int index){
    int cnt = 0;
    int next = index;
    if(index + 1 < nums.length){
      for(int i = index + 1; i < nums.length; i++){
        if(nums[i] == 0){
          cnt++;
        } else {
          next = i - 1;
          break;
        }
      }
    }
    return new int[]{cnt, next};
  }

}
