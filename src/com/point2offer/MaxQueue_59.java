package com.point2offer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-10 12:13
 * @Description:单调队列辅助，注意取当前最大值就是用单调递减进行辅助！
 */
public class MaxQueue_59 {
  private Queue queue;
  private LinkedList max_vals;
  public MaxQueue_59() {
    queue = new LinkedList();
    max_vals = new LinkedList();
  }

  //返回最大值
  public int max_value() {
    if(max_vals.isEmpty()) return -1;
    return (int) max_vals.peekFirst();//这里必须是First
  }

  public void push_back(int value) {
    /**注意：这这里有个多虑情况就是1,4,4这种情况
     * (1)1进max_vals,(2)4进来，1被提出(3)4又进来，4=4所以不被踢出，两个4进来所以又进来4.所以相等的话有几个都是重复的进来，但永远不会少
     * **/
    while(!max_vals.isEmpty() && (int)max_vals.peekLast()<value){//注意这里是while只要小的全删,只要小的都删
      max_vals.pollLast();
    }
    queue.offer(value);
    max_vals.offerLast(value);
  }

  public int pop_front() {
    if(queue.isEmpty()){
      return -1;
    }
    int res = (int) queue.poll();
    if(res==(int)max_vals.peekFirst()){
      max_vals.pollFirst();
    }
    return res;
  }

}
