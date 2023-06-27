package com.ZuoshenGaopinMianshi.eight_DP;

/**
 * @Author: zhaomengyi
 * @Date: 2023-06-07 15:29
 * @Description:
 */
public class Two_Wanpai {
  //只能拿两头的其中一张
  public static int xian(int[] arr, int L, int R){
    if(L == R){
      return arr[L];
    }
    if(L == R - 1){
      return Math.max(arr[L], arr[R]);
    }

    //L..R上，不止两张牌
    //p1:拿走L位置的牌
    int p1 = arr[L] + hou(arr, L + 1, R);
    //p2:拿走R位置的牌
    int p2 = arr[R] + hou(arr, L, R - 1);
    return Math.max(p1, p2);
  }

  //思路:注意后手是拿最差的~1
  public static int hou(int[] arr, int L, int R){
    if(L == R){
      return 0;
    }
    if(L == R - 1){
      return Math.min(arr[L], arr[R]);//先手拿走大的，后手肯定拿小的
    }
    //L...R不止两张牌,肯定要拿小的
    //可能性1:对手先手，拿走L位置的牌，可以在L+1...R上先手
    int p1 = xian(arr, L + 1, R);
    int p2 = xian(arr, L, R - 1);
    return Math.min(p1, p2);//肯定是让最差的可能性落在后手手上
  }

  //从递归到动态规划，xian()函数加一个dpxian[][]缓存，hou()函数加一个dphou[][]缓存
}
