package com.hots100.sorts;

/**
 * @Author: zhaomengyi
 * @Date: 2022-11-24 11:34 上午
 * @Description:
 */
public class Code169_WaterKing {
  public int majorityElement(int[] nums) {
    int N = nums.length;
    int HP = 0;
    int cand = 0;
    for(int i = 0; i < N; i++){
      if(HP == 0){ //思路：HP=0就是当前没有候选
        cand = nums[i];
        HP = 1;
      } else if(cand == nums[i]){
        HP++;
      } else {
        HP--;
      }
    }
    if(HP == 0){
      System.out.println("no such number");
      return -1;
    }

    HP = 0;
    for(int i = 0; i < N; i++){
      if(nums[i] == cand){
        HP++;
      }
    }
    if(HP > N / 2){
      return cand;
    } else {
      System.out.println("No such number too");
      return -1;
    }
  }
}
