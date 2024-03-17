package com.book.zuoshen.InterviewGuide.chpt8.matrix;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/26 12:54
 * @Description:在01矩阵中找到由1连接起来的从左上到右下的通路
 */
public class MinRoadBy1 {
    //BFS,
    public int minPathValue(int[][] m){
        if(m == null || m.length == 0 || m[0].length == 0
                || m[0][0] != 1 || m[m.length - 1][m[0].length - 1]  != 1) {
            return 0;
        }
        int res = 0;
        int[][] map = new int[m.length][m[0].length]; //记录走过的点的距离
        map[0][0] = 1;
        Queue<Integer> rQ = new LinkedList<>();
        Queue<Integer> cQ = new LinkedList<>();
        rQ.add(0);
        cQ.add(0);
        int r = 0;
        int c = 0;
        while(!rQ.isEmpty()){
            r = rQ.poll();
            c = cQ.poll();
            if(r == m.length - 1 && c == m[0].length - 1){
                return map[r][c];
            }
            walkTo(map[r][c], r -1, c, m, map, rQ, cQ);
            walkTo(map[r][c], r +1, c, m, map, rQ, cQ);
            walkTo(map[r][c], r, c - 1, m, map, rQ, cQ);
            walkTo(map[r][c], r, c + 1, m, map, rQ, cQ);
        }
        return res;
    }

    public void walkTo(int pre, int toR, int toC, int[][] m, int[][] map, Queue<Integer> rQ, Queue<Integer> cQ){
        if(toR < 0 || toR == m.length || toC < 0 || toC == m[0].length ||
            m[toC][toR] != 1 || map[toC][toR] != 1){
            return;
        }
        map[toR][toC] = pre + 1;
        rQ.add(toR);
        cQ.add(toC);
    }
}
