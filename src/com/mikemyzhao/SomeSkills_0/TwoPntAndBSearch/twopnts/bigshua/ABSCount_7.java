package com.mikemyzhao.TwoPntAndBSearch.twopnts.bigshua;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-19 14:53
 * @Description:给定数组找出多少不同的平方或者绝对值的种类
 */
public class ABSCount_7 {
  public static int diff(int[] arr){
    int N = arr.length;
    int L = 0;
    int R = N - 1;
    int leftAbs = 0;
    int rightAbs = 0;
    int count = 0;
    while(L <= R){
      count++;
      leftAbs = Math.abs(arr[L]);
      rightAbs = Math.abs(arr[R]);
      if(leftAbs < rightAbs){
        while(R >= 0 && Math.abs(arr[R]) == rightAbs){
          R--;
        }
      }else if(leftAbs > rightAbs){
        while(L < N && Math.abs(arr[L]) == leftAbs){
          L++;
        }
      } else {
        while(L < N && Math.abs(arr[L]) == leftAbs){
          L++;
        }
        while(R >= 0 && Math.abs(arr[R]) == rightAbs){
          R--;
        }
      }
    }
    return count;
  }
}
