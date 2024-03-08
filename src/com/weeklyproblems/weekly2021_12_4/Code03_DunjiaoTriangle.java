package com.weeklyproblems.weekly2021_12_4;

import java.util.Arrays;

/**
 * @Author: zhaomengyi
 * @Date: 2024/3/7 20:10
 * @Description:hulu，给点求能够组成的钝角三角形个数,非常不错的题，解法非常讨巧，葫芦就是hulu
 */
public class Code03_DunjiaoTriangle {
    //几何结论：如果从一个点出发生成钝角三角形，只能确定一个，并且最后一个点和起始点在一个半区
    //把每个点都加上360°，这样就不需要回退了，把数组扩充2倍
    public static long dunjiao(int[] arr){
        int n = arr.length;
        int m = n << 1;
        int[] enlarge = new int[m];
        Arrays.sort(arr);
        for(int i = 0; i < n; i++){
            enlarge[i] = arr[i];
            enlarge[i + n] = arr[i] + 36000;
        }
        long ans = 0;
        //这里使用扩充数组不回退，直接干！起始点L只跑到n即可
        for(int L = 0, R = 0; L < n; L++){
            while(R < m && enlarge[R] - enlarge[L] < 18000){
                R++;//往后延伸
            }
            ans += num(R - L - 1);
        }
        return ans;
    }

    //C(N2)的计算
    public static long num(long nodes){
        return nodes < 2 ? 0 : (nodes * (nodes - 1)) >> 1;
    }
}
