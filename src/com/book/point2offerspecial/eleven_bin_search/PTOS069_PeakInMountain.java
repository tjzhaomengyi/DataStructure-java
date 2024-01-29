package com.book.point2offerspecial.eleven_bin_search;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-13 17:32
 * @Description:
 */
public class PTOS069_PeakInMountain {
  public int peakIndexInMountainArray(int[] arr) {
    //思路：（1）要找的值肯定大于两边的数 （2）如果找的数 大于前面的 小于后面的在递增组，目标数在该数后面。
    // （3）如果找到的数 小于前面的数，大于后面的数 说明该组是递减组，往前面找
    int L = 1;
    int R = arr.length - 2;
    while(L <= R){
      int mid = L + ((R - L) >> 1);
      if(arr[mid] > arr[mid - 1] && arr[mid] < arr[mid + 1]){
        return mid;
      } else if(arr[mid] > arr[mid - 1] && arr[mid] < arr[mid + 1]) {
        L = mid + 1;
      } else {
        R = mid - 1;
      }
    }
    return -1;
  }
}
