package com.book.zuoshen.InterviewGuide.chpt4.recursion.fibonacci;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/31 17:16
 * @Description:生完三个月后能生，当前月等于上个月累加和 + 三个月之前的
 */
public class ThreeMonthCowOrRabbit {
    public int c1(int n){
        if(n < 1){
            return 0;
        }
        if(n == 1 || n == 2 || n == 3){
            return n;
        }
        return c1(n - 1) + c1(n - 3);
    }

    public int c2(int n){
        if(n < 1){
            return 0;
        }
        if( n == 1 || n == 2 || n == 3){
            return n;
        }

        int res = 3;
        int pre = 2;
        int prepare = 1;
        int tmp1 = 0;
        int tmp2 = 0;
        for(int i = 4; i <=n; i++){
            tmp1 = res;
            tmp2 = pre;
            res = res + prepare;
            pre = tmp1;
            prepare = tmp2;
        }
        return res;
    }

    public int cbyMatrix(int n){
        if(n < 1){
            return 0;
        }
        if(n == 1 || n == 2 || n == 3){
            return n;
        }
        int[][] base = {{1, 1 ,0},{0, 0, 1}, {1, 0, 0}};
        int[][] res = Fibonacci.matrixPower(base, n - 3);
        //结果前面是一个(3, 2, 1)的系数
        return 3 * res[0][0] + 2 * res[1][0] + 1 * res[2][0];
    }
}
