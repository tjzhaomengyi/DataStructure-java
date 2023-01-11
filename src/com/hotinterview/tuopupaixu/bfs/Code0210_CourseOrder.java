package com.hotinterview.tuopupaixu.bfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: zhaomengyi
 * @Date: 2022-12-27 12:59 下午
 * @Description:
 */
public class Code0210_CourseOrder {

  //思路：先把图表示转换成自己的标识
  public class Course{
    public int name;
    public int in; //课程的入度
    public ArrayList<Course> nexts;//后续可以修的课程

    public Course(int n){
      this.name = n;
      in = 0;
      nexts = new ArrayList<>();
    }
  }


  //numCourses是要修的课程数
  public int[] findOrder(int numCourses, int[][] prerequisites) {
    int[] res = new int[numCourses];
    for(int i = 0; i < numCourses; i++){
      res[i] = i;
    }
    if(prerequisites == null || prerequisites.length == 0){
      return res;
    }
    //初始化课程和入度
    HashMap<Integer, Course> nodes = new HashMap<>();
    //先全放进去
    for(int i = 0; i < numCourses; i++){
      nodes.put(i, new Course(i));
    }
    //再根据第二个参数补信息
    for(int[] arr : prerequisites){
      int from = arr[1];
      int to = arr[0];
      //如果from 和 to 在nodes中存在
      Course t = nodes.get(to);
      Course f = nodes.get(from);
      f.nexts.add(t);//修改f的出链
      t.in++; // 修改t的入度
    }

    //思路：开始拓扑排序
    Queue<Course> zeroInQueue = new LinkedList<>();//把入度为0的结点先放入
    for(Course node : nodes.values()){
      if(node.in == 0){
        zeroInQueue.add(node);
      }
    }
    int count = 0;
    int index = 0;
    while(!zeroInQueue.isEmpty()){
      Course cur = zeroInQueue.poll();
      count++;
      res[index++] = cur.name;
      for(Course next : cur.nexts){
        if(--next.in  == 0){
          zeroInQueue.add(next);
        }
      }
    }
    return count == numCourses ? res : new int[0];
  }

  public static void main(String[] args) {
    int[] res = new Code0210_CourseOrder().findOrder(3, new int[][]{{1,0}});
    for(int i = 0; i < res.length; i++) {
      System.out.print(res[i] + " ");
    }
  }

}
