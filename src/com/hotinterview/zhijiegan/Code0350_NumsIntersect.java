package com.hotinterview.zhijiegan;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Author: zhaomengyi
 * @Date: 2022-12-20 12:52 下午
 * @Description:
 */
public class Code0350_NumsIntersect {
  public int[] intersect(int[] nums1, int[] nums2) {
    int[] l_nums = nums1.length >= nums2.length ? nums1 : nums2;
    int[] s_nums = l_nums == nums1 ? nums2 : nums1;
    HashMap<Integer,Integer> tmp = new HashMap<>();
    ArrayList<Integer> ans = new ArrayList<>();
    for(int i = 0; i < l_nums.length; i++){
      if(tmp.containsKey(l_nums[i])){
        tmp.put(l_nums[i], tmp.get(l_nums[i]) + 1);
      } else {
        tmp.put(l_nums[i],1);
      }
    }
    for(int i = 0; i < s_nums.length; i++){
      if(tmp.containsKey(s_nums[i]) && tmp.get(s_nums[i]) > 0){
        ans.add( s_nums[i]);
        tmp.put(s_nums[i], tmp.get(s_nums[i]) - 1);
      }
    }
    int[] res = new int[ans.size()];
    for(int i = 0; i < res.length; i++){
      res[i] = ans.get(i);
    }
    return res;
  }
}
