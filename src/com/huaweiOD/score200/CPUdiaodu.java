package com.huaweiOD.score200;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2023-06-01 15:35
 * @Description:
 */
public class CPUdiaodu {
  static class Task implements Comparable<Task>{
    int id;
    int priority;
    int duration;
    int startTime;

    public Task(int id, int priority, int duration, int startTime){
      this.id = id;
      this.priority = priority;
      this.duration = duration;
      this.startTime = startTime;
    }

    @Override
    public int compareTo(Task o) {
      if(this.priority == o.priority){
        return this.startTime - o.startTime;
      }
      return o.priority - this.priority;//优先级从大到小
    }
  }
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    List<Task> tasks = new ArrayList<>();
    while (in.hasNextInt()) {
      int id = in.nextInt();
      int priority = in.nextInt();
      int duration = in.nextInt();
      int startTime = in.nextInt();
      tasks.add(new Task(id, priority, duration, startTime));
    }

    TreeSet<Task> taskTreeSet = new TreeSet<>();
    long time = 0;
    for(Task task : tasks){
      long timeDiff = task.startTime - time;
      while(!taskTreeSet.isEmpty() && timeDiff > 0){
        Task tmp = taskTreeSet.first();
        if(timeDiff >= tmp.duration){
          taskTreeSet.remove(tmp);
          timeDiff -= tmp.duration;
          time += tmp.duration;
          System.out.println(tmp.id + " " + time);
        } else {
          tmp.duration -= timeDiff;
          time += timeDiff;
          break;
        }
      }
      if(time < task.startTime){
        time = task.startTime;
      }
      taskTreeSet.add(task);
    }
    while(!taskTreeSet.isEmpty()){
      Task task = taskTreeSet.pollFirst();
      time += task.duration;
      System.out.println(task.id + " " + time);
    }
  }


}
