package com.point2offer;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-08 18:07
 * @Description:
 * @Solve：概念：(1)将101112...中每一位称为数位，记为n;(2)将10、11、12称为数字，记为num;(3)数字10是一个两位数，此数字位数为2，记为digit；(4)每个digit位数的起始数字记为start
 * 每个digit下数位的数量count=9*start*digit。
 * 求解过程：1、确定n所在数字的位数，记为digit;2、确定n所在的数字，记为num；确定n是num中的哪一数位，返回结果。
 * 细节：如何确定所求数位的所在数字的位数：
 */
public class FindNthDigit_44 {


  public int findNthDigit(int n) {
    int digit = 1;
    long start = 1;
    long count = 9;
    while (n > count) { // 1.
      n -= count;
      digit += 1;
      start *= 10;
      count = digit * start * 9;
    }
    long num = start + (n - 1) / digit; // 2.
    return Long.toString(num).charAt((n - 1) % digit) - '0'; // 3.


  }
}
