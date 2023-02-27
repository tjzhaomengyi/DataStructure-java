package com.mikemyzhao.TrackInTime.recursion;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-02 12:16
 * @Description:给定一个数组判定是不是汉诺塔的调整状态，如果是的话，是最优解的第几步，不是的话返回-1
 */
public class HanoiStep_18 {
  //汉诺塔有三个柱子，如果调整的话，首先要把0~n-1的盘子放在中间，然后分析n号盘子在左侧还是在右侧：
  //如果在左侧那么就要移动一位，如果在右侧，把0~n-1从中间挪到右侧就行
  public static int kth(int[] arr){
    int N = arr.length;
    //返回：arr中的这些状态是0到N-1个盘子最优的挪动步骤有多少步
    return step(arr, N - 1, 1, 3, 2);
  }

  //当前的盘子arr[0~index],index+1层塔，在哪？from，去哪？to，另一个位置other
  //这index+1层问题是最优解的第几步。返回：arr中，0到index个盘子的这些状态是最优的挪动步骤有多少步
  private static int step(int[] arr, int index, int from, int to, int other) {
    if(index == -1){
      return 0;
    }
    //如果arr[index]在other上是错的，【最底下的盘子肯定不在中间位置~！】
    if(arr[index] == other){
      return -1;
    }
    if(arr[index] == from){
      //还在from位置,说明上面的index-1开始也没有动，如果0~index都动了的话那么肯定0~index-1肯定在中间了
      return step(arr, index - 1, from, other, to);
    } else {
      //todo:因为index已经在右边了，说明上面0~index-1已经走完了从左边往中间移动的过程，不用走了
      int p1 = 1;//把index自己从左到右这一步算上
      //(2)以index-1为底的index层从左边到中间走完了需要多少步
      int p2 = 1 << (index) - 1;//todo：公式如果n层盘子从一柱全都挪动到了另一个柱一共移动了2^n-1次
      int p3 = step(arr, index - 1, other, to, from);//从中间的other挪到to
      if(p3 == -1){
        return -1;
      }
      return p1 + p2 + p3;
    }
  }
}
