package com.mikemyzhao.SomeSkills_0.TwoPntAndBSearch.twopnts;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-29 22:57
 * @Description:平方有序数组,这个就是归并排序的后半部分，少了前面的分组递归
 */
public class SortedSquares_977 {
  public int[] sortedSquares(int[] nums) {
    int n = nums.length;
    if(nums.length==0) return null;
    int[] res = new int[n];
    int left = 0,right=n-1;
    int write = n-1;//最后反着写即可
    while(left<=right){
      if(nums[left]*nums[left]>nums[right]*nums[right]){//如果左边的大于右边的
        res[write]=nums[left]*nums[left];
        left++;write--;
      }else{
        res[write]=nums[right]*nums[right];
        right--;write--;
      }
    }
    return res;
  }
}
