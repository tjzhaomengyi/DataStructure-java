package com.mikemyzhao.SomeSkills_0.tricks;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-16 22:12
 * @Description:
 */
public class LC_0088_MergeSortArray {
  public void merge(int[] nums1, int m, int[] nums2, int n) {
    int index = nums1.length;
    while (m > 0 && n>0){
      if(nums1[m - 1] >= nums2[n - 1]){
        nums1[--index] = nums1[--m];
      } else {
        nums1[--index] = nums2[--n];
      }
    }
    while(m > 0){
      nums1[--index] = nums1[--m];
    }
    while(n > 0){
      nums1[--index] = nums2[--n];
    }
  }
}
