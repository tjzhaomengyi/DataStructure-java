package com.book.point2offerspecial.fourteen_dp.seqence_dp;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-16 19:07
 * @Description:
 */
public class PTOS093_LongestFibo {
  public int lenLongestFibSubseq(int[] arr) {
    //技巧：使用一个HashMap存储下标
    Map<Integer, Integer> map = new HashMap<>();
    for(int i = 0; i < arr.length; i++){
      map.put(arr[i], i);//反查表，因为arr是严格递增的
    }
    int len = arr.length;
    //技巧：如果要形成斐波那契数列，必须满足A[j] + A[K] = A[i]，
    // dp[i][j]表示以[i][j]为倒数第二和第一的斐波那契数列的长度
    int[][] dp = new int[len][len];//第一位是j表示上一个结束为止，第二位是i表示当前的结束位，去找k
    int ans = 2;//如果是一个斐波那契数列肯定2个以上的数字组成,技巧：这里定义了后面就不需要填表了
    //表格法
    for(int i = 0; i < len; i++){
      for(int j = 0; j < i; j++){
        int k = map.getOrDefault(arr[i] - arr[j], -1);//找k下标
        dp[i][j] = (k >= 0 && k < j) ? dp[j][k] + 1 : 2; //2表示没在里面找到所以当前这个i和j组成的斐波那契只有长度2
        ans = Math.max(ans, dp[i][j]);
      }
    }
    return ans > 2 ? ans : 0;
  }
}
