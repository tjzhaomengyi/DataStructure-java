package com.book.point2offer;

import java.util.PriorityQueue;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-08 15:24
 * @Description:哎，一个大顶堆，一个小顶堆就能保证流数据有序
 */
public class MedianFinder_41 {
  /** initialize your data structure here. */
  PriorityQueue<Integer> left,right;//左侧是大顶堆，右侧是小顶堆，注意：大顶对永远比小顶堆少一个
  public MedianFinder_41() {
    left = new PriorityQueue<>((x,y)->(y-x));
    right = new PriorityQueue<>();
  }

  public void addNum(int num) {
    if(left.size()!=right.size()) {
      right.offer(num);
      left.offer(right.poll());
    }
    else{
      //往左边放新的，整理一下
      left.offer(num);
      right.offer(left.poll());
    }
  }

  public double findMedian() {
    if(left.size()!=right.size()){
      return right.peek();
    }else{
      return (left.peek()+right.peek())/2.0;
    }
  }
}
