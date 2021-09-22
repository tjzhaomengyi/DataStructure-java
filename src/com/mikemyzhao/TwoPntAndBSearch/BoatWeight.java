package com.mikemyzhao.TwoPntAndBSearch;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-14 10:16
 * @Description:运货问题，给定一个正整数组weight和一个正整数D，其中weights代表一些列货物，weight[i]表示第i个物品的重量。
 * 求货船能在D天内完成所有货物的最低运载能力
 */
public class BoatWeight {
  //如果载重为cap，是否能在D天内完成货运
  boolean canFinish(int[] w,int D,int cap){
    int i=0;
    for(int day=0;day<D;day++){
      int maxCap = cap;
      while((maxCap = maxCap-w[i])>=0){
        i++;
        if(i==w.length){
          return true;
        }
      }
    }
    return false;
  }
  //寻找二分搜索
  int shipWithinDays(int[] weights,int D){
    //最小载重值
    List<Integer> wlst = Arrays.stream(weights).boxed().collect(Collectors.toList());
    int left = Collections.max(wlst);
    int right = left+1;
    while(left<right){
      int mid  = left+(right-left)/2;
      if(canFinish(weights,D,mid)){
        right = mid;
      }else{
        left = mid+1;
      }
    }
    return left;
  }
}
