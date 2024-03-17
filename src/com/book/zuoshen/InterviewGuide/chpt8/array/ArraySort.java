package com.book.zuoshen.InterviewGuide.chpt8.array;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/24 11:35
 * @Description:1到N在数组对应i-1的位置
 */
public class ArraySort {
    public void sort(int[] arr){
        int tmp = 0;
        for(int i = 0; i < arr.length; i++){
            while(arr[i] != i + 1){
                tmp = arr[arr[i] - 1];
                arr[arr[i] - 1] = arr[i];
                arr[i] = tmp;
            }
        }
    }
}
