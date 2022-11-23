package com.mikemyzhao.Tanxin_2_3;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-14 13:40
 * @Description:贪心处理时间管理，这种题目比较常见，一种是会议场配置，一种是参加活动
 * 题目形态给定一个闭区间，算出这些区间中最多有几个互不相交的区间，这样不相交了，就能找到最多参加活动，或者布置会议
 * intvs=[[1,3],[2,4][3,6]],这个区间最多两个不相交
 *  套路！！！！：如何不选差的方案，直接出最优方案
 *  1、从区间集合intvs中选择一个区间x，这个x是当前所有区间中结束最早的，end最小
 *  2、把所有与x相交的区间从intvs集合中删除
 *  3、重复1、2知道选出那些x，就是最大不相交子集
 */
public class TimeManager {
  /**基础架子**/
  int intervalSchedule(int[][] intvs){
    if(intvs.length==0) return 0;
    //按照end的升序排列
    Arrays.sort(intvs,new Comparator<int[]>() {
      @Override
      public int compare(int[] o1, int[] o2) {
        return o1[1]-o2[1];
      }
    });
    //至少有一个区间不相交
    int count=1;
    //排序后第一个区间就是x
    int x_end = intvs[0][1];
    for(int[] interval:intvs){
      int start = interval[0];
      if(start>=x_end){
        //找到下一个选择区间
        count++;
        x_end = interval[1];
      }
    }
    return count;
  }

  /**1、例1问移除几个区间，使得intvs没有重叠区间
   * intvs = [[1,2],[2,3][3,4][1,3]]移除一次
   *
   * **/
  int eraseIntervlas(int[][] intvs){
    int n = intvs.length;
    return n-intervalSchedule(intvs);//求最大再减
  }

  /***
   * 2、用最少箭头射爆气球
   * 假设在二维平面上有很多气球，这些圆形气球投影到x上是一个区间，沿着x可以垂直向上射箭，最少射几只箭才能全部射爆
   * [[10,16],[2,8],[1,6],[7,12]],两次即可
   * **/
  int findMinArrow(int[][] intvs){
    if(intvs.length==0) return 0;
    Arrays.sort(intvs, new Comparator<int[]>() {
      @Override
      public int compare(int[] o1, int[] o2) {
        return o1[1]-o2[1];
      }
    });
    int count=1;
    int end = intvs[0][1];//最小的
    for(int[] interval:intvs){
      int start = interval[0];
      if(start>end){
        count++;
        end=interval[1];
      }
    }
    return count;
  }

}
