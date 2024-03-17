package com.weeklyproblems.weekly_2022_1_3;

import java.util.PriorityQueue;

/**
 * @Author: zhaomengyi
 * @Date: 2024/3/11 13:40
 * @Description:A*算法，游戏中常用，Dijstra算法的延伸，如果图周围都是1的话，那么DJ算法缺乏引导性
 */
public class Code01_AStar {
    // 1 1 1 1 1
    // 1 1 1 1 1
    // 1 1 1 1 1
    // 1 1 1 1 1
    // 1 1 1 1 1

    //map[i][j]=0代表障碍, >0代表同行代价
    //A*方法计算最短距离（1）进行一个到目标点的预估距离[a,1,3],从a到最终点的代价是1，自己的代价是1，预估到target还需要走3步
    //(2)使用曼哈顿距离做预估，最好的距离即可，预估走几次，不需要考虑到每个点上的代价
    //过程(1)c,2,2|b,2,4|d,2,4 (2)c,2,2出栈，e,3,3|g,3,3|f,3,1 入栈f,3,1|b,2,4|d,2,4|e,3,3|g,3,3
    //(3)代价 = f(s) + g(t),f(s)表示从起始点到当前点的代价，g(t)表示从当前点到目标点预估走多少步
// A*算法
    // map[i][j] == 0 代表障碍
    // map[i][j] > 0 代表通行代价
    public static int minDistance2(int[][] map, int startX, int startY, int targetX, int targetY) {
        if (map[startX][startY] == 0 || map[targetX][targetY] == 0) {
            return Integer.MAX_VALUE;
        }
        int n = map.length;
        int m = map[0].length;
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> (a[0] + a[1]) - (b[0] + b[1])); //综合代价
        boolean[][] closed = new boolean[n][m];
        //[真实距离，预估步数， 起始地点]
        heap.add(new int[] { map[startX][startY], distance(startX, startY, targetX, targetY), startX, startY });
        int ans = Integer.MAX_VALUE;
        while (!heap.isEmpty()) {
            int[] cur = heap.poll();
            int fromDistance = cur[0];
            int row = cur[2];
            int col = cur[3];
            if (closed[row][col]) {
                continue;
            }
            closed[row][col] = true;
            if (row == targetX && col == targetY) {
                ans = fromDistance;
                break;
            }
            add2(fromDistance, row - 1, col, targetX, targetY, n, m, map, closed, heap);
            add2(fromDistance, row + 1, col, targetX, targetY, n, m, map, closed, heap);
            add2(fromDistance, row, col - 1, targetX, targetY, n, m, map, closed, heap);
            add2(fromDistance, row, col + 1, targetX, targetY, n, m, map, closed, heap);
        }
        return ans;
    }

    public static void add2(int pre, int row, int col, int targetX, int targetY, int n, int m, int[][] map,
                            boolean[][] closed, PriorityQueue<int[]> heap) {
        if (row >= 0 && row < n && col >= 0 && col < m && map[row][col] != 0 && !closed[row][col]) {
            heap.add(new int[] { pre + map[row][col], distance(row, col, targetX, targetY), row, col });
        }

    }

    // 曼哈顿距离
    public static int distance(int curX, int curY, int targetX, int targetY) {
        return Math.abs(targetX - curX) + Math.abs(targetY - curY);
    }

}
