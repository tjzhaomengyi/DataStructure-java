package com.book.zuoshen.InterviewGuide.chpt8.array;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/23 10:06
 * @Description:水王问题，要求时间复杂度O(N),空间复杂度O(1)
 */
public class ShuiWang {
    public void printHalfMajor(int[] arr){
        int cand = 0;
        int times = 0;
        for(int i = 0; i < arr.length; i++){
            if(times == 0){
                cand = arr[i];
                times = 1;
            } else if(arr[i] == cand){
                times++;
            } else {
                times--;
            }
        }
        times = 0;
        for(int i = 0; i <= arr.length; i++){
            if(arr[i] == cand){
                times++;
            }
        }
        if(times > arr.length / 2){
            System.out.println(cand);
        } else {
            System.out.println("no such num");
        }
    }



}
