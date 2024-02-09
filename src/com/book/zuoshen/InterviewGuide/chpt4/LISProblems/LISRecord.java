package com.book.zuoshen.InterviewGuide.chpt4.LISProblems;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/4 17:14
 * @Description:这个最长递增子序列，可以找出最后的结果
 */
public class LISRecord {
    //使用ends区记录当前有效的最长递增子序列，如果当前遍历的值比这个ends的最大值大，就加入；如果比ends最后值小，就把ENDS里面的对应值和后面的删除，然后
    //添加这个值，dp表示到当前值的最长递增子序列长度.
    public int[] getLISdp(int[] arr){
        int[] dp = new int[arr.length]; //以arr[i]结尾最长子序列有多长
        // ends有效区是有序的
        int[] ends = new int[arr.length]; // 记录最长递增子序列的有效区。end[i]表示当前遍历到i，所有长度为i+1子数组的递增子序列中的最小结尾【这个值不断更新】。
        ends[0] = arr[0];
        dp[0] = 1;
        int right = 0;//ends有效区的最右位置，从0到right的区域是有效区，right往右无效
        int l = 0;
        int r = 0;
        int m = 0;
        for(int i = 1; i < arr.length; i++){
            l = 0;
            r = right;
            while(l <= r){
                m = (l + r) / 2;
                if(arr[i] > ends[m]){
                    l = m + 1; //新来的数字比ends数组最大值可能还大
                } else {
                    r = m - 1;
                }
            }
            //技巧：l是找到的位置，如果在上面二分查找中没有找到一定会来到l+1位置！！！表示有效区没找到，更新有效区
            right = Math.max(right, l);//如果更新有效区，l会比right更大执行更新，否则，如果找到内部区域维持原来的right区
            ends[l] = arr[i]; //递推过程看大厂刷题即可，这个改写ends数组的过程并通过l+1得到长度正好是一个巧劲。更新长度为i+1子数组的递增子序列的最小结尾
            dp[i] = l + 1; //这个改写ends数组的过程并通过l+1得到长度正好是一个巧劲，当前这个值的长度正好是l+1【和ends数组定义吻合】
        }
        return dp;
    }
}
