package com.huaweiOD.od2023.s40e59;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-21 14:39
 * @Description:
 */
public class PaichuTuandui {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      int len = Integer.valueOf(in.nextLine());
      String[] strs  = in.nextLine().split(" ");
      int limit = Integer.valueOf(in.nextLine());
      System.out.println(solve(len, strs, limit));
    }
  }

  public static int solve(int len, String[] strs, int limit){
    int max = 0;
    int[] arr= new int[len];
    for(int i = 0; i < len; i++){
      int can = Integer.parseInt(strs[i]);
      if(can >= limit){
        max++;
      } else {
        arr[i] = can;
      }
    }
    Arrays.sort(arr);
    int start = 0;
    int end = arr.length - 1;
    while(start < end){
      if(arr[start] + arr[end] < limit){
        start++;
        continue;
      } else {
        max++;
        start++;
        end--;
      }
    }
    return max;
  }


}
