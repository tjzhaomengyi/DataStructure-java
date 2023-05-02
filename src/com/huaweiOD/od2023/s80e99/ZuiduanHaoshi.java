package com.huaweiOD.od2023.s80e99;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-19 15:30
 * @Description:
 */
public class ZuiduanHaoshi {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str = in.nextLine();
      String[] str_tasks = str.split(",");
      int coldtime = Integer.valueOf(in.nextLine());
      int[] tasks = new int[str_tasks.length];
      for(int i = 0; i < str_tasks.length; i++){
        tasks[i] = Integer.valueOf(str_tasks[i]);
      }
      System.out.println(getShortestTime(tasks, coldtime));

    }
  }


  //技巧:【贪心】让出现次数最多的先填，然后空出冷却时间，先放技能冷却时间最长的，然后中间空出冷却时间。这个解法即使是0时间后续也能补上，太贱了
  // 如果有相同时间都大的都放在前面
  //这道题在左神大厂刷题班38节讲过，下面是自己理解写的
  public static int getShortestTime(int[] nums, int coldtime){
    Map<Integer, Integer> mapJobHashMap = new HashMap<>();
    for(int job : nums){
      mapJobHashMap.put(job,mapJobHashMap.getOrDefault(job, 0) + 1);
    }
    List<Integer> jobtimes = mapJobHashMap.values().stream().sorted((i1, i2) ->Integer.compare(i2, i1)).collect(Collectors.toList());
    int allTime = 0;
    //按照左神贪心的算法直接计算
    int maxtimes = jobtimes.get(0);//最多出现了多少次，统计最多次数的，有多少次分多少最，并且最后一组不需要留空格
    int cnt_maxtimes = 0; //最多次数的元素一共有几个
    for(int i = 0; i < jobtimes.size(); i++){
      if(maxtimes == jobtimes.get(i)){
        cnt_maxtimes++;
      }
    }
    //计算初始长度
    int d = coldtime - cnt_maxtimes + 1;
    allTime = (cnt_maxtimes + d) * (maxtimes - 1) + cnt_maxtimes;
    int allNoMaxCnt = nums.length - cnt_maxtimes;

    int restForNomax = allTime - cnt_maxtimes;
    if(restForNomax - allNoMaxCnt >= 0){
      return allTime; // 正好填满
    } else {
      return allTime + allNoMaxCnt - restForNomax;//把差的补上
    }
  }

}
