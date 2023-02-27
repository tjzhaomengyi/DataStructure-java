package com.point2offerspecial.five_map.two_yingyong;


import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-07 10:39
 * @Description:
 */
public class PTOS035_FindMinDifference {
  public int findMinDifference(List<String> timePoints) {
    //技巧：把一天的时间分成 24 * 60 = 1440个分钟,但是如果出现0点就特殊，算作第二天，也可以往回减，就是数组中first + 1440 - last
    if(timePoints.size() > 1440) return 0;

    boolean minFlags[] = new boolean[1441];
    for(String time : timePoints){
      String[] t = time.split(":");
      int min = Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
      if(minFlags[min]){
        return 0;//有相同的时间存在
      }
      minFlags[min] = true;
    }
    return timeDiffHelper(minFlags);
  }

  //在该数组中求距离最近的两个元素
  public int timeDiffHelper(boolean minFlag[]){
    int ans = minFlag.length - 1;
    int prev = -1;
    //扣边界：记录下当前的first 要和 last套圈再计算一下
    int first = -1;
    int last = -1;
    for(int i = 0; i < minFlag.length; i++){
      if(prev == -1 && minFlag[i] == true){
        prev = i;
        first = i;
      } else if(prev != -1 && minFlag[i] == true){
        ans = Math.min(ans, i - prev);
        prev = i;
        last = i;
      }
    }
    return Math.min(ans, first + 1440 - last);
  }

  public static void main(String[] args) {
    List<String> str = new ArrayList<>();
    str.add("00:00");
    str.add("23:59");
    int ans = new PTOS035_FindMinDifference().findMinDifference(str);
    System.out.println(ans);
  }
}
