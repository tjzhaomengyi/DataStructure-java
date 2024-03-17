package com.book.zuoshen.InterviewGuide.chpt8.array;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/26 11:43
 * @Description:
 */
public class MultiArrayApart {
    public int[] product(int[] arr){
        if(arr == null || arr.length < 2){
            return null;
        }
        int[] res = new int[arr.length];
        res[0] = arr[0];
        for(int i = 1; i < arr.length; i++){
            res[i] = res[i - 1] * arr[i];
        }
        int tmp = 1;
        for(int i = arr.length - 1; i > 0; i--){
            res[i] = res[i - 1] * tmp;
            tmp *= arr[i];
        }
        res[0] = tmp;
        return res;
    }
}
