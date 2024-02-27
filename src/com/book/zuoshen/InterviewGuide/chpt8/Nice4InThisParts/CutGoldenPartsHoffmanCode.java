package com.book.zuoshen.InterviewGuide.chpt8.Nice4InThisParts;

import java.util.PriorityQueue;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/26 16:13
 * @Description:哈夫曼编码的转型
 */
public class CutGoldenPartsHoffmanCode {
    public int getMinCostSplit(int[] arr){
        if(arr == null || arr.length < 2){
            return 0;
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        //把数字全放入小根堆中
        for(int i = 0; i < arr.length; i++){
            minHeap.add(arr[i]);
        }
        int ans = 0;
        //小根堆不断合并，得到最小的分割代价
        while(minHeap.size() != 1){
            int sum = minHeap.poll() + minHeap.poll();
            ans += sum;
            minHeap.add(sum);
        }
        return ans;
    }
}
