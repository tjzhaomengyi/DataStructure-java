package com.book.zuoshen.InterviewGuide.chpt9.math;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/28 17:27
 * @Description:
 */
public class LeftRightMaxDiff {
    public int maxAbs(int[] arr){
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < arr.length; i++){
            max = Math.max(arr[i], max);
        }
        return Math.max(max - arr[0], max - arr[arr.length - 1]);
    }
}
