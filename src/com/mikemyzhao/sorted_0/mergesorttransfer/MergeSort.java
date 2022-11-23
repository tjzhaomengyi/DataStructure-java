package com.mikemyzhao.sorted_0;

import java.util.Arrays;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-02 18:37
 * @Description:归并排序
 */
public class MergeSort {
  /**
   * 先递归再归并
   **/
  //1、取中间位置，中间点即可
  //2、递归排序分解的左边和右边
  //3、将左右边归并在一起，合二为一；方法，两组数一遍一个指针，一个指针指一个，比较，把小的放在最后的结果里
  void mergeSort(int q[], int l, int r) {
    if (l >= r) return;
    int mid = l + r >> 1; //1、取中间位置
    mergeSort(q, l, mid);//2、归并左边
    mergeSort(q, mid + 1, r);//2、归并右边

    //3、左右两边数组一边一个指针，合并
    int k = 0, i = l, j = mid + 1;
    int[] tmp = new int[q.length];
    while (i <= mid && j <= r) {//关键,双指针将两边进行归并
      if (q[i] <= q[j]) tmp[k++] = q[i++];
      else tmp[k++] = q[j++];
    }
    while (i <= mid) {
      tmp[k++] = q[i++];
    }
    while (j <= r) {
      tmp[k++] = q[j++];
    }
    for (i = l, j = 0; i <= r; i++, j++) {
      q[i] = tmp[j];
    }

  }

  public static void main(String[] args) {
    int[] nums = new int[]{2, 3, 4, 1, 6};
    new MergeSort().mergeSort(nums, 0, 4);
    System.out.println(Arrays.toString(nums));
  }
}
