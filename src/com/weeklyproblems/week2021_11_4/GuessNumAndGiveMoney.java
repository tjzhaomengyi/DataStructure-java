package com.weeklyproblems.week2021_11_4;

/**
 * @Author: zhaomengyi
 * @Date: 2024/3/6 12:39
 * @Description：能够获胜的最小现金数，不管我选哪个数字【暴力递归到动态规划】
 */
public class GuessNumAndGiveMoney {
    //正确的数字，在1到n之间，每次猜错，花费是猜错的数字，返回永远倒霉的情况下，能赢得胜利，所需要的最少钱数
    public static  int minGold(int n){
        return zuo(1, n);
    }


    //目前锁定了，正确的数字在l到r上，除了这个范围都不可能了，返回，永远最倒霉的情况下，猜中这个数，需要的最少钱数
    public static int zuo(int L, int R){
        //猜中了
        if(L == R){
            return 0;
        }
        if(L == R - 1){ //L,R 3,4 猜3错了，剩两个数字永远猜小的，但是总倒霉，就是错了
            //只剩两个数字
            return L;
        }
        //L到R不止两个数
        int p1 = L + zuo(L + 1, R);//就猜L并且最倒霉
        int p2 = R + zuo(L, R - 1);
        int min = Math.min(p1, p2);//选最小的 ,可以自主选的时候选最小的
        //中间的数全尝试一遍
        for(int i = L + 1; i < R; i++){
            //i是猜的数，每个都试
            int left = zuo(L, i - 1); //如果往左走
            int right = zuo(i + 1, R); //往右走
            int cur = i + Math.max(left, right); //永远最倒霉就要承担大的;这个时候自己不能选，只能找最倒霉的情况
            min = Math.min(min, cur);//自己可以选，选最小情况
        }
        return min;

    }

    //将递归修改为动态规划
    public static int minGoldDP(int n){
        int[][] dp = new int[n + 1][n + 1];
        for(int i = 1; i < n; i++){
            dp[i][i + 1] = i;
        }
        for(int L = n - 2; L >= 1; L--){
            for(int R = L + 2; R <= n; R++){
                int min = Math.min(L + dp[L + 1][R], R + dp[L][R - 1]);
                for(int i = L + 1; i < R; i++){
                    min = Math.min(min, i + Math.max(dp[L][i - 1], dp[i + 1][R]));
                }
                dp[L][R] = min;
            }
        }
        return dp[1][n];
    }

}
