package com.weeklyproblems.weekly_2021_12_5;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * @Author: zhaomengyi
 * @Date: 2024/3/8 11:49
 * @Description:工人看到任务标号，然后选择最小的来做，n个工人，m个任务，最少需要多少天，这道题需要两个队列，一个是任务队列，一个是工人队列，这两个配合计算往后推工作的天数
 * 【非常实用的一道题，可以用在实际工作中，并且可以用在任务天数可能不一样的任务】
 * depends[i]=[a,b],表示a任务依赖b任务的完成，所以排序的时候按照b从小到大
 */
public class Code02_TaskWorkerTopology {
    //2->5->6
    //      |
    //1->4->7
    //   |
    //0->3
    // 甲:0 3
    // 乙:1 5
    // 丙:2
    // 012 | 3 5 | 4 6 |7,3个人需要四天
    // 甲：0  2  4  6 7
    // 乙：1  3  5
    //两个人需要5天

    public static int days(int n, int m, int[][] depends){
        if(n < 1){
            return -1;
        }
        if(m <= 0){
            return 0;
        }
        ArrayList<ArrayList<Integer>> nexts = nexts(depends, m);
        int[] indegree = indegree(depends, m);
        //工人队列
        PriorityQueue<Integer> workers = new PriorityQueue<>();
        for(int i = 0; i < n; i++){
            workers.add(0);
        }
        //n个工人醒来，使用小根堆
        //工作队列,放入度为0的工作，并且按照标号小的从小到大排序
        PriorityQueue<Integer> zeroIn = new PriorityQueue<>();//zeroIn放着工作，就是那些入度为0的工作，标号小的任务先做，使用小根堆

        for(int i = 0; i < m; i++){
            if(indegree[i] == 0){
                zeroIn.add(i);
            }
        }
        //start数组表示每个任务可以的开始时间，和Code01安静问题那个quiet很像，start[i]：i之前必须完成的任务，占了几天，导致i任务只能从start[i]开始
        //start[i],i之前必须完成的任务占用了几天，导致i任务只能从start[i]天开始
        int[] start = new int[m];
        int finishAll = 0; //完成所有任务的最大天数
        int done = 0;
        while(!zeroIn.isEmpty()) {//表示有任务可以做
            int job = zeroIn.poll();
            int wake = workers.poll();
            //job什么时候完成？（工人醒来时间， 开工时间）这两个时间最晚的 +1
            int finish = Math.max(start[job], wake) + 1;  //finish一定要等到工人醒来或者工作到可以处理的时间才行，（工人醒来，开工时间）最晚的时间，然后这个工作+1，表示何时完成该工作
            finishAll = Math.max(finishAll, finish); //finishAll表示完成所有任务的最小天数
            done++; //done++后消除影响，把下级工作开始时间拱上去，并把后面的入度--，等于0的时候进入队列。

            //消除影响
            for(int next : nexts.get(job)){
                start[next] = Math.max(start[next], finish);
                if(--indegree[next] == 0){
                    zeroIn.add(next);
                }
            }
            workers.add(finish);    //工人完工后放入队列中
        }
        return done == m ? finishAll : -1;

    }



    //根据依赖生成nexts数组nexts[0]=[1,4]表示0到1有边，0到1有边
    public static ArrayList<ArrayList<Integer>> nexts(int[][] depends, int m){
        ArrayList<ArrayList<Integer>> nexts = new ArrayList<>();
        for(int i = 0;i < m; i++){
            nexts.add(new ArrayList<>());
        }
        for(int[] d : depends){
            nexts.get(d[1]).add(d[0]);
        }
        return nexts;
    }

    private static int[] indegree(int[][] depends, int m) {
        int[] indegree = new int[m];
        for(int[] d : depends){
            indegree[d[0]]++;
        }
        return indegree;
    }


    public static void main(String[] args) {
        // 2 -> 5 -> 6
        //           |
        //           v
        // 1 -> 4 -> 7
        //      ^
        //      |
        // 0 -> 3
        // 两个人
        // {1，2} 工人队列
        // 0 : 干0号工作 ，1
        // 0 : 干1号工作 ，1
        // 1 : 干2号工作，2
        int[][] d = {
                { 3, 0 },
                { 4, 1 },
                { 5, 2 },
                { 4, 3 },
                { 6, 5 },
                { 7, 4 },
                { 7, 6 }
        };
        System.out.println(days(3, 8, d));
        System.out.println(days(2, 8, d));
    }
}
