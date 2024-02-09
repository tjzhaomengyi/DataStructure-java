package com.book.zuoshen.InterviewGuide.chpt4.recursionTodp;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/31 20:27
 * @Description:
 */
public class RobotToPosByNSteps {
    //递归方法

    /**
     * @param N  N位置为1到N，固定参数，
     * @param cur，当前在cur位置，可变参数
     * @param rest，还剩rest步数没走，可变参数
     * @param P，最终目标位置，固定参数
     * @return 从cur位置开始在1到N上移动rest步数，最终能到达P的方法数
     */
    public int walk(int N, int cur, int rest, int P){
        //如果没有剩余步数了，当前的cur位置就是最后的位置
        //如果最后的位置停在P上，那么之前做的动作都是有效的
        //如果最后的位置没在P上，那么之前做的动作都是无效的
        if(rest == 0){
            return cur == P ? 1 : 0;
        }

        //在最左侧的位置只能往右走
        if(cur == 1){
            return walk(N, 2, rest - 1, P);
        }
        //如果还有rest步要走，而当前在N位置上，那么当前这步只能从N走向N-1
        // 后续的过程就是来到N-1位置上，还剩rest - 1要走
        if(cur == N){
            return walk(N, N - 1, rest - 1, P);
        }

        //如果还有rest步要走，而当前的cur位置在中间位置，那么可以向左也可以向右走，方法数是向左和向右走的总和
        return walk(N, cur + 1, rest - 1, P) + walk(N, cur - 1, rest - 1, P);
    }

    public int countWays(int N, int M, int K, int P){
        //无效参数直接返回0
        if(N < 2 || K < 1 || M < 1 || M > N || P < 1 || P > N){
            return 0;
        }
        return walk(N, M, K, P);
    }

    //这种无后效性的递归，完全可以用动态规划进行改写，因为每周半截存储的记录，N和P都是不变的
    public int wayDP(int N, int M, int K, int P){
        if(N < 2 || K < 1 || M < 1 || M > N || P < 1 || P > N){
            return 0;
        }
        int[][] dp = new int[K + 1][N + 1];//K表示还剩多少步，N表示移动多少个位置，0位置不要
        dp[0][P] = 1;//剩下0步时候，当前位置是P，表示是一个有效走法
        //根据上面的递归得到下面的动态规划
        for(int i = 1; i <= K; i++){
            for(int j = 1; j <= N; j++){
                if(j == 1){
                    //最左侧的方法
                    dp[i][j] = dp[i - 1][2];
                } else if(j == N){
                    dp[i][j] = dp[i - 1][N - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j + 1];
                }
            }
        }
        return dp[K][M];//剩下K步，起始位置在M的时候
    }

}
