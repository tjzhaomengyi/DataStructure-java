package com.book.zuoshen.InterviewGuide.chpt8.Nice4InThisParts;

import java.util.LinkedList;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/26 19:42
 * @Description:小车加油问题，这个是最简单的解法，比之前的简单
 */
public class GasStattion {
    // 这个方法的时间复杂度O(N)，额外空间复杂度O(N)
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        boolean[] good = goodArray(gas, cost);
        for (int i = 0; i < gas.length; i++) {
            if (good[i]) {
                return i;
            }
        }
        return -1;
    }

    public static boolean[] goodArray(int[] g, int[] c) {
        int N = g.length;
        int M = N << 1;
        int[] arr = new int[M];
        for (int i = 0; i < N; i++) {
            arr[i] = g[i] - c[i];
            arr[i + N] = g[i] - c[i];
        }
        //求能量数组的前缀和
        for (int i = 1; i < M; i++) {
            arr[i] += arr[i - 1];
        }
        // 举个例子说明一下
        // 比如纯能数组(也就是燃料 - 距离之后)的数组 :
        // 纯能数组 = 3, 2,-6, 2, 3,-4, 6
        // 数组下标 = 0  1  2  3  4  5  6
        // 客观上说:
        // 0位置不是良好出发点
        // 1位置不是良好出发点
        // 2位置不是良好出发点
        // 3位置是良好出发点
        // 4位置不是良好出发点
        // 5位置不是良好出发点
        // 6位置是良好出发点
        // 把数组增倍之后 :
        // arr   = 3, 2,-6, 2, 3,-4, 6, 3, 2,-6, 2, 3,-4, 6
        // 然后计算前缀和 :
        // arr   = 3, 5,-1, 1, 4, 0, 6, 9,11, 5, 7,10, 6,12
        // index = 0  1  2  3  4  5  6  7  8  9 10 11 12 13
        // 这些就是上面发生的过程
        // 接下来生成长度为N的窗口
        LinkedList<Integer> w = new LinkedList<>();//构造滑动窗口中的最小值
        for (int i = 0; i < N; i++) {
            while (!w.isEmpty() && arr[w.peekLast()] >= arr[i]) {//如果w中最后的值比当前的大
                w.pollLast(); //把大的扔掉
            }
            w.addLast(i);
        }
        // 上面的过程，就是先遍历N个数，然后建立窗口
        // arr   =[3, 5,-1, 1, 4, 0, 6],9,11, 5, 7,10, 6,12
        // index = 0  1  2  3  4  5  6  7  8  9 10 11 12 13
        // w中的内容如下:
        // index:  2 5 6
        // value: -1 0 6
        // 左边是头，右边是尾，从左到右严格变大
        // 此时代表最原始的arr的这部分的数字:
        // 原始的值 = [3, 2,-6, 2, 3,-4, 6],3, 2,-6, 2, 3,-4, 6
        // 原始下标 =  0  1  2  3  4  5  6  0  1  2  3  4  5  6
        // 上面这个窗口中，累加和最薄弱的点，就是w中最左信息
        // 也就是会累加出，-1这个值，所以会走不下去。
        // 宣告了此时0位置不是良好出发点。
        // 接下来的代码，就是依次考察每个点是不是良好出发点。
        // 目前的信息是:
        // 计算的前缀和 :
        // arr   =[3, 5,-1, 1, 4, 0, 6],9,11, 5, 7,10, 6,12
        // index = 0  1  2  3  4  5  6  7  8  9 10 11 12 13
        // w中的内容如下:
        // index:  2 5 6
        // value: -1 0 6
        // 此时代表最原始的arr的这部分的数字:
        // 原始的值 = [3, 2,-6, 2, 3,-4, 6],3, 2,-6, 2, 3,-4, 6
        // 原始下标 =  0  1  2  3  4  5  6  0  1  2  3  4  5  6
        // 现在让窗口往下移动
        // 计算的前缀和 :
        // arr   = 3,[5,-1, 1, 4, 0, 6, 9],11, 5, 7,10, 6,12
        // index = 0  1  2  3  4  5  6  7   8  9 10 11 12 13
        // w中的内容如下:
        // index:  2 5 6 7
        // value: -1 0 6 9
        // 此时代表最原始的arr的这部分的数字:
        // 原始的值 =  3,[2,-6, 2, 3,-4, 6, 3],2,-6, 2, 3,-4, 6
        // 原始下标 =  0  1  2  3  4  5  6  0  1  2  3  4  5  6
        // 上面这个窗口中，累加和最薄弱的点，就是w中最左信息
        // 但是w最左的值是-1啊！而这个窗口中最薄弱的累加和是-4啊。
        // 对！所以最薄弱信息 = 窗口中的最左信息 - 窗口左侧刚出去的数(代码中的offset!)
        // 所以，最薄弱信息 = -1 - 0位置的3(窗口左侧刚出去的数) = -4
        // 看到了吗？最薄弱信息，依靠这种方式，加工出来了！
        // 宣告了此时1位置不是良好出发点。
        // 我们继续，让窗口往下移动
        // 计算的前缀和 :
        // arr   = 3, 5,[-1, 1, 4, 0, 6, 9,11], 5, 7,10, 6,12
        // index = 0  1   2  3  4  5  6  7  8   9 10 11 12 13
        // w中的内容如下:
        // index:  2  5  6  7  8
        // value: -1  0  6  9 11
        // 此时代表最原始的arr的这部分的数字:
        // 原始的值 =  3, 2,[-6, 2, 3,-4, 6, 3, 2],-6, 2, 3,-4, 6
        // 原始下标 =  0  1   2  3  4  5  6  0  1   2  3  4  5  6
        // 上面这个窗口中，累加和最薄弱的点，就是w中最左信息
        // 但是w最左的值是-1啊！而这个窗口中最薄弱的累加和是-6啊。
        // 对！所以最薄弱信息 = 窗口中的最左信息 - 窗口左侧刚出去的数(代码中的offset!)
        // 所以，最薄弱信息 = -1 - 1位置的5(窗口左侧刚出去的数) = -6
        // 看到了吗？最薄弱信息，依靠这种方式，加工出来了！
        // 宣告了此时2位置不是良好出发点。
        // 我们继续，让窗口往下移动
        // 计算的前缀和 :
        // arr   = 3, 5, -1,[1, 4, 0, 6, 9,11, 5], 7,10, 6,12
        // index = 0  1   2  3  4  5  6  7  8  9  10 11 12 13
        // w中的内容如下:
        // index:  5  9
        // value:  0  5
        // 没错，9位置的5进来，让6、7、8位置从w的尾部弹出了，
        // 同时原来在w中的2位置已经过期了，所以也弹出了，因为窗口左边界已经划过2位置了
        // 此时代表最原始的arr的这部分的数字:
        // 原始的值 =  3, 2, -6,[2, 3,-4, 6, 3, 2, -6],2, 3,-4, 6
        // 原始下标 =  0  1   2  3  4  5  6  0  1   2  3  4  5  6
        // 上面这个窗口中，累加和最薄弱的点，就是w中最左信息
        // 但是w最左的值是0啊！而这个窗口中最薄弱的累加和是1啊
        // 对！所以最薄弱信息 = 窗口中的最左信息 - 窗口左侧刚出去的数(代码中的offset!)
        // 所以，最薄弱信息 = 0 - 2位置的-1(窗口左侧刚出去的数) = 1
        // 看到了吗？最薄弱信息，依靠这种方式，加工出来了！
        // 宣告了此时3位置是良好出发点。
        // 往下同理
        boolean[] ans = new boolean[N];
        //下面这块是滑窗的操作,是个版子
        for (int offset = 0, i = 0, j = N; j < M; offset = arr[i++], j++) {
            if (arr[w.peekFirst()] - offset >= 0) { //窗口中最薄弱点
                ans[i] = true;
            }
            if (w.peekFirst() == i) { //往下移动了，如果薄弱队列中第一个位置正好是下一轮的前一个位置，把这个薄弱位置删除
                w.pollFirst();
            }
            //扩充薄弱的点，如果arr[j]小于w队列中最后一个递增点，删除掉原来的，扩充新的
            while (!w.isEmpty() && arr[w.peekLast()] >= arr[j]) {
                w.pollLast();
            }
            w.addLast(j);
        }
        return ans;
    }
}