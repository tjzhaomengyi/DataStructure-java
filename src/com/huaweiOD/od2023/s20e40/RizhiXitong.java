package com.huaweiOD.od2023.s20e40;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-25 14:10
 * @Description:
 */
public class RizhiXitong {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String[] arr = in.nextLine().split(" ");
      int[] nums = new int[arr.length];
      for(int i = 0; i < arr.length; i++){
        nums[i] = Integer.valueOf(arr[i]);
      }
      int scoreResult = 0;
      for(int i = 0; i < nums.length; i++){
        scoreResult = Math.max(scoreResult, getScore(nums, i));
      }
    }
  }

  public static int getScore(int[] nums, int index){
    int myscore = 0;
    int sum = 0;
    for(int i = 0; i <= index; i++){
      if(sum + nums[i] < 100){
        myscore += nums[i];
        myscore -= nums[i] * (index - i);
        sum += nums[i];
      } else{ //sum+num[i]>0,说明是到j位置出的问题，所以要把j前面的都减掉
        int dis = 100;
        for(int j = 0; j < i; j++){
          dis -= nums[j];
          nums[j] = 0;
        }
        nums[i] -= dis; //nums[i] 还剩多少
        myscore += dis; //scoer只能得到当前分数
        myscore -= dis * (index - i);
        return myscore;
      }
    }
    return 0;
  }
}
