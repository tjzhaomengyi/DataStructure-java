package com.mikemyzhao.SomeSkills_0.bigshua.tricks;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-06 9:00
 * @Description:在一个数组中找出最长的可整合数组
 * 可整合数组：在拍完序后满足arr[i+1] = arr[i] + 1
 * 技巧：只要满足这样的可整合性，那么必然有 len = max - min + 1
 */
public class LongestIntegratedLength_23 {
  public static int maxLen(int[] arr, int left, int right){
   if(arr == null || arr.length == 0){
     return 0;
   }
   int N = arr.length;
   HashSet<Integer> set = new HashSet<>();
   int ans = 1;
   for(int L = 0; L < N; L++){
     set.clear();
     int min = arr[L];
     int max = arr[L];
     set.add(arr[L]);
     for(int R = L + 1; R < N; R++){
       if(set.contains(arr[R])){
         break;
       }
       set.add(arr[R]);
       min = Math.min(min, arr[R]);
       max = Math.max(max, arr[R]);
       if(max - min == R - L){
         ans = Math.max(ans, R - L + 1);
       }
     }
   }
   return ans;
  }
}
