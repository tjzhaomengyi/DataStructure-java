package com.book.zuoshen.InterviewGuide.chpt4.recursion.fibonacci;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/31 16:36
 * @Description:
 */
public class ClumbStairWays {

    public int s(int n){
        if(n < 1){
            return 0;
        }
        if(n == 1 || n == 2){
            return n;
        }
        int res = 2;
        int pre = 1;
        int tmp = 0;
        for(int i = 3; i <= n; i++){
            tmp = res;
            res = res + pre;
            pre = tmp;
        }
        return res;
    }

    public static int sByMatrix(int n){
        if(n < 1){
            return 0;
        }
        if(n == 1 || n == 2){
            return n;
        }
        int[][] base = {{1, 1}, {1, 0}};
        int[][] res = Fibonacci.matrixPower(base, n - 2);
        return 2 * res[0][0] + res[1][0];
    }

    public  static void main(String[] args) {
        System.out.println(sByMatrix(5));
    }
}
