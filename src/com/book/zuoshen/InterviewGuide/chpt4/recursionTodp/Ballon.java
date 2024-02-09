package com.book.zuoshen.InterviewGuide.chpt4.recursionTodp;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/4 14:37
 * @Description:打气球问题
 */
public class Ballon {
    //为了避免解题两侧条件的判断，直接在气球数组两侧 加两个1即可

    //递归含义：假设arr[L-1]没有打爆和arr[R+1]没有打爆,现在要打爆arr[L..R]上的所有气球，求最大分数
    public int process(int[] arr, int L, int R){
        if(L == R){
            return arr[L - 1] * arr[R + 1] * arr[L];
        }
        //最后打爆arr[L]或者最后打爆arr[R]的方案
        int max = Math.max(
                arr[L - 1] * arr[L] * arr[R + 1] * process(arr, L + 1, R),
                arr[L - 1] * arr[R] * arr[R + 1] * process(arr, L, R - 1)
                );
        //尝试L到R中间的气球i被最后打爆
        for(int i = L + 1; i < R; i++){
            max = Math.max(max, arr[L - 1] * arr[i] * arr[R + 1] + process(arr, L, i - 1) + process(arr, i + 1, R));
        }
        return max;
    }

    public int maxPointBallon(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }
        if(arr.length == 1){
            return arr[0];
        }
        int N = arr.length;
        int[] help = new int[N + 2];
        help[0] = 1;
        help[N + 1] = 1;
        for(int i = 0; i < N; i++){
            help[i + 1] = arr[i];
        }
        return process(help, 1, N);
    }

    //从上面的递归看到最终，位置是dp[1][N+1]位置，表示从整体气球最大分数，从i位置到j位置打气球的最大分数
    public static int maxPointBallonDP(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }
        if(arr.length == 1){
            return arr[0];
        }
        int N = arr.length;
        int[] help = new int[N + 2];
        help[0] = 1;
        help[N + 1] = 1;
        //对角线L=R的时候
        for(int i = 0; i < N; i++){
            help[i + 1] = arr[i];
        }
        int[][] dp = new int[N + 2][N + 2];//当前分数受到L和R前面的影响，已知的分数是对角线上的数,
        for(int i = 1; i <= N; i++){
            dp[i][i] = help[i - 1] * help[i] * help[i + 1];
        }
        //从下往上、从左往右填值，并且只需要填上半区
        for(int L = N; L >= 1; L--){
            for(int R = L + 1; R <= N; R++){
                //求解dp[L][R]表示从L到R上打爆所有气球的最大分数
                //最后打爆L的方案
                int finalL = help[L - 1] * help[L] * help[R + 1] + dp[L + 1][R];
                //最后打爆R的方案
                int finalR = help[L - 1] * help[R] * help[R + 1] + dp[L][R - 1];
                //两个取最大
                dp[L][R] = Math.max(finalL, finalR);
                //最后打中间的
                for(int i = L + 1; i < R; i++){
                    dp[L][R] = Math.max(dp[L][R], help[L - 1] * help[i] * help[R + 1] + dp[L][i - 1] + dp[i + 1][R]);
                }
            }
        }
        return dp[1][N];
    }

    public static void main(String[] args) {
        int[] balls = new int[]{3,1,5,8};
        System.out.println(maxPointBallonDP(balls));
    }
}
