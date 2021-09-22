package com.mikemyzhao.TwoPntAndBSearch;

import sun.security.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-14 9:28
 * @Description:长度为N的数组piles代表N堆香蕉，piles[i]代表第i堆香蕉的数量，Koko要在H小时吃完这些香蕉
 * 限制条件：1、Koko吃香蕉的速度为每小时k根，
 * 2、每小时最多吃一堆香蕉，吃不下的话留到下一小时，
 * 3、如果吃完也会等到下一小时吃下一堆
 */
public class EatBananaSpeed {
  //piles要吃的香蕉堆数，speed吃的速度,H指定吃完的时间
  boolean canFinish(int[] piles, int speed, int H) {
    int time = 0;
    for (int n : piles) {
      time = time + timeOf(n, speed);
    }
    return time <= H;
  }

  //以speed速度吃n个香蕉需要多久,给的条件就保证这个速度要在两个小时内吃完
  int timeOf(int n, int speed) {
    return (n / speed) + ((n % speed > 0) ? 1 : 0);
  }

  int minEatingSpeed(int[] piles,int H){
    List<Integer> pileslst = Arrays.stream(piles).boxed().collect(Collectors.toList());
    int max = Collections.max(pileslst);
    int left =1,right=max+1;
    int mid = left+(right-left)/2;
    if(canFinish(piles,mid,H)){
      right = mid;//在mid中吃完了，还可以再慢一些
    }else{
      left=mid+1;//在mid没吃完，加速吃
    }
    return left;//返回慢的，就返回左边
  }

}
