package com.point2offer;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-08 14:51
 * @Description:最小的K个数，使用小根堆
 */
public class GetLastNumbers_40 {
  public int[] getLeastNumbers(int[] arr, int k) {
    if(arr.length==0||k==0) return new int[0];
    Queue<Integer> tmp = new PriorityQueue<>((v1, v2) -> v2 - v1);//默认是小根堆，变成大根堆
    for(int num:arr){
      if(tmp.size()<k){
        tmp.offer(num);
      }else{
        if(num<tmp.peek()){
          tmp.poll();
          tmp.offer(num);
        }
      }
    }
    int[] res = new int[k];
    int i=0;
    for(int num:tmp){
      res[i++]=num;
    }
    return res;
  }
}
