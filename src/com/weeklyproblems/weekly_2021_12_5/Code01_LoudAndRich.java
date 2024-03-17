package com.weeklyproblems.weekly_2021_12_5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: zhaomengyi
 * @Date: 2024/3/8 09:20
 * @Description:声音大和富有，这个是一个拓扑排序问题,越安静值越高越富有，越吵闹越穷，找到最穷的，就是拓扑排序中最后一个
 */
public class Code01_LoudAndRich {
    //拓扑排序擦除入度为0的点，然后把安静值小的推到后面
    //rich[i]={a,b}表示a比b有钱，q
    public static int[] loudAndRich(int[][] richer, int[] quiet){
        int N = quiet.length;
        ArrayList<ArrayList<Integer>> nexts = new ArrayList<>();//构成每个点的图结构
        for(int i = 0; i < N; i++){
            nexts.add(new ArrayList<>());//nexts[0]={5,7,4}
        }
        int[] degree = new int[N];//每个点的入度统计
        for(int[] r: richer){
            nexts.get(r[0]).add(r[1]);//添加每个点
            degree[r[1]]++; //节点入度加1
        }
        Queue<Integer> zeroQueue = new LinkedList<>();
        for(int i = 0; i < N; i++){ //入度为0的点加入到队列中
            if(degree[i] == 0){
                zeroQueue.offer(i);
            }
        }
        //ans[i] = j，比i有钱的所有人中j最安静
        int[] ans = new int[N];
        for(int i = 0; i < N; i++){
            ans[i] = i; //最安静先设置成自己
        }
        while(!zeroQueue.isEmpty()){
            //入度为0的点出队列
            int cur = zeroQueue.poll();
            //(1)消除当前cur的影响
            for(int next : nexts.get(cur)){
                //cur:比cur有钱，并且更安静，更新ans[cur]
                if(quiet[ans[next]] > quiet[ans[cur]]){
                    ans[next] = ans[cur]; //这步就是把安静值往入度为0的点的后续节点进行传送
                }
                if(--degree[next] == 0){
                    zeroQueue.offer(next);//如果后面节点为0，赶紧扔进队列
                }
            }
        }
        return ans;
    }
}
