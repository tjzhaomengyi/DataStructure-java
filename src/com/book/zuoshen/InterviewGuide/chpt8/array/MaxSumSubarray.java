package com.book.zuoshen.InterviewGuide.chpt8.array;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/24 17:13
 * @Description:子数组最大和
 */
public class MaxSumSubarray {
    public int maxSum(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int cur = 0;
        for(int i = 0; i < arr.length; i++){
            cur += arr[i];
            max = Math.max(max, cur);
            cur = cur < 0 ? 0 : cur; //如果当前值是负数，重新统计cur总和
        }
        return max;
    }
}
