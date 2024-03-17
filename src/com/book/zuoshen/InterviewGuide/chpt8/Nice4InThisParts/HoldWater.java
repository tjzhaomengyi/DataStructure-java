package com.book.zuoshen.InterviewGuide.chpt8.Nice4InThisParts;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/27 15:09
 * @Description:求能得到水量总大小
 */
public class HoldWater {
    // 大思路只考虑i位置上方能盛多少水，i位置的数量就是[0...i-1]的最大值，和[i+1..n-1]的最大值，两个最大值的最小值，水量等于这个最小值-arr[i]
    // 水[i] = min(左max，右max）- arr[i] > 0 ? ...:0
    public int getWater(int[] arr){
        if(arr == null || arr.length < 3){
            return 0;
        }
        int[] leftMaxs = new int[arr.length];
        leftMaxs[0] = arr[0];
        for(int i = 1; i < arr.length; i++){
            leftMaxs[i] = Math.max(leftMaxs[i - 1], arr[i]);
        }
        int[] rightMaxs = new int[arr.length];
        rightMaxs[arr.length - 1] = arr[arr.length - 1];
        for(int i = arr.length - 2; i >= 0; i--){
            rightMaxs[i] = Math.max(rightMaxs[i + 1], arr[i]);
        }
        int res = 0;
        for(int i = 1; i < arr.length - 1; i++){
            res += Math.max(Math.min(leftMaxs[i - 1], rightMaxs[i + 1]) - arr[i], 0);
        }
        return res;
    }
}
