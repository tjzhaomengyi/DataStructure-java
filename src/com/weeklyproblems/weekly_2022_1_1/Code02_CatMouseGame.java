package com.weeklyproblems.weekly_2022_1_1;

import java.util.Arrays;

/**
 * @Author: zhaomengyi
 * @Date: 2024/3/10 09:34
 * @Description:
 */
public class Code02_CatMouseGame {
    //暴力尝试
    //返回值 0：平局、1：老鼠赢、2:猫赢
    //0:{3,7,9}用邻接表表示图
    //cat、mouse表示此时位置
    //turn谁的回合turn==1老鼠的回合，turn==0猫的回合
    // path：路径，当第一次出现，在老鼠的回合，猫在5位置，老鼠在7位置,path[cat][mouse][1] = false，表示是否出现过这个cat mouse组合位置
    // 不是第一次出现path[cat][mouse][1]=true
    public static int win(int[][] graph, int cat, int mouse, int turn, boolean[][][] path){
        if(path[cat][mouse][turn]){
            return 0;//不止一次出现
        }
        //下面是DFS方式，先把这个位置标记为来过，最后还要标记回false
        path[cat][mouse][turn] = true;//这个cat mouse的位置组合这次已经出现过了
        int ans = 0;
        if(cat == mouse){
            ans = 2;
        } else if(mouse == 0){
            ans = 1;
        } else {
            if(turn == 1){ //老鼠回合
                ans = 2; //最差的情况是猫赢
                for(int next : graph[mouse]){
                    int p = win(graph, cat, next, 0, path);//下一次是猫的回合，mouse走到next
                    ans = p == 1 ? 1 : (p == 0 ? 0 : ans);
                    if(ans == 1){ //老鼠走完赢了
                        break;
                    }
                }
            } else { //猫回合
                ans = 1;
                for(int next : graph[cat]){
                    if(next != 0){ //如果不是平局,猫不能走进洞
                        int p = win(graph, next, mouse, 1, path);
                        ans = p == 2 ? 2 : (p == 0 ? 0 : ans);
                        if(ans == 2){
                            break;
                        }
                    }
                }
            }
        }
        path[cat][mouse][turn] = false;
        return ans;
    }

    //优化暴力递归，path变化太多了，用简单的参数来表示状态
    // turn当前到了第几轮，在第turn轮，猫在cat位置，老鼠在mouse位置，返回最终谁赢
    // 猫可以到的位置有N-1个（不算0位置），老鼠可以到的位置有N个，所有猫鼠轮次是N * (N - 1)，同理可以得到
    // 鼠猫轮次的不同组合方式是N * (N - 1)个可能，所以总共可能就是2 * N * (N - 1),只有这些可能得组合，
    // 如果turn轮次超过2*N*(N-1)那么就是平局！！！
    // dp=-1表示这个状态之前没算过，dp=0平局，dp=1老鼠赢， dp=2猫赢
    //limit 是固定参数，轮数不要超过limit，如果超过limit必然是平局
    public static int process(int[][] graph, int limit, int cat, int mouse, int turn, int[][][] dp) {
        if (turn == limit) {
            return 0;
        }
        if (dp[cat][mouse][turn] != -1) {
            return dp[cat][mouse][turn];
        }
        int ans = 0;
        if (cat == mouse) {
            ans = 2;
        } else if (mouse == 0) {
            ans = 1;
        } else {
            if ((turn & 1) == 1) { // 老鼠回合
                ans = 2;
                for (int next : graph[mouse]) {
                    int p = process(graph, limit, cat, next, turn + 1, dp);
                    ans = p == 1 ? 1 : (p == 0 ? 0 : ans);
                    if (ans == 1) {
                        break;
                    }
                }
            } else { // 猫回合
                ans = 1;
                for (int next : graph[cat]) {
                    if (next != 0) {
                        int p = process(graph, limit, next, mouse, turn + 1, dp);
                        ans = p == 2 ? 2 : (p == 0 ? 0 : ans);
                        if (ans == 2) {
                            break;
                        }
                    }
                }
            }
        }
        dp[cat][mouse][turn] = ans;
        return ans;
    }

    // 不贪心，就递归 + 记忆化搜索
    public static int catMouseGame1(int[][] graph) {
        int n = graph.length;
        // 9 : 2 * 8 * 9 再大，平局
        int limit = ((n * (n - 1)) << 1) + 1;
        int[][][] dp = new int[n][n][limit];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        return process(graph, limit, 2, 1, 1, dp);
    }
}
