package com.point2offer;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-08 14:43
 * @Description:摩尔投票法：如果有超过容量一半的元素存在就符合这个规则，每个先投票，碰上一样的就+1，不一样的就-1，最后>1的肯定是众数
 */
public class Majorelement_39 {
  public int majorityElement(int[] nums) {
    int x=0,votes=0;
    for(int num:nums) {
      if(votes==0) x=num;
      if(x==num){
        votes++;
      }else {
        votes--;
      }
    }
    return x;
  }
}
