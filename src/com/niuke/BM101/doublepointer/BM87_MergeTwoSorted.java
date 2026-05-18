package com.niuke.BM101.doublepointer;

public class BM87_MergeTwoSorted {
    //合并两个升序数组，数组A的长度等于两个数组之和，不要开辟新的数组空间，把结果都放在数组A中
    public void merge(int[] A, int m, int[] B, int n){
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        while(i >= 0 && j >= 0){
            if(A[i] > B[j]){
                A[k--] = A[i--];
            } else {
                A[k--] = B[j--];
            }
        }
        while(j >= 0){
            A[k--] = B[j--];
        }
    }
}
