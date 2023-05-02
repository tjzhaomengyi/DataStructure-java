package com.huaweiOD.nowcoder;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-13 19:15
 * @Description:
 */
public class PrimeNumberCouple {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    // 注意 hasNext 和 hasNextLine 的区别
    while (in.hasNextInt()) { // 注意 while 处理多个 case
      int len = in.nextInt();
      int[] nums = new int[len];
      for(int i = 0; i < len; i++){
        nums[i] = in.nextInt();
      }
      boolean[] visited = new boolean[len];
    }
  }

  public static void bfs(int[] nums, int index, boolean[] visited){

  }



  public static boolean isPrime(int num){
    for(int i = 2; i * i <= num; i++){
      if(num % i == 0){
        return false;
      }
    }
    return true;
  }

}
