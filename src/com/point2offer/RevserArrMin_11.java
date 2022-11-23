package com.point2offer;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-04 22:54
 * @Description:
 * @Slove:这个证明可以用二分找到这个值，是一个结论
 */
public class RevserArrMin_11 {

  public int minArray(int[] numbers) {
    int l=0,r=numbers.length-1;
    while(l<=r){
      int mid = l+(r-l)/2;
      if(numbers[mid]>numbers[r]) l=mid+1;//中间的大于最右边的,移动的区域在右边
      else if(numbers[mid]<numbers[r]) r=mid;//移动的区域在左边
      else r--;
    }
    return numbers[l];
  }
}
