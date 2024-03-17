package com.book.zuoshen.InterviewGuide.chpt9.math;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/28 18:16
 * @Description:调整[0,x)区间上的数出现的概率
 */
public class ChangeRandomRate {
    //变为x²的概率
    public double randomPower2() {
        return Math.max(Math.random(), Math.random());
    }
    //变为x^k次的概率
    public double randomPowerK(int k){
        if (k < 1){
            return 0;
        }
        double res = -1;
        for(int i = 0; i < k; i++){
            res = Math.max(res, Math.random());
        }
        return res;
    }

}
