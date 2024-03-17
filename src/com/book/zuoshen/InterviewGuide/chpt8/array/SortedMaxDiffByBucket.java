package com.book.zuoshen.InterviewGuide.chpt8.array;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/26 14:13
 * @Description:相邻数的最大差值
 */
public class SortedMaxDiffByBucket {
    public int maxGap(int[] arr){
        if(arr == null || arr.length < 2){
            return 0;
        }
        int len = arr.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < len; i++){
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
        }
        if(min == max){
            return 0;
        }
        boolean[] hasNum = new boolean[len + 1]; //把数放入1到len序号的桶中
        int[] maxs = new int[len + 1];
        int[] mins = new int[len + 1];
        int bid = 0;
        for(int i = 0; i < len; i++){
            bid = bucket(arr[i], len, min, max); //算出桶号
            mins[bid] = hasNum[bid] ? Math.min(mins[bid], arr[i]) : arr[i];
            maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], arr[i]) : arr[i];
            hasNum[bid] = true;
        }
        int res = 0;
        int lastMax = maxs[0];
        int i = 1;
        for(; i <= len; i++){ //如果存在大于跨区间的值那么他们应该存在于
            if(hasNum[i]){
                res = Math.max(res, mins[i] - lastMax);
                lastMax = maxs[i];
            }
        }
        return res;
    }

    //计算桶号：(num - min) * len / (max - min),推导公式 len / (max - min) = x / (num - min)
    public int bucket(long num, long len, long min, long max){
        return (int)((num - min) * len / (max - min));
    }
}
