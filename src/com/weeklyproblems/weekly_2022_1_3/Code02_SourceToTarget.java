package com.weeklyproblems.weekly_2022_1_3;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: zhaomengyi
 * @Date: 2024/3/11 15:17
 * @Description:leecode1018,通过数据里猜解法
 */
public class Code02_SourceToTarget {
    //如果有2个x,围住2个，有n个格子，最多可以围住200 * 194 / 2个格子，如果在遍历19900次表示x至少没有被围住，从t出发再去找s，如果超过19900次，
    //格子被围住到不了了
    //（1）bfs找到T true，（2）没有找到t，并且走过的格子数不够19900=>表示被围住了！ false（3）没找到t，但是步数到了19901，true，这些个点围不住这个点
    //T bfs找s，和上面一样，只有两个BFS都为True才成功到达

    public static long offset = 1000000; //使用这个值帮助存储block点和visited点，缩小存储量

    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        int n = blocked.length;
        int maxPoints = n * (n - 1) / 2; //n个x不能通过的点，最多可以包裹多少点
        HashSet<Long> blockSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            blockSet.add((long) blocked[i][0] * offset + blocked[i][1]);
        }
        return bfs(source[0], source[1], target[0], target[1], maxPoints, blockSet)
                && bfs(target[0], target[1], source[0], source[1], maxPoints, blockSet);
    }

    public static boolean bfs(int fromX, int fromY, int toX, int toY, int maxPoints, HashSet<Long> blockSet) {
        HashSet<Long> visited = new HashSet<>();
        Queue<Long> queue = new LinkedList<>();
        visited.add((long) fromX * offset + fromY);
        queue.add((long) fromX * offset + fromY);
        while (!queue.isEmpty() && (visited.size() <= maxPoints)) { //这里剪枝，如果访问点小于要不就到达了，要不就没有到达
            long cur = queue.poll();
            long curX = cur / offset;
            long curY = cur - curX * offset;
            if (findAndAdd(curX - 1, curY, toX, toY, blockSet, visited, queue) ||
                    findAndAdd(curX + 1, curY, toX, toY, blockSet, visited, queue) ||
                    findAndAdd(curX, curY - 1, toX, toY, blockSet, visited, queue) ||
                    findAndAdd(curX, curY + 1, toX, toY, blockSet, visited, queue)
            ) {
                return true;
            }
        }
        return visited.size() > maxPoints; //如果访问过的点大于可以被阻止最多的点也可以被访问到，这个条件是这个题比较特殊的地方

    }

    //来到的点（row，col）,寻找toX,toY；blockset放着不能走的点；queue帮助bfs；如果这个点是Visitd就不要放入queue
    public static boolean findAndAdd(long row, long col, int toX, int toY, HashSet<Long> blockSet, HashSet<Long> visited, Queue<Long> queue) {
        if (row < 0 || row == offset || col < 0 || col == offset) {
            return false;
        }
        if (row == toX && col == toY) {
            return true; //到达这个点
        }
        long value = row * offset + col;
        if (!blockSet.contains(value) && !visited.contains(value)) { //没有访问过，不是block点
            visited.add(value);
            queue.add(value);
        }
        return false;
    }
}



