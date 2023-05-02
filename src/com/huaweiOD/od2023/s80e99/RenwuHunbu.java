package com.huaweiOD.od2023.s80e99;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.function.ToIntFunction;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-20 9:22
 * @Description:
 */
public class RenwuHunbu {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNext()) {
      int taskNum = in.nextInt();
      int[][] myTasks = new int[taskNum][3];
      for(int i = 0; i < taskNum; i++){
        myTasks[i][0] = in.nextInt();
        myTasks[i][1] = in.nextInt();
        myTasks[i][2] = in.nextInt();
      }
      Arrays.sort(myTasks, Comparator.comparingInt(a -> a[0]));//技巧:二维数组比较器
      PriorityQueue<Integer> q = new PriorityQueue<>((o1, o2) -> o2 - o1);
      int result = 0;
      for(int i = 0; i < taskNum; i++){
        //如果当前任务的开始时间大于优先队列中任务的结束时间就可以复用
        while(!q.isEmpty() && q.peek() <= myTasks[i][0]){
          q.poll();
        }
        //将当前任务的并行度个服务器加入队列
        for(int j = 0; j < myTasks[i][2]; j++){
          q.offer(myTasks[i][1]);
        }
        //记录最大并行度
        result = Math.max(result, q.size());
      }
      System.out.println(result);
    }
  }


}
