package com.book.zuoshen.InterviewGuide.chpt8.array;

import java.util.TreeSet;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/23 18:26
 * @Description:子数组的最大长度不超过k，使用前缀和解决
 */
public class LongestSumArrayNoMoreThanK {
    public int getMaxLength(int[] arr, int k){
        TreeSet<Integer> set = new TreeSet<Integer>();
        set.add(0); //什么数都不放就是0
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for(int i = 0; i < arr.length; i++){
            sum += arr[i]; //当前累加和
            if(set.ceiling(sum - k) != null){ // subarr <= k => sum - pre <= k => sum - k <= pre
                max = Math.max(max, sum - set.ceiling(sum - k));
            }
            set.add(sum);
        }
        return max;
    }

}
