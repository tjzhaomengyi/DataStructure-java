package com.huaweiOD.od2023.s100e119;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-17 20:21
 * @Description:https://dream.blog.csdn.net/article/details/129191377
 */
public class GonghaoBugouyong {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str = in.nextLine();
      String[] nums = str.split(" ");
      int a = Integer.parseInt(nums[0]); //一共多少人
      int b= Integer.parseInt(nums[1]);//一共能用几位字母
      System.out.println(process(a, b));
    }
  }



  public static int process(int x, int y){
    int j = 1;
    while(x > Math.pow(26, y) * Math.pow(10, j)){
      j++;
    }
    return j;
  }


}
