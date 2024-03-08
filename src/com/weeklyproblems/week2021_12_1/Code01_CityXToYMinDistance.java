package com.weeklyproblems.week2021_12_1;

import java.util.PriorityQueue;

/**
 * @Author: zhaomengyi
 * @Date: 2024/3/6 19:33
 * @Description:求城市x到y的最短距离,【借助小根堆priorityqueue通过BFS思想实现,本质上还是队列】
 */
public class Code01_CityXToYMinDistance {
    //在小根堆中不断弹出，小根堆可以放重复记录，如果原来记录大不弹出，省去加强堆的动态调整的步骤
    //描述，n座城市，城市编号是1到n，[1,3,9] 代表一条路，从1到3的距离是9，求x到y的最短路径
    public static int minDistance(int n, int[][] roads, int x, int y){
        int[][] map = new int[n + 1][n + 1];
        //本质就是一个BFS,利用小根堆不断到终点
        for(int i = 0; i <= n; i++){
            for(int j = 0; j <= n; j++){
                map[i][j] = Integer.MAX_VALUE;//每个点都不可达
            }
        }
        //这里输入可能会犯贱，所以每次拿最小
        for(int[] road : roads){
            map[road[0]][road[1]] = Math.min(map[road[0]][road[1]], road[2]);
            map[road[1]][road[0]] = Math.min(map[road[1]][road[0]], road[2]);
        }
        boolean[] computed = new boolean[n + 1];
        //距离小根堆
        PriorityQueue<Node> heap = new PriorityQueue<>((a, b) -> (a.pathSum - b.pathSum));
        heap.add(new Node(x, 0));
        while(!heap.isEmpty()){
            Node cur = heap.poll();//x到当前城市有距离
            if(computed[cur.city]){ //到这个城市结算过
                continue;
            }
            if(cur.city == y){
                return cur.pathSum; //如果是最终重点
            }
            computed[cur.city] = true;
            //进行BFS,题目描述城市从1开始
            for(int next = 1; next <= n; next++){
                if(next != cur.city && map[cur.city][next] != Integer.MAX_VALUE && !computed[next]){
                    heap.add(new Node(next, cur.pathSum + map[cur.city][next])); //到next点去，从cur点到next的距离
                }
            }
        }
        return Integer.MAX_VALUE;

    }
    //延伸，如果是求路径，在小根堆结构中加入上一步从哪来的点
    //自定义封装类，当前来到城市和，从出发源到该城市的距离
    public static class Node {
        public int city;
        public int pathSum;
        public Node(int c, int p){
            city = c;
            pathSum = p;
        }
    }
}
