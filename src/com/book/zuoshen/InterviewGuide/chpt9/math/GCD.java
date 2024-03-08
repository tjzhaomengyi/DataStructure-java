package com.book.zuoshen.InterviewGuide.chpt9.math;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/28 13:12
 * @Description:最大公约数
 */
public class GCD {
    public static int gcd(int m, int n){
        return n == 0 ? m : gcd(n, m % n);
    }
    public static void main(String[] args) {
        System.out.print(gcd(24, 60));
    }
}
