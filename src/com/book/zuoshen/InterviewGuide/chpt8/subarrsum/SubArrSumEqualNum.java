package com.book.zuoshen.InterviewGuide.chpt8.subarrsum;

import java.util.HashMap;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/25 16:03
 * @Description:未排序数组中累加和为给定值的最长子数组系列问题
 */
public class SubArrSumEqualNum {
    public int maxLength(int[] arr, int k){
        if(arr == null || arr.length == 0){
            return 0;
        }

        //前缀和记录，key为sum的值，val为第一次出现该sum的位置
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, -1); //什么也不放，不是开头
        int len = 0;
        int sum = 0;
        for(int i = 0; i < arr.length; i++){
            sum += arr[i];
            if(map.containsKey(sum - k)){
                len = Math.max(i - map.get(sum - k), len);
            }
            if(!map.containsKey(sum)){
                map.put(sum, i);
            }
        }
        return len;
    }


}
