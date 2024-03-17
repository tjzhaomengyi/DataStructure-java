package com.book.zuoshen.InterviewGuide.chpt4.dp;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/9 12:32
 * @Description:
 */
public class JumpGame {
    public int jump(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }
        int[] dp = new int[arr.length]; //到i位置跳了多少步
        //初始化DP每一步都是最大尝试跳跃次数
        for(int i = 1; i < arr.length; i++){
            dp[i] = arr.length;
        }
        dp[0] = 0;
        for(int i = 1; i < arr.length; i++){
            for(int j = 0; j < i; j++){//因为从i前面的每个j都可能跳到j上
                if(j + arr[j] >= i){
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[arr.length - 1];
    }

    //利用多变量
    public int jumpbetter(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }
        int step = 0;//当前跳了多少步
        int cur = 0; // 当前这些步可以覆盖到那个index；
        int next = 0; // 多跳一步最远可以到哪里
        for(int i = 0; i < arr.length; i++){
            if(cur < i){ //当前覆盖不到了
                step++; //
                cur = next;
            }

            next = Math.max(next, i + arr[i]);//当前是i，和i之前的进行对比，多跳一步最远到哪
        }
        return step;
    }
}
