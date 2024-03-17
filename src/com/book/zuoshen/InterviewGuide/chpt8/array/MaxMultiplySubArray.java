package com.book.zuoshen.InterviewGuide.chpt8.array;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/24 17:53
 * @Description:
 */
public class MaxMultiplySubArray {
    public double maxProduct(double[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }
        double max = arr[0];
        double min = arr[0];
        double res = arr[0];
        double maxEnd = 0;
        double minEnd = 0;
        for(int i = 1; i < arr.length; ++i){
            maxEnd = max * arr[i];
            minEnd = min * arr[i];
            max = Math.max(Math.max(maxEnd, minEnd), arr[i]); //有可能是最小的那个
            min = Math.min(Math.min(maxEnd, minEnd), arr[i]);
            res = Math.max(res, max);
        }
        return res;
    }
}
