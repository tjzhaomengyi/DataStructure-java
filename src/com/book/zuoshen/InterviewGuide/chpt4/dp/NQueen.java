package com.book.zuoshen.InterviewGuide.chpt4.dp;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/9 19:22
 * @Description:
 */
public class NQueen {
    public int num1(int n){
        if(n  < 1){
            return 0;
        }
        int[] record = new int[n]; //记录i行的queen放置的列号
        return process1(0, record, n);
    }

    //当前来到i行，当i行放上Queen后，所有列都要尝试，并且保证和之前走过的不打架
    //record[x]=y,表示在访问i行之前，x行的queen放在了y列，
    //返回从i行开始，返回多少方法数
    public int process1(int i, int[] record, int n){
        if(i == n){//所有行走完，返回整体一种方法，这个是所有递归的套路
            return 1;
        }
        int res = 0;
        //i行的皇后放哪里
        for(int j = 0; j < n; j++){
            if(isValid(record, i, j)){
                record[i] = j;
                res += process1(i + 1, record, n);
            }
        }
        return res;
    }

    //j只能与放在record[i]前面的位置进行验证
    public static boolean isValid(int[] record, int i, int j){
        for(int k = 0; k < i; k++){
            //斜线和直线上都不行
            if(j == record[k] || Math.abs(record[k] - j) == Math.abs(k - i)){
                return false;
            }
        }
        return true;
    }

}
