package com.point2offer;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-09 15:21
 * @Description:
 */
public class Search_53 {
  //有序数组中找到某个数的出现次数,right-left+1
  //如果nums[mid]=target那么右边界right在[mid+1,j]，left在[i,m-1],查找左边界left，执行j=m-1;查找右边界，执行i=m+1
  public int search(int[] nums, int target) {
    if(nums.length==0)return 0;
    //分成左右两部分的下标查找
    //搜索右边界
    int i=0,j=nums.length-1;
    while(i<=j){
      int m = (i+j)/2;
      if(nums[m]<=target){
        i=m+1;
      }else{
        j=m-1;
      }
    }
    int right = i;//找到右侧
    //没有值就返回
    if(j>=0 && nums[j]!=target) return 0;
    //找左侧
    i=0;j=nums.length-1;
    while (i<=j){
      int m=(i+j)/2;
      if(nums[m]<target){//这里是小于没有等于了
        i=m+1;
      }else{
        j=m-1;
      }
    }
    int left = j;
    return right-left-1;
  }

  public int search_II(int[] nums,int target){
    //这个更好理解
    int cnt=0;
    int left =0,right=nums.length-1;
    while(left<right){
      int mid = (left+right)/2;
      if(nums[mid]>=target){
        right=mid;
      }
      if(nums[mid]<target){
        left=mid+1;
      }
    }
    while(left<nums.length&&nums[left++]==target){
        cnt++;
    }
    return cnt;
  }
  //有序数组，每个都是0到n-1，找到缺少的那一个，肯定缺一个
  public int missingNumber(int[] nums){
    int i=0,j=nums.length-1;
    while (i<=j){
      int m = (i+j)/2;
      if(nums[m]==m) i=m+1;
      else j=m-1;
    }
    return i;
  }
}

