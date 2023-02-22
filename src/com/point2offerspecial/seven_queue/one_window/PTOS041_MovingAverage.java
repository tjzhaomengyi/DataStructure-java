package com.point2offerspecial.seven_queue.one_window;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-07 17:28
 * @Description:
 */
public class PTOS041_MovingAverage {
  private Queue<Integer> queue;
  private int capacity;
  private int sum;
  public PTOS041_MovingAverage(int size){
    this.queue = new LinkedList<>();
    this.capacity =  size;
  }

  public double next(int val){
    queue.offer(val);
    sum += val;
    if(queue.size() > capacity){
      sum -= queue.poll();
    }
    return (double)sum / queue.size();
  }
}
