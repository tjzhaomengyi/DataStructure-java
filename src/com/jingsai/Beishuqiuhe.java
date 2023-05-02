package com.jingsai;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-23 11:05
 * @Description:
 */
public class Beishuqiuhe {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextInt()) {
      int str = in.nextInt();
    }
  }

  public static int sumOfMultiples(int n) {
    int sum = 0;
    for(int i = 1; i <= n; i++){
      if(i % 3 == 0 || i % 5 == 0 || i % 7 == 0){
        sum += i;
      }
    }
    return sum;
  }
}
