package com.book.zuoshen.InterviewGuide.chpt4.playcards;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/8 22:15
 * @Description:俩人从牌堆中选牌，看谁最大，以某一个人为视角得到递归win1()解法.然后使用两个DP表推出最终结果
 */
public class TakeBetterPoint {
    public int win1(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }
        //这人先拿或者后拿的最大分数是多少
        return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
    }

    //这人先拿
    public int f(int[] arr, int i, int j){
        if(i == j){
            return arr[i]; //剩下一张牌直接拿走
        }
        //拿走第i张，或者第j张后，面对后手的情况
        return Math.max(arr[i] + s(arr, i + 1, j), arr[j] + s(arr, i, j - 1));
    }
    //这人后拿
    public int s(int[] arr, int i, int j){
        if(i == j){ //剩下一张要等人家拿完，自己就没了
            return 0;
        }
        //这轮这人不得分，对面那人把i或者j拿走了，只能从剩下最小的里面再抽牌,
        return Math.min(f(arr, i, j - 1), f(arr, i + 1, j));
    }

    //从递归改写为dp
    public int winDP(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }
        int[][] f = new int[arr.length][arr.length]; //从i到j先拿牌的最高分数
        int[][] s = new int[arr.length][arr.length]; //从i到j后拿牌的最高分数
        for(int j = 0; j < arr.length; j++){
            f[j][j] = arr[j];
        }
        for(int j = arr.length - 1; j >= 0; j--){
            for(int i = j - 1; i >= 0; i--){
                f[i][j] = Math.max(arr[i] + s[i + 1][j], arr[j] + s[i][j - 1]);
                s[i][j] = Math.min(f[i + 1][j], s[i][j - 1]);
            }
        }
        return Math.max(f[0][arr.length - 1], s[0][arr.length - 1]);
    }

}
