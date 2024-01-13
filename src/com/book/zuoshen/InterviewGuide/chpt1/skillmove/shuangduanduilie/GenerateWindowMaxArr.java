package com.book.zuoshen.InterviewGuide.chpt1.stack;

import java.util.LinkedList;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/11 16:26
 * @Description:生成窗口最大值数组，这种题真的就是想出来的时候会就会，不会也不太好想这个时间复杂度。
 */
public class GenerateWindowMaxArr {
    //窗口大小为w，数组长度为N，要求时间复杂度为O(N)
    //结论：使用双端队列(qmax)实现窗口最大值的更新,原则：qmax存放遍历的下标，如果qmax是空的，直接放入当前位置i。如果qmax不是空的，
    // qmax队尾的位置代表的值如果不比当前的值，将一直弹出队尾的位置，直到qmax队尾的位置所代表的值比当前值大，当前的位置才放入qmax队尾。
    // 【评价】这个思路比较奇葩，就是小的正常往里放，因为它有可能是后面大的，但是如果新的值比qmax的最后一个值大，那么新的值就把老的值替换掉

    public int[] getMaxWindow(int[] arr, int w){
        if (arr == null || w < 1 || arr.length < w){
            return null;
        }
        LinkedList<Integer> qmax = new LinkedList<Integer>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for(int i = 0; i < arr.length; i++){
            while(!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]){
                qmax.pollLast();
            }
            qmax.addLast(i);
            if(qmax.peekFirst() == i - w){
                qmax.pollFirst();//只有这动qmax辅助数据第一个值
            }
            if(i >= w - 1){ //边走边往结果填充，然后把qmax辅助的队头放入
                res[index++] = arr[qmax.peekFirst()];
            }
        }
        return res;
    }

}