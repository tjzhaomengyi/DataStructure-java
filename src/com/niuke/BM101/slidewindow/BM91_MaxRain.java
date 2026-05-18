package com.niuke.BM101.slidewindow;

/**
 * 盛水最多的蓄水池
 */
public class BM91_MaxRain {
    public int maxRain(int[] arr){
        if(arr.length < 2) return 0;
        int left = 0;
        int right = arr.length - 1;
        int max = 0;
        while(left < right){
            int height = Math.min(arr[left], arr[right]);
            max = Math.max(max, (right - left) * height);
            //这里有个贪心，就是抛弃不要小的柱子，保住高的柱子，才可能遇上更大的蓄水量
            if(arr[left] < arr[right]){
                left++;
            } else {
                right--;
            }
        }
        return max;
    }
}
