package com.niuke.BM101.slidewindow;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 滑动窗口写法原则：
 * 1、创建双端队列qmax，队首记录当前窗口的最大值的下标 和 队尾记录“潜在”最大值的下标
 * 2、如何操作这个qmax双端队列
 * 原则一： 入队规则：保持单调递减
 *     如果队尾元素小于当前遍历到的元素，那么把队尾元素出队。当前元素入队。
 *     为什么要移除队尾的小元素？
 *     因为这些小元素：（1）比新元素小，在当前窗口中不可能是最大值；
 *      （2）比新元素早过期（下标更小），当新元素还在窗口时它们一定已经过期
 *      留着这些旧的小元素，只会判断干扰，并且它们永远不会成为窗口最大值。
 * 原则二：出队规则:移除过期元素
 *     什么时候过期，当前窗口范围：[R-w+1, R]
 *     如果队首下标qmax.peekFirst()等用户R-w，说明它正好是窗口左边界的前一个元素就，
 *     此时它已经不在当前窗口内，必须移除
 * 原则三：记录规则：收集结果
 *      当窗口完整时记录最大值，窗口完整条件：R > w -1 右边界足够形成完整窗口
 *      此时队首一定是当前窗口的最大值，因为队列递减。
 *      思考：这里可能不太好像，因为R在循环中一直往右去推，所以左边卡住窗口大小就行，因为
 *      不在窗口内的下标已经在 “原则二”中被移除了，所以qmax自动把这个窗口给维护好了，不用担心左边界问题。
 *
 */
public class M45_SlidingWindowMaxValue {
    public ArrayList<Integer> maxInWindows(int[] nums, int size){
        if(size > nums.length){
            return null;
        }
        ArrayList<Integer> res = new ArrayList<>();
        LinkedList<Integer> qmax = new LinkedList<>();
        for(int r = 0; r < nums.length; r++){
            // 原则1,如果qmax队尾的值小于当前遍历的值，让它出队
            while(!qmax.isEmpty() && nums[qmax.peekLast()] < nums[r]){
                qmax.pollLast();
            }
            qmax.offer(r); //当前遍历的下标入队
            // 原则2，删除过期的队头元素
            if(qmax.peekFirst() == r - size){
                qmax.pollFirst();
            }
            // 原则3，在满足窗口大小的时候每次记录窗口最大值，即qmax的队头元素的值
            if(r >= size - 1){
                res.add(nums[qmax.peekFirst()]);
            }
        }
        return res;
    }
}
