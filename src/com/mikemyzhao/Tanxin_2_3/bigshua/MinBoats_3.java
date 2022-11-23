package com.mikemyzhao.Tanxin_2_3.bigshua;

import java.util.Arrays;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-15 13:54
 * @Description:// 给定一个正数数组arr，代表若干人的体重
 * // 再给定一个正数limit，表示所有船共同拥有的载重量
 * // 每艘船最多坐两人，且不能超过载重
 * // 想让所有的人同时过河，并且用最好的分配方法让船尽量少
 * // 返回最少的船数
 */
public class MinBoats_3 {
  //解法：把arr按照从小到大的顺序排列，然后从中间找，如果能凑上一堆就上，如果大于船的承重L左移，尽量让r往右移动
  public static int minBoat(int[] arr, int limit){
    if(arr == null || arr.length == 0){
      return 0;
    }
    int N = arr.length;
    Arrays.sort(arr);
    if(arr[N-1] > limit){
      return -1;
    }
    int lessR = -1;
    for(int i = N-1; i>=0; i--){
      if(arr[i] <= limit/2){
        lessR = i;//找到刚好小于或者等于船承重一半的位置
        break;
      }
    }
    if(lessR == -1){
      return N;//没法两个人一船
    }
    //左右节点开始
    int L = lessR;
    int R = lessR + 1;
    int noUsed = 0;
    while(L >= 0){
      int solved = 0;
      while(R < N && arr[L] + arr[R] <= limit){
        //这里是R可以扩充往右划
        R++;
        solved++;//说明R这个点可以搞定
      }
      if(solved == 0){
       noUsed++;//R不能右划，解决不了问题，那么只能让L减少
       L--;
      }else {
        //贪心的点，既然让R往右划了那么多，那么左边的L就可以接到对应多个即L-solved
        L = Math.max(-1,L-solved);
      }
    }
    int all = lessR + 1;
    int used = all - noUsed;
    int moreUnsolved = N - all - used;
    return  used+ ((noUsed + 1) >> 1) + moreUnsolved;
  }
}
