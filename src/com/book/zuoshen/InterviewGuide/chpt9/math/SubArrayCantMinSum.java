
package com.book.zuoshen.InterviewGuide.chpt9.math;

/**
 * @Author: zhaomengyi
 * @Date: 2024/3/6 10:56
 * @Description:子集不能凑出的最小和是多少
 */
public class SubArrayCantMinSum {
    public int unformedSum(int[] arr){
        if(arr == null || arr.length == 0){
            return 1;
        }
        int sum = 0;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < arr.length; i++){
            sum += arr[i];
            min = Math.min(min, arr[i]);
        }
        //dp[i]为true表示i这个累加和能够被arr的子集相加得到，如果为false表示不能。
        // 如果arr[0..i]范围上的数组成的所有子集可以累加出k，那么arr[0..i+1]范围上的数组成的所有子集必然可以累加得到k+arr[i +1]
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        for(int i = 0; i < arr.length; i++){
            for(int j = sum; j >= arr[i]; j--){
                dp[j] = dp[j - arr[i]] ? true : dp[j];
            }
        }
        for(int i = min; i != dp.length; i++){
            if(!dp[i]){
                return i;
            }
        }
        return sum + 1;
    }
}
