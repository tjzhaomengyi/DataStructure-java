package com.book.zuoshen.InterviewGuide.chpt1.skillmove.shuangduanduilie;


import java.util.LinkedList;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/12 15:18
 * @Description: 【微软Code02_AllLessNumSubArray】一个数组和一个值，保证max[arr(i..j)] - min[arr(i..j)] <= num
 */
public class MaxSubMinLessThanNum {
    //结论：（1）如果max[arr(i..j)] - min[arr(i..j)] <= num，那么从i≤k≤l≤j中（k...l）每个子集肯定也符合这个这个条件.因为(k..l)这区间的
    // 最大值肯定比i到j的还小，(k..l)区间的最小值比i到j的最小值还大
    // (2)如果max[arr(i..j)] - min[arr(i..j)]不满足条件，那么从i开始的子集也不满足这个条件
    // 使用两个单调队列处理这个问题：如果i到j的序列满足这个条件，那么把子集加进去res += j - i + 1。
    // 然后把i向右边移动计算新的位置符合条件的个数
    public static int getNum(int[] arr, int num){
        if (arr == null || arr.length == 0 || num < 0){
            return 0;
        }

        LinkedList<Integer> qmax = new LinkedList<Integer>();
        LinkedList<Integer> qmin = new LinkedList<Integer>();
        int i = 0;
        int j = 0;
        int res = 0;
        while(i < arr.length){
            while(j < arr.length){


//  链接：https://www.nowcoder.com/questionTerminal/5fe02eb175974e18b9a546812a17428e
//                来源：牛客网：为了保证同一个下标只进栈一次，出栈一次，这样时间复杂度才能保证(每个元素O(1)，n个元素O(n))
                //如果break,j不变，而qmin.peekLast()正好是上一轮的j，后面i++，所以判断[i+1..j]是否满足条件
                //到j不满足条件，所以[i+1..j]不一定满足条件
                if(qmin.isEmpty() || qmin.peekLast() != j){ //注意：这个条件挺恶心，注意这里是算i到j的子数组，基础课讲的里面没有这个条件
                    /**
                     * 举例2\3\5\4,num=2，当i=0，j=2的时候，正好不满足条件，算出一组结果res=2-0，这个时候i滑动到i=1的时候，此时j还在j=2的时候，
                     * 所以就不用进入到这个条件中，因为上个时刻已经把这里面都处理了，两个辅助队列此时都满足单调的要求
                      */
                    while(!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[j]){
                        qmin.pollLast();
                    }
                    qmin.addLast(j);
                    while(!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[j]){
                        qmax.pollLast();
                    }
                    qmax.addLast(j);
                }
                if (arr[qmax.getFirst()] - arr[qmin.getFirst()] > num){
                    break;//不满足条件赶紧冲j的循环中跳出，计算当前结果
                }
                j++;
            }
            res += j - i;
            if(qmin.peekFirst() == i){
                qmin.pollFirst();
            }
            if(qmax.peekFirst() == i){
                qmax.pollFirst();
            }
            i++;
        }
        return res;
    }


    public static void main(String[] args) {
//        MaxSubMinLessThanNum maxmin = new MaxSubMinLessThanNum();
        int[] arr = new int[]{2,3,5,4,2,1};
        int res = getNum(arr, 2);
        System.out.println(res);
    }
}
