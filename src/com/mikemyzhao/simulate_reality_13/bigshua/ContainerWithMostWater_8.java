package com.mikemyzhao.simulate_reality_13.bigshua;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-19 18:00
 * @Description: https://leetcode.com/problems/container-with-most-water/
 */
public class ContainerWithMostWater_8 {
  //这个题给定n的长度，我们可以猜出要的解法就是O(N),又因为我们希望每次得到最好的可能性，不断改变数组中最低值的取值
  public static int maxArea2(int[] h){
    int max = 0;
    int l = 0;
    int r = h.length-1;
    while(l < r) {
      max = Math.max(max,Math.min(h[l],h[r]) * (r - l));
      if(h[l] > h[r]){
        r--;
      }else{
        l++;
      }
    }
    return max;
  }
}
