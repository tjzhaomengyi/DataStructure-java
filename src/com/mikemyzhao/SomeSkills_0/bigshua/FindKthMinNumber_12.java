package com.mikemyzhao.SomeSkills_0.bigshua;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-23 16:49
 * @Description:找到第k小的数字，使用基本定理，如果A和B两个数组等长，那么子数组的上中位数是整体的上中位数
 */
public class FindKthMinNumber_12 {
  //A[s1....e1]
  //B[s2...e2],这里隐含条件s1和s2等长
  //返回整体的上中位数
  public static int getUpMedian(int[] A, int s1, int e1, int[] B, int s2, int e2) {
    int mid1 = 0;
    int mid2 = 0;
    while (s1 < e1) {
      mid1 = (s1 + e1) / 2;
      mid2 = (s2 + e2) / 2;
      if (A[mid1] == B[mid2]) {
        return A[mid1];
      }
      //两个中点不等,如果是奇数个，要抛出去一个
      if (((e1 - s1 + 1) & 1) == 1) {
        if (A[mid1] > B[mid2]) {
          if (B[mid2] >= A[mid1 - 1]) {//后面的小于1前面的
            return B[mid2];
          }
          //这里用到整体到局部的中位数性质，大的选自己的头部，小的选自己的尾部
          e1 = mid1 - 1;
          s2 = mid2 + 1;
        } else { //A[mid1] < B[mid2]
          if(A[mid1] >= B[mid2-1]){
            return A[mid1];
          }
          e2 = mid2 - 1;
          s1 = mid1 +1;
        }
      }else{
        if(A[mid1] > B[mid2]){
          e1 = mid1;
          s2 = mid2 +1;
        } else {
          e2 = mid2;
          s1 = mid1 + 1;
        }
      }
    }
    return Math.min(A[s1],B[s2]);
  }

  // 进阶问题 : 在两个都有序的数组中，找整体第K小的数
  // 可以做到O(log(Min(M,N)))
  public static int findKthNum(int[] arr1, int[] arr2, int kth) {
    int[] longs = arr1.length >= arr2.length ? arr1 : arr2;
    int[] shorts = arr1.length < arr2.length ? arr1 : arr2;
    int l = longs.length;
    int s = shorts.length;
    if (kth <= s) { // 1)
      return getUpMedian(shorts, 0, kth - 1, longs, 0, kth - 1);
    }
    if (kth > l) { // 3)
      if (shorts[kth - l - 1] >= longs[l - 1]) {
        return shorts[kth - l - 1];
      }
      if (longs[kth - s - 1] >= shorts[s - 1]) {
        return longs[kth - s - 1];
      }
      return getUpMedian(shorts, kth - l, s - 1, longs, kth - s, l - 1);
    }
    // 2)  s < k <= l
    if (longs[kth - s - 1] >= shorts[s - 1]) {
      return longs[kth - s - 1];
    }
    return getUpMedian(shorts, 0, s - 1, longs, kth - s, kth - 1);
  }
}
