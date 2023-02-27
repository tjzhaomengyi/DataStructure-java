package com.daybyday.垃圾题;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-24 09:50
 * @Description:
 */
public class LC2357_MinimumOperations {

  public int minimumOperations(int[] nums) {
    HashSet<Integer> tmp = new HashSet<>();
    for(int n : nums){
      if(n != 0) {
        tmp.add(n);
      }
    }
    return tmp.size();
  }
}
