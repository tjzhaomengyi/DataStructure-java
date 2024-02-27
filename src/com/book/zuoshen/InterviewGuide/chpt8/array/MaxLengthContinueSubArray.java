package com.book.zuoshen.InterviewGuide.chpt8.array;

import java.util.HashSet;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/23 10:51
 * @Description:找出数组中可整合的最长子数组，可整合：就是从小到大每两个数相差为1
 */
public class MaxLengthContinueSubArray {
    public int getMaxLength(int[] arr){
        //技巧：如果可以整合，那么这个子数组max - min + 1 = 元素个数
        if(arr == null || arr.length == 0){
            return 0;
        }
        int len = 0;
        int max = 0;
        int min = 0;
        HashSet<Integer> set = new HashSet<Integer>(); //判断重复
        for(int i = 0; i < arr.length; i++){
            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;
            for(int j = i; j < arr.length; j++){
                if(set.contains(arr[j])){
                    break;
                }
                set.add(arr[j]);
                max = Math.max(max, arr[j]);
                min = Math.min(min, arr[j]);
                if(max - min == j - i){
                    len = Math.max(len, j - i + 1);
                }
            }
            set.clear();//清空，下一组
        }
        return len;
    }
}
