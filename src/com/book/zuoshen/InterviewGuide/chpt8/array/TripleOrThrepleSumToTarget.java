package com.book.zuoshen.InterviewGuide.chpt8.array;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/23 12:12
 * @Description:为指定和的二元组或者三元组
 */
public class TripleOrThrepleSumToTarget {
    public void printTuple(int[] arr, int k){
        if(arr == null || arr.length < 2){
            return;
        }
        int left = 0;
        int right = arr.length - 1;
        while(left < right){
            if(arr[left] + arr[right] < k){
                left++;
            } else if(arr[left] + arr[right] > k){
                right--;
            } else {
                //避免打印重复的，如果上面一个重复重现过就不要打印了
                if(left == 0 || arr[left - 1] != arr[left]){
                    System.out.println(arr[left] + "," + arr[right]);
                }
                left++;
                right--;
            }
        }
    }

    //三元组
    public void printTriad(int[] arr, int k){
        if(arr == null || arr.length < 3) {
            return;
        }
        for(int i = 0; i < arr.length - 2; i++){
            if(i == 0 || arr[i] != arr[i - 1]){
                //从i + 1位置开始找等于k-arr[i]的二元组
                printRest(arr, i, i + 1, arr.length - 1, k - arr[i]);
            }
        }
    }

    public void printRest(int[] arr, int f, int l, int r, int k){
        while(l < r){
            if(arr[l] + arr[r] < k){
                l++;
            } else if(arr[l] + arr[r] > k){
                r--;
            } else {
                if(l == f + 1 || arr[l - 1] != arr[l]){
                    System.out.println(arr[f] + "," + arr[l] + "," + arr[r]);
                }
                l++;
                r++;
            }
        }
    }
}
