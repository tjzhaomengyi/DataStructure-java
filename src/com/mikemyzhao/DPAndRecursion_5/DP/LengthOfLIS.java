package com.mikemyzhao.DP_5;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-04 15:49
 * @Description:最长上升子序列输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * @NO:LC300，解法：dp[i]表示到nums[i]这个数的最长子序列，
 *  前后两个指针，状态转移方程：dp[i]=max(dp[i],dp[j]+1)
 */
public class LengthOfLIS {
  public int lengthOfLIS(int[] nums){
    int[] dp = new int[nums.length];
    Arrays.fill(dp,1);
    for(int i=0;i<=nums.length-1;i++){
      for(int j=0;j<i;j++){
        if(nums[i]>nums[j]){ //只有上升才考虑最长子序列大小
          dp[i]=Math.max(dp[i],dp[j]+1);
        }
      }
    }
    //[1, 2, 3, 4, 5, 3, 6, 4, 5]，这个可能在一半的时候已经出现了最优子序列，所以这Arrays里求最大即可
//    System.out.println(Arrays.toString(dp));
    List<Integer> lst = Arrays.stream(dp).boxed().collect(Collectors.toList());
    return (int) Collections.max(lst);
  }

  public static void main(String[] args) {
    int[] nums = new int[]{1,3,6,7,9,4,10,5,6};
    System.out.println(new LengthOfLIS().lengthOfLIS(nums));
  }
}
