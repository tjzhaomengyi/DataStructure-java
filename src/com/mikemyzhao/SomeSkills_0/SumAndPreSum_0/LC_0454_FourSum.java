package com.mikemyzhao.SomeSkills_0.SumAndPreSum_0;

import com.MCAAlgorithm.base.class33.Hash;

import java.util.HashMap;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-19 9:54
 * @Description:
 */
public class LC_0454_FourSum {
  public static int fourSumCount(int[] A, int[] B, int[] C, int[] D){
    HashMap<Integer, Integer> map = new HashMap<>();
    int sum = 0;
    for(int i = 0; i < A.length; i++){
      for(int j = 0; j < B.length; j++){
        sum = A[i] + B[j];
        if(map.containsKey(sum)){
          map.put(sum, map.get(sum) + 1);
        } else{
          map.put(sum, 1);
        }
      }
    }
    int ans = 0;
    for(int i = 0; i < C.length; i++){
      for(int j = 0; j < D.length; j++){
        sum = C[i] + D[j];
        if(map.containsKey(-sum)){
          ans += map.get(-sum);
        }
      }
    }
    return ans;
  }
}
