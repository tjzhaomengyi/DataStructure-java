package com.book.zuoshen.InterviewGuide.chpt8.array;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/23 09:38
 * @Description:找到需要排序的最短子数组
 */
public class MinLengthToSort {
    //从右往左遍历一次，有比当前值最小值大的说明需要调整。然后从左往右遍历一次，有比当前最大值小的说明需要听啊哦正
    public int getMinLength(int[] arr){
        if(arr == null || arr.length < 2){
            return 0;
        }
        int min = arr[arr.length - 1];
        int noMinIndex = -1;
        for(int i = arr.length - 2; i >= 0; i--){
            if(arr[i] > min){
                noMinIndex = i;
            } else {
                min = Math.min(min, arr[i]);
            }
        }
        if(noMinIndex == -1){
            return 0;
        }
        int max = arr[0];
        int noMaxIndex = -1;
        for(int i = 1; i < arr.length; i++){
            if(arr[i] < max){
                noMaxIndex = i;
            } else {
                max = arr[i];
            }
        }
        if(noMaxIndex == -1){
            return 0;
        }
        return noMaxIndex - noMinIndex + 1;
    }
}
