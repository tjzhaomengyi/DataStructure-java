package com.book.zuoshen.InterviewGuide.chpt8.array;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/23 12:58
 * @Description:正整数数组中累加和为给定值的最长子数组长度
 */
public class LongestSumArrayEqual {
    public int getMaxLength(int[] arr, int k){
        if(arr == null || arr.length == 0 || k <= 0){
            return 0;
        }
        int left = 0;
        int right = 0;
        int sum = arr[0];
        int len = 0;
        while(right < arr.length){
            if(sum == k){
                len = Math.max(len, right - left + 1);
                sum -= arr[left++]; //左侧收缩,原则从左侧缩小值
            } else if(sum < k){
                right++;
                if(right == arr.length){
                    break;
                }
                sum += arr[right];
            } else {
                sum -= arr[left++];
            }
        }
        return len;
    }
}
