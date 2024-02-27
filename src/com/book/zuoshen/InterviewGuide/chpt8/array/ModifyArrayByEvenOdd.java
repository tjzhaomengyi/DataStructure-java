package com.book.zuoshen.InterviewGuide.chpt8.array;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/24 14:34
 * @Description:让数组的偶数全在偶数位置，让奇数全在奇数位
 */
public class ModifyArrayByEvenOdd {
    public void modify(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        int even = 0;
        int odd = 1;
        int end = arr.length - 1;
        while(even <= end && odd <= end){
            if((arr[end] & 1) == 0){
                swap(arr, end, even);
                even += 2;
            } else {
                swap(arr, end, odd);
                odd += 2;
            }
        }
    }

    public void swap(int[] arr, int index1, int index2){
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }
}
