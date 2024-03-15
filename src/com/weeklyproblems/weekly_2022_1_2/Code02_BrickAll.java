package com.weeklyproblems.weekly_2022_1_2;

import java.util.Arrays;

/**
 * @Author: zhaomengyi
 * @Date: 2024/3/11 09:40
 * @Description:每行只能最多用两块砖，并且要把砖块全用掉，最多能拜多少层
 */
public class Code02_BrickAll {
    public static int maxLevels(int[] arr){
        if(arr == null){
            return 0;
        }
        int n = arr.length;
        if(n < 2){
            return n;
        }
        Arrays.sort(arr);
        int p1 = levels(arr, arr[n - 1]);
        int p2 = levels(arr, arr[n - 1] + arr[0]);
        return Math.max(p1, p2);
    }

    public static int levels(int[] arr, int len){
        int ans = 0;
        int L = 0;
        int R = arr.length - 1;
        while(L <= R){
            //只有两种可能，要不最长部分可以自己占据一行，要不和一个小的凑成一行，结束！
            if(arr[R] == len){
                R--;
                ans++;
            } else if (L < R && arr[L] + arr[R] == len) { //
                L++;
                R--;
                ans++;
            } else {
                return -1;
            }
        }
        return ans;
    }
}
