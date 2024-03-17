package com.weeklyproblems.weekly_2022_1_3;


import java.util.LinkedList;

/**
 * @Author: zhaomengyi
 * @Date: 2024/3/11 17:09
 * @Description:Leetcode862
 */
public class Code03_SubArrayMoreThanK {
    //看到子数组，就想到"以i位置结尾怎么怎么样"
    //如果找某个子数组的累加和大于等于200，当前累加和是1000，那就找当前位置之前累加和是小于等于800的，且离当前位置最近
    //使用小根堆记录前面的前缀和，并且只记录小的前缀和，如果前缀和更小并且更靠右，就开始把前面的淘汰掉！并且可以利用单调结构，o(logn)找到这个值
    // 重新梳理流程:
    // 300  400  600   900    1400
    // 0-5  0-7  0-10  0-12  0-13
    //假设当前来到17位置，前缀和1000，目标大于等于200 6-17，8-17，11-17，11-17更新ans，然后更新0-17为1000
    //新的值从尾部入，前缀和比当前的前缀和大于等于的从尾部弹出 ，就是前面单调栈里面要保存小的原则
    public static int  shortSubarray(int[] arr, int K){
        int n = arr.length;
        long[] sum = new long[n + 1];
        for(int i = 0; i < n; i++){
            sum[i + 1] = sum[i] + arr[i];
        }
        int ans = Integer.MAX_VALUE;
        LinkedList<Integer> dq = new LinkedList();
        for(int i = 0; i < n + 1; i++){
            while(!dq.isEmpty() && sum[i] - sum[dq.getFirst()] >= K){
                ans = Math.min(ans, i - dq.pollFirst());
            }
            //把当前这个值放入，然后把小于的都删掉
            while(!dq.isEmpty() && sum[dq.getLast()] >= sum[i]){
                dq.pollLast();
            }
            dq.offerLast(i);
        }
        return ans != Integer.MAX_VALUE ? ans : -1;
    }
}
