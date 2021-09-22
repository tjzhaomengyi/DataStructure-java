package com.mikemyzhao.Sum;

import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-13 12:56
 * @Description:求和问题
 */
public class Sums {
  /**两数求和,普通解法用一个HashMap**/
  int[] twoSums(int[] nums,int target){
    int n = nums.length;
    Map<Integer,Integer> index =  new HashMap<>();
    for(int i=0;i<n;i++){
      index.put(nums[i],i);
    }
    for(int i=0;i<n;i++){
      int other = target-nums[i];
      if(index.containsKey(other) && index.get(other)!=i){
        return new int[]{i,index.get(other)};
      }
    }
    return new int[]{-1,-1};
  }

  /**求和系列的通用模板，左右指针解法**/
  List<ArrayList<Integer>> twoSumTarget(int[] nums,int target){
    //1、首先保证数组有序
    Arrays.sort(nums);
    int low = 0,high=nums.length-1;
    List<ArrayList<Integer>> res = new ArrayList<>();
    while(low<high){
      int sum = nums[low]+nums[high];
      int left = nums[low];//记录最初的值
      int right = nums[high];
      if(sum<target){
        //前面处理指针，后面跳过重复元素
        while(low<high && nums[low]==left) low++;//注意：让指针继续往下走，如果碰上一样的就跳过
      }else if(sum>target){
        while(low<high && nums[high]==right) high--;
      }else{//只能sum==target了
        res.add(new ArrayList<>());
      }
    }
    return res;
  }
  /**三数求和**/
  List<ArrayList<Integer>> threeSum(int[] nums,int targe){
    Arrays.sort(nums);
    int n = nums.length;
    List<ArrayList<Integer>> res=new ArrayList<>();
    for(int i=0;i<n;i++){
      List<ArrayList<Integer>> tuples = twoSumTarget(Arrays.copyOfRange(nums,i,nums.length-1),targe-nums[i]);
      for(ArrayList<Integer> tuple:tuples){
        tuple.add(nums[i]);
        res.add(tuple);
      }
      while(i<n-1 && nums[i]==nums[i+1])i++;
    }
    return res;
  }
}
