package com.book.zuoshen.InterviewGuide.chpt4.recursionTodp;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/31 22:00
 * @Description:
 */
public class CoinMinZhangShuToAim {
    public int minCoins(int[] arr, int aim){
        if(arr == null || arr.length == 0 || aim < 0){
            return -1;
        }
        return process(arr, 0, aim);
    }

    /**
     *
     * @param arr
     * @param i 表示当前使用arr[i]面值
     * @param rest
     * @return 使用arr[i]面值时候，返回最少张数
     */
    public int process(int[] arr, int i, int rest){
        //表示已经没有可以考虑的面值了，如果此时剩余rest=0，返回0张；此时剩余rest≠0，返回-1,这个情况表示凑不齐
        if(i == arr.length){
            return rest == 0 ? 0 : -1;
        }

        int res = -1;
        //使用当前面值，不能超过rest
        for(int k = 0; k * arr[i] <= rest; k++){
            //使用k张arr[i],剩下rest - k * arr[i]
            int next = process(arr, i + 1, rest - k * arr[i]);
            //看看后续过程是否有效
            if(next != -1){ //这个方法是有效的
                res = res == -1 ? next + k : Math.min(next + k, res);
            }
        }
        return res;
    }

    //这么写其实不好理解，
    public int minCoinsDP(int[] arr, int aim){
        if(arr == null || arr.length == 0 || aim < 0){
            return -1;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];//N第i硬币，aim剩余金额
        //设置最后一排的值，最后一行表示的是已经把所有的硬币都选完了
        // 除了dp[N][0]为0，其他都是-1，表示到了第i张，还剩0元，说明成功了
        for(int col = 1; col <= aim; col++){
            dp[N][col] = -1;
        }
        //更少变量的算张数，不使用zhang变量
        for(int i = N - 1; i >= 0; i--){
            for(int rest = 0; rest <= aim; rest++){ //这里相当于探索张数
                dp[i][rest] = -1; //初始时设置dp[i][rest]为无效
                //下面的值如果有效
                if(dp[i + 1][rest] != -1){
                    dp[i][rest] = dp[i + 1][rest];//先设置成下面的值
                }
                //如果左边位置不越界且有效
                if(rest - arr[i] >= 0 && dp[i][rest - arr[i]] != -1){
                    if(dp[i][rest] == -1){//如果之前的值无效
                        dp[i][rest] = dp[i][rest - arr[i]] + 1;
                    } else { //说明下面和左边的值都有效，取最小的
                        dp[i][rest] = Math.min(dp[i][rest], dp[i][rest - arr[i]] + 1);
                    }
                }
            }
        }
        return dp[0][aim];
    }

    //根据泛化后某个点的值就等于上一张的结果dp[index+1][rest],或者从左边的dp[index][rest-arr[index]]+1过来
    public static int minCoinsSimple(int[] arr, int aim){
        if(arr == null || arr.length == 0 || aim < 0){
            return -1;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 0; //到N号纸币的时候凑0元，说明已经凑完了，N号纸币是不存在的，N张纸币的最大编号是N-1
        for(int j = 1; j <= aim; j++){
            dp[N][j] = Integer.MAX_VALUE;
        }
        for(int index = N - 1; index >= 0; index--){
            for(int rest = 0; rest <= aim; rest++){
                //1、待求点的下方
                dp[index][rest] = dp[index + 1][rest];
                //2、左边界 和 左边上一个位置的情况
                if(rest - arr[index] >= 0 && dp[index][rest - arr[index]] != Integer.MAX_VALUE){
                    dp[index][rest] = Math.min(dp[index][rest], dp[index][rest - arr[index]] + 1);
                }
            }
        }
        return dp[0][aim];
    }
}
