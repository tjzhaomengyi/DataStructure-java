package com.book.point2offerspecial.twelve_sort.merge_sort;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-14 15:43
 * @Description:
 */
public class MergeSort {
  //方法1：递归,归并排序的题目用递归基本都可以搞定
  public void mergeSort(int[] arr){
    if(arr == null || arr.length < 2) return;
    process(arr, 0, arr.length - 1);
  }

  public void process(int[] arr, int L, int R){
    if(L == R){
      return;
    }

    int mid = L + ((R - L) >> 1);
    process(arr, L, mid );
    process(arr, mid + 1, R);
    merge(arr, L, mid, R);
  }

  public void merge(int[] arr, int L, int M, int R){
    int[] help = new int[R - L + 1];
    int i = 0;
    int p1 = L;
    int p2 = M + 1;
    while(p1 <= M && p2 <= R){
      help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
    }
    while(p1 <= M){
      help[i++] = arr[p1++];
    }
    while(p2 <= R){
      help[i++] = arr[p2++];
    }
    for(i = 0; i < help.length; i++){
      arr[L + i] = help[i];
    }
  }

  //方法2：非递归
  public void mergeSort2(int[] arr){
    if(arr == null || arr.length < 2) return;
    int N = arr.length;
    int mergeSize = 1;//一半区间
    while(mergeSize < N){
      //当前左组第一个位置
      int L = 0;
      while(L < N){
        if(mergeSize >= N - L){ //mergeSize(可以想象成递归函数里面help数组的大小)，mergeSize+L超过了N
          break;
        }
        int M = L + mergeSize - 1;
        int R = M + Math.min(mergeSize, N - M - 1);
        merge(arr, L, M, R);
        L = R  + 1;
      }
      if(mergeSize > N / 2){
        break;
      }
      mergeSize <<= 1;
    }
  }

}
