package com.huaweiOD.score200;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-05-30 11:59
 * @Description: 垃圾
 */
public class JihePIngjunZuidaZishuzu {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextInt()) {
      int n = in.nextInt();
      int l = in.nextInt();
      double[] nums = new double[n];
      for(int i = 0; i < n; i++){
        nums[i] = in.nextDouble();
      }

      int lo = 0;
      int size = 0;
      double maxMean = Double.NEGATIVE_INFINITY;
      for(int i = 0; i <= n - l; i++){
        for(int j = i + l; j <= n; j++){
          double meanVal = calcMeand(nums, i, j);
          if(meanVal - maxMean >= 1e-5){
            maxMean = meanVal;
            lo = i;
            size = j - i;
          } else if(Math.abs(meanVal - maxMean) <= 1e-10){
            if(j - i < size){
              lo = i;
              size = j - i;
            }
          }
        }
      }
      System.out.println(lo + " " + size);
    }
  }

  public static double calcMeand(double[] numlist, int start, int end){
    double val = 1;
    for(int i = start; i < end; i++){
      val *= numlist[i];
    }
    return Math.pow(val, 1.0 / (end - start));
  }


}
