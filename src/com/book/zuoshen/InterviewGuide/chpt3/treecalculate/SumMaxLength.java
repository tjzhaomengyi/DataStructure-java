package com.book.zuoshen.InterviewGuide.chpt3.treecalculate;

import java.util.HashMap;

/**
 * @Author: zhaomengyi`
 * @Date: 2024/1/25 16:46
 * @Description:这是道前缀和的问题，根本题是chpt8的SumArrSumEqualNum
 */
public class SumMaxLength {
    public static class Node{
        private int value;
        private Node left;
        private Node right;

        public Node(int data){
            this.value = data;
        }
    }

    public static int getMaxLength(Node head, int sum){
        //key是前缀和的值，value是第一次出现该前缀和的层级
        HashMap<Integer, Integer> sumMap = new HashMap<Integer, Integer>();
        sumMap.put(0, 0);//重要，不遍历任何层级，前缀和为0
        return(preOrder(head, sum, 0, 1, 0, sumMap));

    }

    //使用递归的方式对问题进行求解
    public static int preOrder(Node head, int sum, int preSum, int level, int maxLen, HashMap<Integer, Integer> sumMap){
        if(head == null){
            return maxLen;
        }
        int curSum = preSum + head.value;//preSum每经过一层都要进行更新，都是new一个新Int值，所以不需要记录
        if(!sumMap.containsKey(curSum)){
            sumMap.put(curSum, level);
        }
        if(sumMap.containsKey(curSum - sum)){
            maxLen = Math.max(level - sumMap.get(curSum - sum), maxLen);
        }
        maxLen = preOrder(head.left, sum, curSum, level + 1, maxLen, sumMap);
        maxLen = preOrder(head.right, sum, curSum, level + 1, maxLen, sumMap);
        if(level == sumMap.get(curSum)){
            sumMap.remove(curSum);
        }
        return maxLen;
    }
}
