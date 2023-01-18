package com.hots100.zhijiegan;

/**
 * @Author: zhaomengyi
 * @Date: 2022-12-16 12:21 下午
 * @Description:
 */
public class Code0011_ContainerMostWater {
  public int maxArea(int[] height) {
    int most = 0;
    int l = 0;
    int r = height.length - 1;
    while (l < r){
      int h = Math.min(height[l], height[r]);
      most = Math.max((r - l) * h, most);
      if(height[l] > height[r]){
        r--;
      } else {
        l++;
      }
    }
    return most;
  }
}
