package com.mikemyzhao.SomeSkills_0.TwoPntAndBSearch.twopnts.bigshua;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-05 12:11
 * @Description:https://leetcode.com/problems/trapping-rain-water/
 * 傻逼二维接雨水，起始里面有个贪心，就是每次把小的那边和当前的arr[L]|arr[R]比较选择大的，
 * 伪蓄水池问题
 * 起始
 */
public class TrappingRain_22 {
  public static int trap(int[] arr){
    if(arr == null || arr.length < 2){
      return 0;
    }
    int N = arr.length;
    //左右两侧位置不用关注，因为这两边都是洼地，接不到水
    int L = 1;
    int leftMax = arr[0];
    int R = N - 2;
    int rightMax = arr[N - 1];
    int water = 0;
    while(L <= R){
      if(leftMax <= rightMax){
        //接小的,然后左边开始往左边动
        water += Math.max(0,leftMax - arr[L]);
        leftMax = Math.max(leftMax , arr[L++]);
      } else {
        water += Math.max(0,rightMax - arr[R]);
        rightMax = Math.max(rightMax , arr[R--]);
      }
    }
  return water;
  }
}
