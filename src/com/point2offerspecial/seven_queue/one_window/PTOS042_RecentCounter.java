package com.point2offerspecial.seven_queue.one_window;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-07 17:44
 * @Description:
 */
public class PTOS042_RecentCounter {
  private Queue<Integer> times;
  private int windowSize;
  public PTOS042_RecentCounter() {
    times = new LinkedList<>();
    windowSize = 3000;
  }

  public int ping(int t) {
    times.offer(t);
    while(times.peek() + windowSize < t){
      times.poll();//窗口移动
    }
    return times.size();
  }
}
