package com.daybyday.bit;

import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-23 09:18
 * @Description:前置题目：LC0089格雷编码
 * 给你两个整数 n 和 start。你的任务是返回任意 (0,1,2,,...,2^n-1) 的排列 p，并且满足：
 *
 * p[0] = start
 * p[i] 和 p[i+1] 的二进制表示形式只有一位不同
 * p[0] 和 p[2^n -1] 的二进制表示形式也只有一位不同
 */
public class LC1238_CirclemPermutation {
  class Solution {
    public List<Integer> circularPermutation(int n, int start) {
      int max = 1 << n;
      int[] ans = new int[max];
      ans[0] = 0;
      for(int i = 1; i < max; i++){
        ans[i] =i ^ (i >> 1);
      }
      int split = 0;
      for(int i = 0; i < ans.length; i++){
        if(ans[i] == start){
          split = i;
        }
      }

      List<Integer> result = new ArrayList<>();
      for(int i = split; i < max; i++){
        result.add(ans[i]);
      }
      for(int i = 0; i < split; i++){
        result.add(ans[i]);
      }

      return result;
    }
  }
}
