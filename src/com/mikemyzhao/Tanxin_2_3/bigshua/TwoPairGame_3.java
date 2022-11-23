package com.mikemyzhao.Tanxin_2_3.bigshua;

import java.util.Arrays;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-15 13:26
 * @Description:// 给定一个数组arr，代表每个人的能力值。再给定一个非负数k。
 * // 如果两个人能力差值正好为k，那么可以凑在一起比赛，一局比赛只有两个人
 * // 返回最多可以同时有多少场比赛
 */
public class TwoPairGame_3 {
  public static int process1(int[] arr,int index, int k){
    //思路先对能力排序，用LR标记左右，然后如果差值小就动R，如果差值大就动L。然后用usedR[L]，判断这个点是否被R走过并拿走过
    //如果usedR=true就让L往下跳
    if(k < 0 || arr == null || arr.length < 2){
      return 0;
    }
    Arrays.sort(arr);
    int ans = 0;
    int N = arr.length;
    int L = 0;
    int R = 0;
    boolean[] usedR = new boolean[N];
    while(L< N && R < N){
      if(usedR[L]){
        L++;
      }else if(L == R){
        R++;
      }else{
        //这个点没用过看R和L点位的差值是否为k
        int d = arr[R]-arr[L];
        if(d == k){
          ans++;
          usedR[R++] = true;
          L++;
        }else if(d < k){
          R++;
        }else if(d > k){
          L++;
        }
      }
    }
    return ans;
  }
}
