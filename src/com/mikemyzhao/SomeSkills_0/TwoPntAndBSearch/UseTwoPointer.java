package com.mikemyzhao.SomeSkills_0.TwoPntAndBSearch;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-03 11:42
 * @Description:左右双指针的常用
 */
public class UseTwoPointer {
  /**一般把指针设置为left=0，right=len(nums)-1**/
  /**二分查找模板,注意一些细节，这是寻找一个数的二分搜索**/
  int binarySearch(int[] nums,int target){
    int left = 0;
    int right = nums.length-1;
    while(left <= right){//while加等号不用后续打补丁！！！
      int mid = left+(right-left)/2;//注意：这里防止边界溢出
      if(nums[mid] == target) return mid;
      else if(nums[mid]<target) left = mid+1;
      else if(nums[mid]>target) right = mid-1;
    }
    return -1;
  }
  //LC704,二分查找
  public int search(int[] nums, int target) {
    int left =0;
    int right = nums.length-1;
    while(left<=right){
      int mid = left+(right-left)/2;
      if(nums[mid] == target) return mid;
      else if(nums[mid]>target) right = mid-1;
      else if(nums[mid]<target) left = mid+1;
    }
    return -1;
  }
}
