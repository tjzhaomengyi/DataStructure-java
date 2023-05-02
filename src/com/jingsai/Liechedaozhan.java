package com.jingsai;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-23 11:03
 * @Description:
 */
public class Liechedaozhan {
  public int findDelayedArrivalTime(int arrivalTime, int delayedTime) {
    return (arrivalTime + delayedTime) % 24;
  }
}
