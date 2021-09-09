package com.mikemyzhao.sorted;

import java.util.Arrays;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-02 18:15
 * @Description:模板快排，分治+递归思想
 */
public class QuickSorted {
  //1、确定分界点x
  //2、调整区间,大的放在x右边，小的放在x左边，这里比较重要，方法(1)开两个数组(2)
  //3、递归处理左右两端

  void quick_sort(int[] q,int l ,int r){
    if(l>=r) return;
    int i = l-1,j=r+1,x=q[l+r>>1];
    while(i<j){
      do i++;while(q[i]<x);
      do j--;while(q[j]>x);
      if(i<j){ //上面保证了小于/大于就不交换直接++或者--
        int tmp=q[i];
        q[i]=q[j];
        q[j]=tmp;
      }
      quick_sort(q,l,j);
      quick_sort(q,j+1,r);
    }
  }

  public static void main(String[] args) {
    int[] nums = new int[]{2,3,4,1,6};
    new QuickSorted().quick_sort(nums,0,4);
    System.out.println(Arrays.toString(nums));
  }
}
