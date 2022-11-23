package com.mikemyzhao.SortList_ALL.QueueAndHeap.bigshua;

import java.util.PriorityQueue;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-18 18:07
 * @Description:
 */
public class LC_0295_FindMedianFromDataStream {

  //数学结论:使用一个大根堆和一个小根堆实现这个结构
  class MedianFinder {
    private PriorityQueue<Integer> maxh;
    private PriorityQueue<Integer> minh;

    public MedianFinder(){
      maxh = new PriorityQueue<>((a,b) -> b-a);
      minh = new PriorityQueue<>();
    }

    //数学结论:记住就完了，当前数小于大顶进入大根堆，当前数大于大顶进小根堆
    // 如何调整:如果两边相差两个，把多的放到少的里面
    public void addNum(int num) {
      if(maxh.isEmpty() || num <= maxh.peek()){
        maxh.add(num);
      } else {
        minh.add(num);
      }
      balance();
    }

    public double findMedian() {
      if(maxh.size() == minh.size()){
        return (double) (maxh.peek() + minh.peek()) / 2;
      } else {
        return maxh.size() > minh.size() ? maxh.peek() : minh.peek();
      }
    }

    private void balance(){
      if(Math.abs(maxh.size() - minh.size()) == 2){
        if(maxh.size() > minh.size()){
          minh.add(maxh.poll());
        } else {
          maxh.add(minh.poll());
        }
      }
    }
  }


}
