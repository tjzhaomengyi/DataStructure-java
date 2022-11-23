package com.mikemyzhao.SomeSkills_0.bigshua.tricks;

import java.util.Arrays;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-06 20:16
 * @Description:
 */
public class KthMinPair_24 {
  //O(N*logN)
  public static int[] kthMinPair(int[] arr, int K){
    int N = arr.length;
    if(K > N * N) {//数学结论：总共就他妈N^2个
      return null;
    }
    Arrays.sort(arr);
    //数学结论:找到第K小pair的初始数值
    int firstNum = arr[(K - 1) / N]; //数学结论：pair的头部有多少个算一遍
    int lessFirstNumSize = 0;//数出比firstNumber小的一共多少个
    int firstNumSize = 0;//输出等于firstNum的数有几个
    //技巧：求小于firstNum的个数
    for(int i = 0; i < N && arr[i] <= firstNum; i++){
      if(arr[i] < firstNum){
        lessFirstNumSize++;
      } else {
        firstNumSize++;
      }
    }
    int rest = K - (lessFirstNumSize * N);
    return new int[] { firstNum, arr[(rest - 1) / firstNumSize]};//尾部有多少个再算一遍
  }
}
