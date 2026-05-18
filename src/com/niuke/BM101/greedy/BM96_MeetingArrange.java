package com.niuke.BM101.greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

public class BM96_MeetingArrange {
    //主持人安排 等同于 会议安排问题
    public int meetingcount(int n, int[][] arr){
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);
        //小根堆存储结束时间
        PriorityQueue<Integer> tmp = new PriorityQueue<>();
        int max = 0; //在tmp调整的时候，大小会变，可能会在某一个时刻出现最大量的情况
        for(int i = 0; i < arr.length; i++){
           if(!tmp.isEmpty() && arr[i][0] > tmp.peek()){
               tmp.poll();
           }
           tmp.offer(arr[i][1]);
           max = Math.max(max, tmp.size());

        }
        return max;
    }
}
