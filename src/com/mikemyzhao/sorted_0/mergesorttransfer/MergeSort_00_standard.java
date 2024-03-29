package com.mikemyzhao.sorted_0.mergesorttransfer;

/**
 * @Author: zhaomengyi
 * @Date: 2022-01-01 10:15
 * @Description:MergeSort的基本形式
 */
public class MergeSort_00_standard {
  public static void mergeSort(int[] arr){
    if(arr==null || arr.length<2){
      return;
    }
    process(arr,0,arr.length-1);
  }
  /**请把[L...R]变成有序**/
  public static void process(int[] arr,int L,int R){
    if(L==R){
      return;
    }
    int mid = L + ((R-L)>>1);
    //递归黑盒，让左右边都满足排好序
    process(arr,L,mid);
    process(arr,mid+1,R);
    merge(arr,L,mid,R);//整理归并排序开辟的辅助数组
  }

  //假设条件：此时L到M有序，M +1到R有序
  public static void merge(int[] arr,int L,int M,int R){
    int[] help = new int[R-L+1];
    int i = 0;
    int p1 = L;
    int p2 = M+1;
    while(p1 <= M && p2 <= R){
      help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
    }
    while(p1 <= M){
      help[i++] = arr[p1++];
    }
    while(p2<=R){
      help[i++] = arr[p2++];
    }
    for(i=0;i<help.length;i++){
      arr[L+i] = help[i];
    }
  }

  //非递归版本:思路:步长开始为1，然后每次2倍调整，直到步长正好大于当灯数组长度。
  public static void mergeSortNoRec(int[] arr){
    if(arr == null || arr.length < 2){
      return;
    }
    int step = 1;
    int N = arr.length;
    while (step < N){
      //要关心凑不满一组的情况
    }
  }

}
