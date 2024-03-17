package com.book.point2offerspecial.two_array.ArrAndTwoPnt;

/**
 * @Author: zhaomengyi
 * @Date: 2023-01-20 10:03
 * @Description:
 */
public class PTOS006_TwoSum {
  public int[] twoSum(int[] numbers, int target) {
    int L = 0;
    int R = numbers.length - 1;
    while (L < R){
      if(numbers[L] + numbers[R] < target){
        L++;
      } else if(numbers[L] + numbers[R] > target){
        R--;
      } else {
        return new int[]{L, R};
      }
    }
    return null;
  }

  public static void main(String[] args) {
    int[] nums = new int[]{1,2,4,6,10};
    int[] ans = new PTOS006_TwoSum().twoSum(nums, 0);
    System.out.print(ans);
  }
}
