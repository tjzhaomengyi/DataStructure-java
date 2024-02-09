package com.book.zuoshen.InterviewGuide.chpt4.hanoita;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/5 15:20
 * @Description: [解释搜hanoiStep_18]判断汉诺塔给出的数组步骤是否是汉诺塔最优解的某一个步，如果是给出这是第几步，不是的话返回-1
 */
public class JudgeHanoitaStep {
    public int step(int[] arr){
        if(arr == null){
            return -1;
        }
        return process(arr, arr.length - 1, 1, 2, 3); //把这些节点全部从左边移动到右边的时候，当前步骤是第几步
    }

    public int process(int[] arr, int index, int from, int other, int to){
        if(index == -1){
            return 0;
        }
        //当前这批节点的最后一个节点在other上，这样肯定是不对的！！只能在from或者是to上
        if(arr[index] == other){
            return -1;
        }
        //index节点在最左边，说明前面0...i-1盘子还没有完成，看看那些到了多少步
        if(arr[index] == from){
            return process(arr, index - 1, from, to, other);
        } else {//index在最右边，说明前面的0...i-1的盘子已经移动好了
            int p1 = (1 << index) - 1; //已经移动好的步骤
            int p2 = 1; //index自己移动的这步
            int p3= process(arr, index - 1, other, from, to);
            //注意：这里p3是个坑，可能有错的
            if(p3 == -1){
                return -1;
            }
            return p1 + p2 + p3;
        }
    }

    //修改为dp,没啥意义
    public int stepDP(int[] arr){
        if(arr == null || arr.length == 0){
            return -1;
        }
        int from = 1;
        int mid = 2;
        int to = 3;
        int i = arr.length - 1;
        int res = 0;
        int tmp = 0;
        while(i >= 0){
            if(arr[i] == mid){
                return -1;
            }
            if(arr[i] == to){
                res += 1 << i;
                tmp = from;
                from = mid;
            } else {
                tmp = to;
                to = mid;
            }
            mid = tmp;
            i--;
        }
        return res;
    }
}
