package com.book.point2offerspecial.eight_tree.four_treemap_treeset;

import java.util.TreeSet;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-12 14:14
 * @Description:要求（1）abs(nums[i] - nums[j]) <= t (2) abs(i - j) <= k
 */
public class PTOS057_ContainsNearbyAlmostDuplicate {
  public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
    //技巧：TreeSet的使用
    TreeSet<Long> set = new TreeSet<>();
    for(int i = 0; i < nums.length; i++){
      Long lower = set.floor((long)nums[i]);
      if(lower != null && lower >= (long)nums[i] - t){
        return true;
      }
      Long upper = set.ceiling((long)nums[i]);
      if(upper != null && upper <= (long)nums[i] + t){
        return true;
      }
      set.add((long)nums[i]);
      if(i >= k){
        set.remove((long)nums[i - k]);
      }
    }
    return false;
  }
}
