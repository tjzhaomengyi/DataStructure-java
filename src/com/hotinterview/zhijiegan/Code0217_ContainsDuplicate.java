package com.hotinterview.zhijiegan;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: zhaomengyi
 * @Date: 2022-12-20 11:54 上午
 * @Description:
 */
public class Code0217_ContainsDuplicate {
  public boolean containsDuplicate(int[] nums) {
    HashSet<Integer> tmp = new HashSet<>();
    for(int i = 0; i < nums.length; i++){
      tmp.add(nums[i]);
    }
    return tmp.size() == nums.length ? false : true;
  }
}
