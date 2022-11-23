package com.mikemyzhao.DPAndRecursion_5.DP.bigshua;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-16 13:38
 * @Description:LC514,老式拨号电话
 */
public class FreedomTrail_3 {
  public static int findRotateSteps(String r,String k){
    char[] ring = r.toCharArray();
    int N = ring.length;
    HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
    for(int i = 0; i < N; i++){
      if(!map.containsKey(ring[i])){
        map.put(ring[i],new ArrayList<>());
      }
      map.get(ring[i]).add(i);
    }
    char[] str = k.toCharArray();
    int M = str.length;
    int[][] dp = new int[N][M+1];
    for(int i = 0; i < N; i++){
      for(int j = 0; j <= M; j++){
        dp[i][j] = -1;
      }
    }
    return process(0,0,str,map,N,dp);
  }

  public static  int process(int preButton, int index, char[] str, HashMap<Character,ArrayList<Integer>> map,int N,int[][] dp){
    if(dp[preButton][index] != -1){
      return dp[preButton][index];
    }
    int ans = Integer.MAX_VALUE;
    if(index == str.length){
      ans = 0;
    }else{
      char cur = str[index];
      ArrayList<Integer> nextPositions = map.get(cur);
      for(int next : nextPositions){
        int cost = dial(preButton, next, N) + 1 + process(next,index + 1, str, map, N, dp);
        ans = Math.min(ans, cost);
      }
    }
    dp[preButton][index] = ans;
    return ans;
  }

  public static int dial(int i1, int i2, int size){
    return Math.min(Math.abs(i1 - i2),Math.min(i1, i2) + size - Math.max(i1, i2));
  }
}
