package com.point2offerspecial.eleven_bin_search;

import java.util.Random;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-13 19:30
 * @Description:
 */
public class PTOS071_PickIndexByWeight {
  //思路：给[1,2,3,4]表示0~1的概率是10%，1~3的概率是20%
  private int[] sums;
  private int total;
  public PTOS071_PickIndexByWeight(int[] w) {
    sums = new int[w.length];
    for(int i = 0; i < w.length; i++){
      total += w[i];
      sums[i] = total;
    }
  }

  public int pickIndex() {
    Random random = new Random();
    int p = random.nextInt(total);//目标就是这个p
    int left = 0;
    int right = sums.length - 1;
    while(left <= right){
      int mid = left +((right - left) >> 1);
      if(sums[mid] > p){
        if(mid == 0 || sums[mid - 1] <= p){
          return mid;
        }
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return -1;
  }
}
