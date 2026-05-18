package com.niuke.BM101.slidewindow;

import java.util.HashSet;

public class BM90_MAXLenSubArray {
    public int maxLen(int[] arr){
        HashSet<Integer> set = new HashSet<>();
        int left = 0;
        int max = 0;
        for(int right = 0; right < arr.length; right++){
            while(set.contains(arr[right])){
                set.remove(arr[left]);
                left++;
            }
            set.add(arr[right]);
            max = Math.max(max, right - left + 1);
        }
        return max;
    }
}
