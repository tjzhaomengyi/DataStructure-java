package com.mikemyzhao.divide_and_conquer_10.bigshua;

import java.util.Arrays;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-15 15:08
 * @Description:// 本题测试链接 : https://leetcode.com/problems/closest-subsequence-sum/
 * // 本题数据量描述:
 * // 1 <= nums.length <= 40
 * // -10^7 <= nums[i] <= 10^7
 * // -10^9 <= goal <= 10^9
 * // 通过这个数据量描述可知，需要用到分治，因为数组长度不大
 * // 而值很大，用动态规划的话，表会爆
 *
 * LC1755
 */
public class ClosestSubsequenceSum_3 {
  //左右各20个数字，最接近goal的情况就是都在左边，或者都在右边，或者两边结合
  public static int[] l = new int[1 << 20];
  public static int[] r = new int[1 << 20];

  public static int minAbsDifference(int[] nums,int goal){
    if (nums == null || nums.length == 0){
      return goal;
    }
    //求左右两侧的子序列累加和
    int le = process(nums,0,nums.length>>1,0,0,l);
    int re = process(nums,nums.length >> 1,nums.length,0,0,r);
    Arrays.sort(l,0,le);
    Arrays.sort(r,0,re--);
    int ans = Math.abs(goal);
    for(int i = 0; i < le; i++){
      int rest = goal -l[i];
      while(re > 0 && Math.abs(rest - r[re - 1]) <= Math.abs(rest - r[re])){
        re--;
      }
      ans = Math.min(ans,Math.abs(rest-r[re]));
    }

    return ans;

  }

  //从index位置到end求这个范围子序列的累加和，当index等于end时候返回fill填充节点的位置
  private static int process(int[] nums, int index, int end, int sum, int fill, int[] arr) {
    if(index == end){
      arr[fill++] = sum;
    }else{
      //子序列求和就是要这个点和不要这个点，所以是sum和sum+nums[index],一定要等到index=end下标一致才能填这个fill
      //返回的fill是当前要填写的点
      fill = process(nums,index+1,end,sum,fill,arr);
      fill = process(nums,index+1,end,sum + nums[index], fill, arr);
    }
    return fill;
  }

}
