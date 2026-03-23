package com.mikemyzhao.sorted_0.mergesorttransfer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2022-01-01 9:53
 * @Description:Offer51,计算右侧小于当前元素的个数,逆序对问题
 */
public class InverseCount_02 {
//  private int res = 0;
  public int reversePairs(int[] nums){
    if(nums.length==0 || nums==null){
      return 0;
    }
    int res = process(nums,0,nums.length-1);
    return res;
  }

  public int process(int[] arr,int L,int R){
    if(L==R){
      return 0;
    }
    int mid = L+((R-L)>>1);
    //左侧逆序对的数量+右侧逆序对的数量+归并时候发现的逆序对数量
    return process(arr,L,mid)+ process(arr,mid+1,R)+merge(arr,L,mid,R);
  }

  public int merge(int[] arr,int L,int M,int R){
    int[] help = new int[R-L+1];
    int i = help.length-1;
    int p1 = M;
    int p2 = R;
    int res = 0;
    while(p2 >= M+1 && p1 >= L){
      res += arr[p1] > arr[p2] ? (p2-M):0;
      help[i--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
    }
    while(p2>=M+1){
      help[i--] = arr[p2--];
    }
    while(p1>=L){
      help[i--]=arr[p1--];
    }
    for(i=0;i<help.length;i++){
      arr[L+i]=help[i];
    }
    return res;
  }

}
