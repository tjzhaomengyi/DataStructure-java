package com.book.zuoshen.InterviewGuide.chpt8.array;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/26 12:11
 * @Description:partition排序
 */
public class SortPart {
    public void leftUnique(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        int u = 0;//u是有序序列的最后一个位置
        int i = 1;
        while(i != arr.length){
            if(arr[i++] != arr[u]){
                swap(arr, ++u, i - 1); //把u扩充，把arr[i]交换到arr[++u]扩充位置
            }
        }
    }

    //快排序基础版,只有0，1，2是三个数
    public void sort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        int left = -1; //小区的位置
        int index = 0;
        int right = arr.length; //大区的位置
        while(index < right){
            if(arr[index] == 0){ //小区
                swap(arr, ++left, index++); //先把left++，然后left和index交换
            } else if(arr[index] == 2){
                swap(arr, index, --right);
            } else {
                index++;
            }
        }
    }

    public void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
