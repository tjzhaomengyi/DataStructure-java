package com.mikemyzhao.SomeSkills_0.tricks;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-19 13:30
 * @Description:
 */
public class LC_0621_CPUTaskScheduler {
  public static int leastInterval(char[] tasks, int free) {
    int[] count = new int[256];
    int maxcount = 0;
    for(char task : tasks){
      count[task]++;
      maxcount = Math.max(maxcount, count[task]);
    }

    int maxKinds = 0;//出现次数最多的任务一共多少个
    for (int task = 0; task < 256; task++){
      if(count[task] == maxcount){
        maxKinds++;
      }
    }

    //把最后一组最多个数一样的减掉
    int taskExceptFinalTime = tasks.length - maxKinds;
    //分空格
    int spaces = (free + 1) * (maxcount - 1);//中间间隔 * 减掉最后一组的个数，就是总空格数
    //往空格填
    int restSpaces = Math.max(0, spaces - taskExceptFinalTime);
    return tasks.length + restSpaces;

  }

}
