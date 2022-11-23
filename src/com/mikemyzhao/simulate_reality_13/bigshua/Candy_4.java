package com.mikemyzhao.simulate_reality_13;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-16 17:48
 * @Description:https://leetcode-cn.com/problems/candy/
 * LC135
 */
public class Candy {
  public static int candy1(int[] ratings){
    if(ratings == null || ratings.length == 0){
      return 0;
    }
    int N = ratings.length;
    int[] left = new int[N];
    int[] right = new int[N];
    for(int i = 1; i < N; i++){
      if(ratings[i-1] < ratings[i]){
        left[i] = left[i-1] + 1;
      }
    }
    for(int i = N-2; i >= 0; i--){
      if(ratings[i] > ratings[i+1]){
        right[i] = right[i+1] + 1;
      }
    }
    int ans = 0;
    for(int i = 0; i < N; i++){
      ans += Math.max(left[i],right[i]);
    }
    return ans + N;
  }


  //如果相等左右要一样
  public static int candy2(int[] ratings){
    if(ratings == null || ratings.length == 0){
      return 0;
    }
    int N = ratings.length;
    int[] left = new int[N];
    int[] right = new int[N];
    for(int i = 1; i < N; i++){
      if(ratings[i-1] < ratings[i]){
        left[i] = left[i-1] + 1;
      }else if(ratings[i-1] == ratings[i]){
        left[i] = left[i-1];
      }
    }
    for(int i = N-2; i >= 0; i--){
      if(ratings[i] > ratings[i+1]){
        right[i] = right[i+1] + 1;
      }else if(ratings[i+1] == ratings[i]){
        right[i] = right[i+1];
      }
    }
    int ans = 0;
    for(int i = 0; i < N; i++){
      ans += Math.max(left[i],right[i]);
    }
    return ans + N;
  }
}
