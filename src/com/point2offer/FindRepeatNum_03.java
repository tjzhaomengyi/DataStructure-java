package com.point2offer;

import java.util.HashSet;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-03 18:28
 * @Description:找出任意一个重复数字,注意条件：在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内，说明这里肯定有重复
 * @Solve:双指针
 */
public class FindRepeatNum_03 {
  public int findRepeatNumber(int[] nums) {
    if(nums==null || nums.length==0){
      return -1;
    }
    HashSet tmp = new HashSet();
    int n = nums.length;
    for(int i=0;i<n;i++){
      if(!tmp.contains(nums[i])){
        tmp.add(nums[i]);
      }else {
        return nums[i];
      }
    }
    return -1;
  }

  //原地交换，空间复杂度控制为O(1)，因为长度为n，数字范围0~n-1,所以肯定有重复，用下标作为索引，这就是一个HashMap的小问题
  /**
   * 解法：通过i遍历nums
   *  如果nums[i]=i，说明在对应位置不需要交换
   *  如果nums[nums[i]]=nums[i],表示nums[i]处和i处的元素值都为nums[i]，找到了重复数值，返回nums[i]
   *   否则交换i和nums[i]的元素，将此数字交换到对应位置
   * **/
  public int findRepeatNumber_2(int[] nums) {
    int i=0;
    int n = nums.length;
    while(i<n){
      if(nums[i]==i){
        i++;
        continue;
      }
      if(nums[nums[i]]==nums[i]) return nums[i];
      int tmp = nums[i];
      nums[i]=nums[tmp];
      nums[tmp]=tmp;
    }
    return -1;
  }

  }
