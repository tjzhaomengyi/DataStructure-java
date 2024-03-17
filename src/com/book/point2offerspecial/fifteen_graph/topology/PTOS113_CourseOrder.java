package com.book.point2offerspecial.fifteen_graph.topology;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-21 11:27
 * @Description:拓扑排序模板 HashSet<Integer，Node
 */
public class PTOS113_CourseOrder {
    public class Course{
      int name;
      int in;//技巧：这个是关键，一定要有这个属性，这个是必须的，因为后面每次把in为0的结点推入zeroQueue中
      int out;
      ArrayList<Course> nexts;

      public Course(int name){
        this.name = name;
        nexts = new ArrayList<>();
      }
    }

    public class Graph {
      HashMap<Integer, Course> nodes;

      public Graph(){
        nodes = new HashMap<>();
      }
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
      int[] ans = new int[numCourses];
      for(int i = 0; i < numCourses; i++){
        ans[i] = i;
      }
      if(prerequisites == null || prerequisites.length == 0 ) {
        return ans;
      }
//
      int N = prerequisites.length;
      int M = prerequisites[0].length;
      //技巧：拓扑排序必须准备一个Hash表示是当前节点和出去的节点
      // 1、把图初始化好，因为没有weight权重的事情，所以图中只有Map节点，存号对应Course实体
      Graph graph = new Graph();
      //技巧：一定要在这吧所有节点添加上，否则测试用例报错
      for(int i = 0; i < numCourses; i++){
        graph.nodes.put(i, new Course(i));
      }
      for(int[] courses : prerequisites){
        int to = courses[0];
        int from = courses[1];
        Course toNode = graph.nodes.get(to);
        Course fromNode = graph.nodes.get(from);
        fromNode.nexts.add(toNode);
        fromNode.out++;
        toNode.in++;
      }

      //技巧：2、使用BFS完成拓扑排序
      Queue<Course> zeroInQueue = new LinkedList<>();
      //技巧：找入度为0的结点
      for(Course node : graph.nodes.values()){
        if(node.in == 0){
          zeroInQueue.add(node);
        }
      }

      int count = 0;
      int index = 0;
      while(!zeroInQueue.isEmpty()){
        Course cur = zeroInQueue.poll();
        count++;
        ans[index++] = cur.name;
        for(Course next : cur.nexts){
          //太难了~直接记住例子吧：把入度为0的结点不断放入ZeroQueue中
          next.in--;
          if(next.in == 0){
            zeroInQueue.add(next);
          }
        }
      }
      return count == numCourses ? ans : new int[0];
    }
}
