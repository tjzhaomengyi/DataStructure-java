package com.book.point2offerspecial.two_array.ArrAndTwoPnt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2023-01-19 22:53
 * @Description:
 */
public class PTOS007_ThreeSum {
  public List<List<Integer>> threeSum(int[] nums){
    Arrays.sort(nums);
    int N = nums.length;
    List<List<Integer>> ans = new ArrayList<>();
    //从前往后找，这样找到正好把这个数塞到最后
    for(int i = N - 1; i > 1; i--) {
      //技巧:保证[R]位置的数不重复，和下面 twosum 的 【L】呼应，避免出现重复
      if (i == N - 1 || nums[i] != nums[i + 1]) {
        List<List<Integer>> tmp = twoSum(nums, i - 1, -nums[i]);
        for (List<Integer> cur : tmp) {
          cur.add(nums[i]);
          ans.add(cur);
        }
      }
    }
    return ans;
  }

  public List<List<Integer>> twoSum(int[] nums, int end, int t){
    List<List<Integer>> ans = new ArrayList<>();
    int L = 0;
    int R = end;
    while (L < R){
      if(nums[L] + nums[R] > t){
        R--;
      } else if(nums[L] + nums[R] < t){
        L++;
      } else {
        //技巧:nums[L] + nums[R] = t，
        // (1)往答案里面添加需要满足一些条件:(1)保证[L] 和 [L-1]不一样，一样的话答案就有重复 【因为数组是排序好的，所以验证前一个就行】
        // (2)如果L是起始位，也可以直接加进去
        if(L == 0 || nums[L - 1] != nums[L]){
          List<Integer> cur = new ArrayList<>();
          cur.add(nums[L]);
          cur.add(nums[R]);
          ans.add(cur);
        }
        L++;
      }
    }
    return ans;
  }
}
