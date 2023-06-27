package com.huaweiOD.score200;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-05-18 15:47
 * @Description:
 */
public class TonjiChayizhiDayuXiangsizhiGeshu {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNext()) {
      int len = in.nextInt();
      int[] nums = new int[len];
      for(int i = 0; i < len; i++){
        nums[i] = in.nextInt();
      }
      Arrays.sort(nums);
      int ans = getCount(nums);
      System.out.println(ans);
    }
  }

  public static int getCount(int[] nums){
    int len = nums.length;
    int cnt = 0;
    for(int i = 0; i < len - 1; i++){
      for(int j = i + 1; j < len; j++){
        int a = nums[i];
        int b = nums[j];
        if(isChayiDayuXiangsi(a, b)){
          cnt++;
        }
      }
    }
    return cnt;
  }

  public static boolean isChayiDayuXiangsi(int a, int b){
    String bin_a = Integer.toBinaryString(a);
    String bin_b = Integer.toBinaryString(b);
    if(bin_b.length() > bin_a.length()){
      StringBuilder sb = new StringBuilder();
      for(int i = 0; i < bin_b.length() - bin_a.length(); i++){
        sb.append('0');
      }
      bin_a = sb.toString() + bin_a;
    }
    StringBuilder str_chayi = new StringBuilder();
    StringBuilder str_xiangsi = new StringBuilder();
      for(int i = 0; i < bin_a.length(); i++){
        char bit_a = bin_a.charAt(i);
        char bit_b = bin_b.charAt(i);
        if(bit_a != bit_b){
          str_chayi.append('1');
        } else {
          str_chayi.append('0');
        }
        if(bit_a == '1' && bit_b == '1'){
          str_xiangsi.append('1');
        } else {
          str_xiangsi.append('0');
        }
      }

    return Integer.parseInt(str_chayi.toString(), 2) > Integer.parseInt(str_xiangsi.toString(), 2);
  }
}
