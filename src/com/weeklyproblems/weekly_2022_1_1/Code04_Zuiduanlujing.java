package com.weeklyproblems.weekly_2022_1_1;

import java.util.PriorityQueue;

/**
 * @Author: zhaomengyi
 * @Date: 2024/3/10 16:59
 * @Description:Dijtsra
 */
public class Code04_Zuiduanlujing {
    //每次把小根堆中最小的拿出来，然后弹出，直到找到目标点为止
    //小根堆里面是三维数组{dis, row, col},dis表示从0到当前为止的最小距离，距离谁小谁在上面，
    //并且一旦触发了这个位置，再遇到就不做处理，这里就用poped数组进行记录
    public static int bestWalk2(int[][] map){
        int n = map.length;
        int m = map[0].length;
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        heap.add(new int[] {map[0][0], 0, 0});
        boolean[][] poped = new boolean[n][m];
        int ans = 0;
        while(!heap.isEmpty()){
            int[] cur = heap.poll();
            int dis = cur[0];
            int row = cur[1];
            int col = cur[2];
            if(poped[row][col]){
                continue;
            }
            //处理这个位置
            poped[row][col] = true;
            if(row == n - 1 && col == m - 1){
                ans = dis; //找到重点
                break;
            }
            add(dis, row - 1, col, n, m, map, poped, heap); //前面位置
            add(dis, row + 1, col, n, m, map, poped, heap);
            add(dis, row, col - 1, n, m, map, poped, heap);
            add(dis, row, col + 1, n, m, map, poped, heap);
        }
        return ans;
    }

    public static void add(int pre, int row, int col, int n, int m, int[][] map, boolean[][] poped,
                           PriorityQueue<int[]> heap){
        if(row >= 0 && row < n && col >= 0 && col < m && !poped[row][col]){
            heap.add(new int[]{pre + map[row][col], row, col});
        }
    }
}
