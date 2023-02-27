package com.point2offerspecial.twelve_sort;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-14 11:26
 * @Description:
 */
public class PTOS074_Merge {
  public int[][] merge(int[][] intervals) {
    //技巧：这题直接用排序的接口
    if(intervals.length == 0 || intervals[0].length == 0 || intervals == null) return null;
    Arrays.sort(intervals,(i1, i2) -> i1[0] - i2[0]);
    List<int[]> merged = new LinkedList<>();
    int i = 0;
    while(i < intervals.length){
      int[] tmp = new int[]{intervals[i][0], intervals[i][1]};
      int j = i + 1;
      //和左神056写法稍有不同，左神直接在原地起飞了，每次都把右侧的结果往左边推；
      //《剑指offer》这里就是开辟一个结果空间，然后每次遍历判断是否修改
      while(j < intervals.length && intervals[j][0] <= tmp[1]){//一旦后面有符合要求的就比大小调整
        tmp[1] = Math.max(tmp[1], intervals[j][1]);
        j++;
      }
      merged.add(tmp);//如果没有就加入结果集，i跟着j向后推
      i = j;
    }
    int[][] ans = new int[merged.size()][];
    return merged.toArray(ans);
  }
}
