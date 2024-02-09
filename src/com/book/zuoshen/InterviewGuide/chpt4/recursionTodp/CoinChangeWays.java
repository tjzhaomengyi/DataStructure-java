package com.book.zuoshen.InterviewGuide.chpt4.recursionTodp;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/2 10:46
 * @Description:
 */
public class CoinChangeWays {
    public int coins(int[] arr, int aim){
        if(arr == null || arr.length == 0 || aim < 0){
            return 0;
        }
        return process1(arr, 0, aim);
    }

    public int process1(int[] arr, int index, int aim){
        int res = 0;
        if(index == arr.length){
            res = aim == 0 ? 1 : 0;
        } else{
            for(int i = 0; arr[index] * i <= aim; i++){
                res += process1(arr, index + 1, aim - arr[index] * i);
            }
        }
        return res;
    }

    //缓存方式，上面的方法有好多重复的过程，通过一个map进行记录，map[i][j]=0表示(i,j)情况没有计算过，map[i][j]=-1表示计算过返回0；
    public int coins2(int[] arr, int aim){
        if(arr == null || arr.length == 0 || aim < 0){
            return 0;
        }
        int[][] map = new int[arr.length + 1][aim + 1];
        return process2(arr,0, aim, map);
    }

    public int process2(int[] arr, int index, int aim, int[][] map){
        int res = 0;
        if (index == arr.length){
            res = aim == 0 ? 1 : 0;
        } else {
            int mapValue = 0;
            for (int i = 0; arr[index] * i <= aim; i++){
                mapValue = map[index + 1][aim - arr[index] * i];//上一个位置是否计算过
                if (mapValue != 0){
                    res += mapValue == -1 ? 0 : mapValue; //上一个位置能不能凑出来， 可以凑出来的话 res
                } else { //上一个位置没有计算过
                    res += process2(arr, index + 1, aim - arr[index] * i, map);
                }
            }
        }
        map[index][aim] = res == 0 ? -1 : res;
        return res;
    }

    //进入dp方法
    public int coinDP1(int[] arr, int aim){
        if(arr == null || arr.length == 0 || aim < 0){
            return 0;
        }
        int[][] dp = new int[arr.length][aim + 1];//表示从0到第i个硬币，有多少种方法可以凑出aim，最终返回结果dp[arr.length-1][m]
        for (int i = 0; i < arr.length; i++){
            dp[i][0] = 1; //从0到i的硬币凑0元，直接就是一种方法
        }
        //只用一张来凑aim
        for(int j = 1; arr[0] * j <= aim; j++){
            dp[0][arr[0] * j] = 1;
        }
        int num = 0;
        for(int i = 1; i < arr.length; i++){
            for(int j = 1; j <= aim; j++){
                num = 0;
                for(int k = 0; j - arr[i] * k >= 0; k++){
                    num += dp[i - 1][j - arr[i] * k];
                }
                dp[i][j] = num;
            }
        }
        return dp[arr.length - 1][aim];
    }

    //技巧:优化枚举行为的技巧就是把k里面的arr[i]*k的枚举优化为dp[i][j-arr[i]]
    public int coin4(int[] arr, int aim){
        if(arr == null || arr.length == 0 || aim < 0){
            return 0;
        }
        int[][] dp = new int[arr.length][aim + 1];
        for(int i = 0; i < arr.length; i++){
            dp[i][0] = 1;
        }
        for(int j = 1; arr[0] * j <= aim; j++){
            dp[0][j * arr[0]] = 1;
        }
        for(int i = 1; i < arr.length; i++){
            for(int j = 1; j <= aim; j++){
                dp[i][j] = dp[i - 1][j]; //上一张纸币能凑j的方法数
                dp[i][j] +=  j - arr[i] >= 0 ? dp[i][j - arr[i]] : 0; //当前纸币能凑j-arr[i]的方法数，把k的for循环枚举优化掉了
            }
        }
        return dp[arr.length - 1][aim];
    }
}
