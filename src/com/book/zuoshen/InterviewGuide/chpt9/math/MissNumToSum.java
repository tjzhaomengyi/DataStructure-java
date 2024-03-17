package com.book.zuoshen.InterviewGuide.chpt9.math;

/**
 * @Author: zhaomengyi
 * @Date: 2024/3/6 11:19
 * @Description:如果想得到range上范围的所有数，给出的数组中缺少哪些数
 */
public class MissNumToSum {
    public int minNeeds(int[] arr, int range){
        int needs = 0;
        long touch = 0;
        for(int i = 0; i < arr.length; i++){
            while(arr[i] > touch + 1){
                touch += touch + 1;
                needs++;
                if(touch >= range){
                    return needs;
                }
            }
            touch += arr[i];
            if(touch >= range){
                return needs;
            }
        }
        while(range >= touch + 1){
            touch += touch + 1;
            needs++;
        }
        return needs;
    }
}
