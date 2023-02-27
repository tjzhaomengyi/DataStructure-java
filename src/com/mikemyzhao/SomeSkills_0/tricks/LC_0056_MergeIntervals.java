package com.mikemyzhao.SomeSkills_0.tricks;

import java.util.Arrays;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-16 19:51
 * @Description:合并在一起的区间
 */
public class LC_0056_MergeIntervals {
  //技巧:先根据起始点排序，如果后面的start大于前面的end就新开一组，否则的话就对当前的interval进行扩展
  public static int[][] merge(int[][] intervals) {
    if(intervals.length == 0){
      return new int[0][0];
    }
    Arrays.sort(intervals,(a, b) -> a[0] - b[0]);
    int s = intervals[0][0];
    int e = intervals[0][1];
    int size = 0;
    for(int i = 1; i < intervals.length; i++){
      if(intervals[i][0] > e){//技巧:当前的起始点大于了上面一组的结束点把上面一组生成,
        intervals[size][0] = s;
        intervals[size++][1] = e;
        s = intervals[i][0];//技巧:生成完上一组，把这一组的s和e更新，什么时候生成待定
        e = intervals[i][1];
      } else {
        e = Math.max(e, intervals[i][1]);
      }
    }
    //技巧:最后一定把最后剩下的一组生成，因为还在等着，囧
    intervals[size][0] = s;
    intervals[size][1] = e;//先取再加加
    size++;
    return Arrays.copyOf(intervals, size);

  }

}
