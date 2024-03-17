package com.book.point2offerspecial.eleven_bin_search;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-13 20:04
 * @Description:
 */
public class PTOS072_SqrX {
  public int mySqrt(int x) {
    //技巧：《剑指offer II-二分查找》这部分内容技巧都是在 mid<=target这个条件内 再卡返回条件，
    // 这个技巧避免了再开始的时候再判断多余部分
    if(x == 0) return 0;
    int left = 1;
    int right = x;
    while(left <= right){
      int mid = left + ((right - left) >> 1);
      if(mid * mid <= x){
        if((mid + 1) * (mid + 1) > x){
          return mid;//卡住条件返回
        }
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return 0;
  }
}
