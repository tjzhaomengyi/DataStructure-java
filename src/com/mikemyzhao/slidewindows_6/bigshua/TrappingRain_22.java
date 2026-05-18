package com.mikemyzhao.slidewindows_6.bigshua;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-05 12:11
 * @Description:https://leetcode.com/problems/trapping-rain-water/
 * 傻逼二维接雨水，起始里面有个贪心，就是每次把小的那边和当前的arr[L]|arr[R]比较选择大的，
 * 伪蓄水池问题
 * 起始
 */
public class TrappingRain_22 {
  /**
   * 公式，某个位置能接多少水？
   *  water[i] = min(左边最高，右边最高) - height[i]
   * @param arr
   * @return
   */
  public static int trap(int[] arr){
    /**
     * 将数组分成左右两侧，leftMax 和 rightMax 卡住左右的最大高度， L 和 R 来进行数组遍历直到两者相撞。
     */
    if(arr == null || arr.length < 2){
      return 0;
    }
    int N = arr.length;
    //左右两侧位置不用关注，因为这两边都是洼地，接不到水
    int L = 1;
    int leftMax = arr[0]; //左侧接水最大量
    int R = N - 2;
    int rightMax = arr[N - 1]; //右侧接水最大量
    int water = 0; //在移动中表示这个位置最多可以接到多少水，这个由 leftMax 和 rightMax 中小的那个决定
    while(L <= R){
      // 原则就是谁小结算谁那一边
      if(leftMax <= rightMax){ //理解：表示现在leftMax 和 rightMax 左右两侧的挡板中矮的挡板决定了当前这个柱子“上面”能存多少水！！！
        //接小的,然后左边开始往左边动
        water += Math.max(0,leftMax - arr[L]); //表示i位置这个格子“头顶上”能接多少水
        leftMax = Math.max(leftMax , arr[L++]);
      } else {
        water += Math.max(0,rightMax - arr[R]);
        rightMax = Math.max(rightMax , arr[R--]);
      }
    }
  return water;
  }
}
