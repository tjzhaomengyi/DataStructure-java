package com.huaweiOD.score200;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-05-24 10:06
 * @Description:这道题挺他妈的傻逼
 */
public class ZuiduoDengheBuxiangjiaoLianxuZixulie {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextInt()) {
      int n  = in.nextInt();
      int[] arr = new int[n];
      for(int i = 0; i < n; i++){
        arr[i] = in.nextInt();
      }
      int maxValues = 0;
      int[] dp = new int[n];
      System.arraycopy(arr, 0, dp, 0, n);
      Map<Integer, Integer> sumCountMap = new HashMap<>();
      Map<Integer, HashSet<Integer>> sumPosMap = new HashMap<>();

      for(int i = 0; i < n; i++){
        for(int j = 0; j + i < n; j++){ //增量
          if(i > 0){
            dp[j] = dp[j] + arr[i + j];
          }
          int sum = dp[j];
          if(!sumCountMap.containsKey(sum)){
            sumCountMap.put(sum, 0);
            sumPosMap.put(sum, new HashSet<>());
          }
          boolean exists = false;
          HashSet<Integer> possSet = sumPosMap.get(sum);
          for(int k = j; k <= j + i; k++){
            if(possSet.contains(k)){
              exists = true;
              break;
            }
          }
          if(!exists){
            int newSum = sumCountMap.get(sum) + 1;
            sumCountMap.put(sum, newSum);
            maxValues = Math.max(maxValues, newSum);
            for(int k = j; k <= j + i; k++){
              possSet.add(k);
            }
            sumPosMap.put(sum, possSet);
          }
        }
      }
      System.out.println(maxValues);


    }
  }

}
