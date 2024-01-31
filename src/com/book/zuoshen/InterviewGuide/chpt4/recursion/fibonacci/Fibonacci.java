package com.book.zuoshen.InterviewGuide.chpt4.recursion.fibonacci;

import sun.swing.text.html.FrameEditorPaneTag;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/31 16:02
 * @Description:求斐波那契数列第n个值
 */
public class Fibonacci {
    public int f(int n){
        if(n < 1){
            return 0;
        }
        if(n == 1 || n == 2){
            return 1;
        }
        int res = 1; //当前前面的结果
        int pre = 1; //等待求解位置前一个值
        int tmp = 0;
        for(int i = 3; i <= n ;i++){
            tmp = res;
            res = res + pre;
            pre = tmp;
        }
        return res;
    }

    //使用矩阵求解
    public static int[][] matrixPower(int[][] m, int p){
        int[][] res = new int[m.length][m[0].length];
        //把结果先设置为单位矩阵
        for(int i = 0; i < res.length; i++){
            res[i][i] = 1;
        }
        int[][] tmp = m;
        for(; p != 0; p >>= 1){
            if((p & 1) != 0){
                res = muliMatrix(res, tmp);
            }
            tmp = muliMatrix(tmp, tmp);
        }
        return res;
    }

    public static int[][] muliMatrix(int[][] m1, int[][] m2){
        int[][] res = new int[m1.length][m2[0].length];
        for(int i = 0; i < m1.length; i++){
            for(int j = 0; j < m2[0].length; j++){
                for(int k = 0; k < m2.length; k++){
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }

    public static int fBuyMatrix(int n){
        if( n < 1){
            return 0;
        }
        if (n == 1 || n == 2){
            return 1;
        }
        int[][] base = {{1, 1}, {1, 0}};
        int[][] res = matrixPower(base, n - 2);
        return res[0][0] + res[1][0];
    }



}
