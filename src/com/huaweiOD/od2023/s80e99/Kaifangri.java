package com.huaweiOD.od2023.s80e99;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-20 11:29
 * @Description:
 */
public class Kaifangri {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNext()) {
      int sum = in.nextInt();
      int nums = in.nextInt();
      int[] myBalls = new int[nums];
      for(int i = 0; i < nums; i++){
        myBalls[i] = in.nextInt();
      }
      int total = getTotalNum(myBalls);
      if(sum >= total){
        //不能取
        System.out.println("[]");
        return;
      }
      int[] tmps = new int[myBalls.length];
      int[] subs = new int[myBalls.length];
      for(int i = sum; true; i--){ //注意写法
        for(int k = 0; k < myBalls.length; k++){
          if(i > myBalls[k]){//贪心，要求的总数量比当前的球数多，就保留当前球
            subs[k] = 0;
            tmps[k] = myBalls[k];
          } else {
            subs[k] = myBalls[k] - i;
            tmps[k] = i;
          }
        }
        if(sum >= getTotalNum(tmps)){
          System.out.println(Arrays.toString(subs).replaceAll(" ",""));//直接打印数组的方式
          break;
        }
      }
    }
  }

  public static int getTotalNum(int[] nums){
    int total = 0;
    for(int b : nums){
      total += b;
    }
    return total;
  }



}
