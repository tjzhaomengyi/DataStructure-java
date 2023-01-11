package com.mikemyzhao.BFSDFS_ForTreeAndGraph_1_4.bigshua.BFS.Graph.topology.bigshua;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-18 11:07
 * @Description:
 */
public class LC_0210_CourseScheduleII {

  public static class Course {
    public int name;
    public int in;//技巧:课程的入度
    public ArrayList<Course> nexts;

    public Course(int n) {
      name = n;
      in = 0;//技巧:记录自己前面的入度数量
      nexts = new ArrayList<>();//技巧:记录自己后面连着谁
    }
  }

  //给定课程数量和课程关系网
  public static int[] canFinish(int courses, int[][] relation) {
    //数学结论:看是否拓扑成功:1、先找入度为0的点，然后删除掉入度为0的点，再找入度为0的点，再删除，以此类推....
    // 当关系网中没有节点的时候完成拓扑
    int[] res = new int[courses];
    if (relation == null || relation.length == 0) {
      return res;
    }
    //初始化课程和它的入度
    HashMap<Integer, Course> nodes = new HashMap<>();
    for(int i = 0; i < courses; i++){
      res[i] = i;
    }
    for (int[] arr : relation) {
      //注意这里from在后面，to在前面
      int to = arr[0];
      int from = arr[1];
      if (!nodes.containsKey(to)) {
        nodes.put(to, new Course(to));
      }
      if (!nodes.containsKey(from)) {
        nodes.put(from, new Course(from));
      }
      Course t = nodes.get(to);
      Course f = nodes.get(from);
      f.nexts.add(t);
      t.in++;
    }

    int needPreNums = nodes.size();
    //技巧:拓扑排序的BFS来了
    //技巧:1往队列里面塞入度为0的点
    Queue<Course> zeroInQueue = new LinkedList<>();
    for (Course node : nodes.values()) {
      if (node.in == 0) {//先找入度为0的节点
        zeroInQueue.add(node);
      }
    }
    int count = 0;
    //技巧:2开始BFS了，出队列
    int index = 0;
    while (!zeroInQueue.isEmpty()) {
      Course cur = zeroInQueue.poll();
      count++;
      res[index++] = cur.name;
      for (Course next : cur.nexts) {
        if (--next.in == 0) {//删掉一个入度点！
          //技巧;如何让下一个点称为入度为0的点并进入队列
          zeroInQueue.add(next);

        }
      }
    }
    //技巧:3看看从队列里面出来的数量是不是和需要修完的课程数量一样
    return count == needPreNums ? res : new int[0];
  }

  public static void main(String[] args) {
    int[] res = canFinish(3, new int[][]{{1,0}});
    for(int i = 0; i < res.length; i++) {
      System.out.print(res[i] + " ");
    }
  }

}
