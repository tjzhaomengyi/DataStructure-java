package com.point2offerspecial.eleven_bin_search;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-13 18:20
 * @Description:
 */
public class PTOS070_SingleNoDuplicateSorted {
  //思路：和PTOS069思路大致差不多，如果某个数字只在nums中出现一次，如果在前半段出现，后半段肯定有影响，如果后半段出现，那么前半段正常
  public int singleNonDuplicate(int[] nums) {
    //技巧：两两分组查看并判断
    int L = 0;//第一组下标
    int R = L + ((nums.length - 1 - L) >> 1); //第二组下标
    while(L <= R){
      int mid = (L + (R - L) >> 1); //中间组
      int i = mid * 2;//中间组第一个数在数组中的下标
      if(i < nums.length - 1 && nums[i] != nums[i + 1]){//中间组出问题了,说明单个字符在左侧
        //技巧：既然这组数不同了，那么他们就可能出现问题的那组
        if(mid == 0 || nums[i - 2] == nums[i - 1]){
          return nums[i];
        }
        //在左侧出现问题导致mid组出现了这个问题
        R = mid - 1;
      } else {
        L = mid + 1;
      }
    }
    return nums[nums.length - 1];
  }
}
