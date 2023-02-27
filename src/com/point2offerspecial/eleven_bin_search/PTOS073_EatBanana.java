package com.point2offerspecial.eleven_bin_search;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-14 10:41
 * @Description:
 */
public class PTOS073_EatBanana {
  //让piles中的数有序，让狒狒吃香蕉的速度为1~piles[len-1]范围挨个尝试，看看能不能满足h小时的标准
  public int minEatingSpeed(int[] piles, int h) {
    int max = Integer.MIN_VALUE;
    for (int pile : piles) {
      max = Math.max(pile, max);
    }

    //left和right分别是狒狒吃香蕉速度的范围区间，在这个范围内取值
    int left = 1;
    int right = max;
    while (left <= right) {
      int mid = left + ((right - left) >> 1);
      int hours = getHours(piles, mid);
      if (hours <= h) {
        if (mid == 1 || getHours(piles, mid - 1) > h) { //《剑指offerII-二分》专用写法，直接把左边界考虑进去
          return mid;
        }
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return -1;
  }

  private int getHours(int[] piles, int speed) {
    int hours = 0;
    for (int pile : piles) {
      //技巧：
      hours += (pile + speed - 1) / speed;
    }
    return hours;
  }
}
