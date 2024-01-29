package com.book.point2offerspecial.twelve_sort.count_sort;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-14 12:12
 * @Description:
 */
public class PTOS075_relativeSort {
  public int[] relativeSortArray(int[] arr1, int[] arr2) {
    //arr1没出现在arr2中的放在后面
    int[] counts = new int[1001];
    int[] ans = new int[arr1.length];
    for(int n : arr1){
      counts[n]++;
    }
    int i = 0;
    for(int n : arr2){
      while(counts[n] > 0){
        ans[i++] = n;
        counts[n]--;
      }
    }
    for(int j = 1; j < 1001; j++){
      while(counts[j] > 0){
        ans[i++] = j;
        counts[j]--;
      }
    }
    return ans;
  }
}
