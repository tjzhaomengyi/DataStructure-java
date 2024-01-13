package com.book.zuoshen.InterviewGuide.chpt1.skillmove.dandiaozhan;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/11 20:42
 * @Description: 找到每个位置数左右离着最近刚好比该位置值小的两个位置
 */
public class LeftRightCloseMin {
    //终极结论：单调栈辅助结构，栈内数据大小安排方法：准备一个栈，记为stack<Integer>，栈中放的元素是数组的位置，开始时stack为空。
    //(1)如果找到每一个i位置左边和右边离i位置最近且值比arr[i]小的值位置，那么让stack从栈顶到栈底的位置所代表的值是严格递减的。
    // (2)如果找到每一个i位置左边和右边离i位置最近且值比arr[i]大的值位置，那么让stack从栈顶到栈底的位置所代表的值严格递增的。
    // (3)操作过程，以找到最近比arr[i]小的为例，如果遍历的值arr[i]刚好小于栈顶元素，那么栈顶元素出栈，得到栈顶位置的答案：[次顶位置，当前进栈位置]，
    // 栈顶值重复上述过程，直到新的值，能够保证单调栈符合单调递减的序列，入栈。
    public int[][] getNearLessNoRepeat(int[] arr){
        int[][] res = new int[arr.length][2];
        Stack<Integer> stack = new Stack<Integer>();
        for(int i = 0; i < arr.length; i++){
            while(!stack.isEmpty() && arr[stack.peek()] > arr[i]){
                int popIndex = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
                res[popIndex][0] = leftLessIndex;
                res[popIndex][1] = i;
            }
            stack.push(i);
        }
        while(!stack.isEmpty()){
            int popIndex = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
            res[popIndex][0] = leftLessIndex;
            res[popIndex][1] = -1;
        }
        return res;
    }

    /**
     * 有重复值的原序列
     * @param arr
     * @return
     */
    public int[][] getNearLess(int[] arr){
        //如果有重复值在入栈的时候往上摞起来即可
        int[][] res = new int[arr.length][2];
        Stack<List<Integer>> stack = new Stack<>();//每个List存放相同值的位置，用空间换取时间每次向前遍历的时间
        for(int i = 0; i < arr.length; i++){
            while(!stack.isEmpty() && arr[i] < arr[stack.peek().get(0)]){
                List<Integer> popIs = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().get(0));
                for (int popi : popIs){
                    res[popi][0] = leftLessIndex;
                    res[popi][1] = i;
                }
            }
            //处理如何往当前的stack中加入当前的值
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]){
                stack.peek().add(Integer.valueOf(arr[i]));
            } else {
                ArrayList<Integer> list = new ArrayList<Integer>();
                list.add(arr[i]);
                stack.push(list);
            }
        }

        while(!stack.isEmpty()){
            List<Integer> popIs = stack.pop();
            int leftLessIndex = stack.empty() ? -1 : stack.peek().get(stack.peek().size()-1);
            for(int popI : popIs){
                res[popI][0] = leftLessIndex;
                res[popI][1] = -1;
            }
        }
        return res;
    }
}
