package com.mikemyzhao.SomeSkills_0.TwoPntAndBSearch.twopnts.bigshua;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-18 17:42
 * @Description:
 */
//技巧:因为已知是在1到n，有至少1个重复数字，别客气下标怼吧
public class LC_0287_FindDuplicateNumber {
  public static int findDuplicate(int[] nums){
    if(nums == null || nums.length < 2){
      return -1;
    }
    int slow = nums[0];
    int fast = nums[nums[0]];//下标怼
    while(slow != fast){
      slow = nums[slow];
      fast = nums[nums[fast]];
    }
    //技巧:slow和fast撞上啦，撞上啦，用快慢链表那招，让两人相爱
    fast = 0;
    while(slow != fast){
      fast = nums[fast];
      slow = nums[slow];
    }
    return nums[slow];
  }
}
