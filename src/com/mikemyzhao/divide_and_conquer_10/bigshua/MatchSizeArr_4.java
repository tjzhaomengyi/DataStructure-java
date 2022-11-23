package com.mikemyzhao.divide_and_conquer_10.bigshua;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-17 8:49
 * @Description:给定数组长度size，求该数组满足[i]+[j]!=2k,i<k<j
 */
public class MatchSizeArr_4 {
  public static int[] makeNo(int size){
    if(size == 1){
      return new int[]{1};
    }

    int halfSize = (size + 1) / 2;
    int[] base = makeNo(halfSize);
    int[] ans = new int[size];
    int index = 0;
    for(; index < halfSize; index++){
      ans[index] = base[index] * 2 - 1;
    }
    for(int i = 0; index < size; index++, i++){
      ans[index] = base[i] * 2;
    }
    return ans;
  }
}
