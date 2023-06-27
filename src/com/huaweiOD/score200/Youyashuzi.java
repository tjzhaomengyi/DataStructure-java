package com.huaweiOD.score200;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-05-22 15:48
 * @Description:
 */
public class Youyashuzi {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextInt()) {
      int n = in.nextInt();
      int k = in.nextInt();
      int[] arr = new int[n];
      for(int i = 0; i < n; i++){
        arr[i] = in.nextInt();
      }
      System.out.println(solveMethod(arr, n, k));
    }
  }


  public static int solveMethod(int[] arr, int n, int k){
    int ans = 0;

    for(int i = 0; i < n; i++){
      Map<Integer, Integer> count = new HashMap<>();
      for(int j = i; j < n; j++){
        int key = arr[j];
        count.put(key, count.getOrDefault(key, 0) + 1);
        if(count.get(key) >= k){
          ans += n - j; //当前长度满足了，后面剩下的也都满足，哭
          break;
        }
      }
    }
    return ans;
  }

}
