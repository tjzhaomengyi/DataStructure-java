package com.huaweiOD.od2023.s120e138;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-17 9:10
 * @Description:
 */
public class SortByHeightAndWeight {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNext()) {
      int len = in.nextInt();
      int[] heights = new int[len];
      int[] weights = new int[len];
      for(int i = 0; i < len; i++){
        heights[i] = in.nextInt();
      }
      for(int j = 0; j < len; j++){
        weights[j] = in.nextInt();
      }
      PriorityQueue<Student> q = new PriorityQueue<>((o1, o2) -> {
        if(o1.height != o2.height){
          return o1.height - o2.height;
        } else if(o1.weight != o2.height){
          return o1.weight - o2.weight;
        } else {
          return o1.index - o2.index;
        }
      });
      for(int i = 0; i < len; i++){
        int index = i;
        int height = heights[i];
        int weight = weights[i];
        Student s = new Student(index, height, weight);
        q.offer(s);
      }
      List<Student> lst = q.stream().collect(Collectors.toList());

      while(!q.isEmpty()){
        System.out.print(q.poll().index + 1 + " ");
      }
      System.out.println();

    }

  }

}

class Student{
  int index;
  int height;
  int weight;

  public Student(int index, int height, int weight){
    this.index = index;
    this.height = height;
    this.weight = weight;
  }
}
