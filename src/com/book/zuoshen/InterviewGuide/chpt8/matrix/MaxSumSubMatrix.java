package com.book.zuoshen.InterviewGuide.chpt8.matrix;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/24 17:27
 * @Description:
 */
public class MaxSumSubMatrix {
    public int maxSum(int[][] m){
        if(m == null || m.length == 0 || m[0].length == 0){
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int cur = 0;
        int[] s = null; //累加数组
        for(int i = 0; i < m.length; i++){
            s = new int[m[0].length];
            for(int j = i; j < m.length; j++){
                cur = 0;
                for(int k = 0; k < s.length; k++){
                    s[k] += m[j][k];
                    cur += s[k];
                    max = Math.max(max, cur);
                    cur = cur < 0 ? 0 : cur;
                }
            }
        }
        return max;
    }
}
