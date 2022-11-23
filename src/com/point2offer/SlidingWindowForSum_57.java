package com.point2offer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-09 21:58
 * @Description:使用滑窗解决求和问题
 */
public class SlidingWindowForSum_57 {
  public int[][] findContinuousSequence(int target) {
    /**
     * 考虑从第i位置开始，可能求的target的组合有哪些
     * 当窗口的和小于target时候，需要窗口扩大，右边界向右移动
     * 当窗口的和大于target时候，需要减少减少窗口，因为规定从i开始，所以要右移左边界
     * 当找到了target，开始从i+1开始的窗口，左边界向右移动。
     * **/
    int i = 1,j=1,sum=0;
    List<int[]> res = new ArrayList<>();

    while(i<=target/2){//注意：处理一半即可,因为是递增序列
      if(sum<target){
        //右边界向右移动
        sum=sum+j;
        j++;
      }else if(sum>target){
        sum=sum-i;
        i++;
      }else{
        int[] arr = new int[j-i];
        for(int k=i;k<j;k++){
          arr[k-i]=k;
        }
        res.add(arr);
        //左边界向右移动
        sum=sum-i;
        i++;
      }
    }

    return res.toArray(new int[res.size()][]);
  }
}
