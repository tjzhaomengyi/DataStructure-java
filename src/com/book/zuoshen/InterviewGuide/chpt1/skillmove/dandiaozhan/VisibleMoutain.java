package com.book.zuoshen.InterviewGuide.chpt1.skillmove.dandiaozhan;

import com.mikemyzhao.SortList_ALL.Stack.bigshua.VisibleMoutains_22;

import java.util.Stack;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/13 10:21
 * @Description: 可见山峰问题，这道题在base班里讲过
 */
public class VisibleMoutain {
    //直接给结论：原则：智能从小的来找大的，不能让大的找小的，避免重复。
    // （1）如果不同高度的山峰，可见对数是2*n-3座，挑出最高的三座山峰，这三个里面最矮的肯定可以看到剩下两个大的，这样就组成了
    // 两对山峰，以此类推，2*(n - 2) + 1，最后两座山峰是一对。
    // （2）如果存在高度相同的山峰，使用一个单调栈，小根堆，最小的在最上面，记录山峰高度和出现次数，先遍历出来最高的山峰。
    // （3）然后遍历所有山峰，有比小根堆堆顶大的就对栈顶进行弹栈，并计算该弹栈高度所组成的山峰，
    //      a.如果该弹栈记录了次数是1，那么他能组成的山峰是2；
    //      b.如果该弹栈记录了次数大于1，那么他能组成的山峰是2 * k + C(2,k)【解释：每个出现的该高度和两边高度组成两对 + 自己内部】
    //(4)最后栈中最多有三对记录：
    //      a.对于倒数第三条记录：该条记录生成的山峰对数，k=1，生成对数=2；如果k>2，生成对数2 * k + C(2,k).
    //      b.对于倒数第二条记录：该条记录生成的山峰对数，这个要看最后一条记录(Y,M)，如果M=1，生成1 * k + C(2,k);如果M>1,生成
   //             2 * k + C(2,k)【倒数第二组的山峰智能看到最后一组的山峰两座】
    //      c.对于最后一条记录：k==1,0对，k>2,C(2,k)对

    public class Record{
        public int value;
        public int times;

        public Record(int value){
            this.value = value;
            this.times = 1;
        }
    }

    public int getVisibleNum(int[] arr){
        if(arr == null || arr.length < 2){
            return 0;
        }
        int ans = 0;
        int size = arr.length;
        int maxIndex = 0;
        for (int i = 0; i < size; i++){
            maxIndex = arr[i] > arr[maxIndex] ? i : maxIndex;
        }
        Stack<Record> stack = new Stack<Record>();
        //把最大的放入栈底
        stack.push(new Record(arr[maxIndex]));
        int index = nextIndex(maxIndex, size);
        //用小找大统计所有可见山峰对
        while(index != maxIndex){
            //满足进栈条件
            while (stack.peek().value < arr[index]){
                int k = stack.pop().times;
                //如果K==1产生两对，如果K>1,计算内部山峰对
                ans += 2 * k + getInnerPairs(k);
            }
            //如果当前栈中存在该arr[index]合并，否则加一个，并且根据单调栈这个值在顶上
            if(stack.peek().value == arr[index]){
                stack.peek().times++;
            } else {
                stack.push(new Record(arr[index]));
            }
            index = nextIndex(index, size);
        }

        //把stack中内容清除成最后三组
        while(stack.size() > 2){
            int times = stack.pop().times;
            ans += times * 2 + getInnerPairs(times);//因为正向可以看到下一个，反向可以看到栈底最高的
        }
        //完成倒数第二组
        if(stack.size() == 2){
            int times = stack.pop().times;
            //先往栈底看
            ans += getInnerPairs(times) + (stack.peek().times == 1 ? times : 2 * times);
        }
        ans += getInnerPairs(stack.pop().times);
        return ans;
    }

    public int nextIndex(int pos, int size){
        return (pos + 1) % size;
    }

    public int getInnerPairs(int k) {
        return k == 1 ? 0 : k * (k - 1) / 2;
    }


}
