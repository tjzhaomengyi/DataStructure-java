package com.weeklyproblems.weekly_2022_1_1;

/**
 * @Author: zhaomengyi
 * @Date: 2024/3/10 11:14
 * @Description:
 */
public class Code03_MultiMaxPoint {
    //A[left..right],B[i]，B[0..i-1]已经消耗完了，B[i..m-1],直到把B数组消耗完，能获得的最大分数返回
    public static int zuo(int[] A, int[] B, int left, int right, int i){
        if(i == B.length){
            return 0;//B数组消耗完，返回0
        }
        int p1 = A[left] * B[i] + zuo(A, B, left + 1, right, i + 1);
        int p2 = A[right] * B[i] + zuo(A, B, left, right - 1, i + 1);
        return Math.max(p1, p2);
    }
    public static int max(int[] A, int[] B){
        return zuo(A, B, 0, A.length - 1, 0);
    }

    //通过取A数字后，B的位置
    public static int zuo(int[] A, int[] B, int left ,int right){
        int leftAlready = left; //左边消耗的
        int rightAlready = A.length - right - 1;//右边消耗的
        int i = leftAlready + rightAlready; //这样在参数中减少i参数
        if(i == B.length){
            return 0;
        }
        int p1= A[left] * B[i] + zuo(A, B, left + 1, right);
        int p2 = A[right] * B[i] + zuo(A, B, left, right - 1);
        return Math.max(p1, p2);
    }

    public static int max1(int[] A, int[] B){
        return zuo(A, B, 0, A.length - 1);
    }

    //todo:把他这个要改写一下，这个不好理解
    public static int maxDP(int[] A, int[] B){
        int N = A.length;
        int M = B.length;
        int[][] dp = new int[M + 1][M + 1];
        for(int L = M - 1; L >= 0; L--){
            for(int j = L + 1; j <= M; j++){ //限制右侧使用个数
                int R = N - 1 - M + j ; //M-j表示右侧使用了多少个，N-1是最后一个
                int i = L + N - R - 1;
                dp[L][i] = Math.max(A[L] * B[i] + dp[L + 1][j], A[R] * B[i] + dp[L][j - 1]);
            }
        }
        return dp[0][M];
    }
}
