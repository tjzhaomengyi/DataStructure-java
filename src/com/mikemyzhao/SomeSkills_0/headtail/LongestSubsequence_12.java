package com.mikemyzhao.SomeSkills_0.bigshua.headtail;

import java.util.HashMap;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-23 19:22
 * @Description:找出连续最长子序列，可以比连续，像直播问题
 * // 本题测试链接 : https://leetcode.com/problems/longest-consecutive-sequence/
 * // 单表讲解，一张表只考虑开头结尾正确，中间脏数据无所谓
 */
public class LongestSubsequence_12 {
  public static int longestConsecutive(int[] nums) {
    HashMap<Integer, Integer> map = new HashMap<>();
    int len = 0;
    for(int num : nums){
      if(!map.containsKey(num)){
        map.put(num,1);
        int preLen = map.getOrDefault(num - 1,0);
        int posLen = map.getOrDefault(num + 1,0);
        int all = preLen + posLen + 1;
        //只更新边界情况，中间的不用管了，反正进来找到头的和尾部都是更新好的，鸡贼
        map.put(num - preLen,all);
        map.put(num + posLen,all);
        len = Math.max(len,all);
      }
    }
    return len;
  }



  public static void main(String[] args) {
  }
}

