package com.huaweiOD.nowcoder;

import java.util.Scanner;
import java.util.*;
/**
 * @Author: zhaomengyi
 * @Date: 2023-04-12 19:27
 * @Description:
 */
public class RecordScore {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    // 注意 hasNext 和 hasNextLine 的区别
    PriorityQueue<Student> q;
    while (in.hasNextLine()) { // 注意 while 处理多个 case
      int len = Integer.valueOf(in.nextLine());
      int daxiao = Integer.valueOf(in.nextLine());
      if(daxiao == 0){//降序
        q = new PriorityQueue<>((o1, o2) -> o1.score != o2.score ? o2.score - o1.score : o1.index - o2.index);
      } else {//升序
        q = new PriorityQueue<>((o1, o2) -> o1.score != o2.score ? o1.score - o2.score : o1.index - o2.index);
      }
      for(int i = 0 ; i < len; i++){
        String record = in.nextLine();
        String name = record.split(" ")[0];
        int score  = Integer.parseInt(record.split(" ")[1]);
        q.add(new Student(name, score, i));
      }
      while(!q.isEmpty()){
        Student out = q.poll();
        System.out.println(out.name + " " + out.score);
      }
    }
  }
}

class Student{
  String name;
  int score;
  int index;
  public Student(String name, int score, int index){
    this.name = name;
    this.score = score;
    this.index = index;
  }
}


