package com.book.zuoshen.InterviewGuide.chpt4.xor;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/7 14:34
 * @Description:【网易】找出最多的异或和为0的子数组个数，假设答案法
 */
public class MaxXORZeroSubArray {

    // 暴力递归：就很复杂了
    public static int comparator(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }
        int N = arr.length;
        int[] eor = new int[N];
        eor[0] = arr[0];
        for(int i = 1; i < N; i++){
            eor[i] = eor[i - 1] ^ arr[i];
        }
        return process(eor, 1, new ArrayList<>());
    }

    //数学结论：使用异或和前缀数组,xor(1..4)=xor(0)^xor(4),
    // index去决定index前一坨部分是否要结束，如果结束就把index放入到parts中，表示结束了，如果不结束就不放
    // parts放每个结束位置，比如parts中10是终止位置{3,6,8}的隔断，最后把10放进去，[0,3）[3,6),[6,8),[8,10)塞进去让这个结束，结束位置不包括这个位置的数！！！
    public static int process(int[] eor, int index, ArrayList<Integer> parts){
        int ans = 0;
        //来到最后位置，这里比较重要要理解
        if(index == eor.length){
            parts.add(eor.length); //parts加入最后越界位置
            ans = eorZeroParts(eor, parts);//在这种情况下有多少种异或和子数组
            //parts中10是终止位置{3,6,8}的隔断，最后把10放进去，[0,3）[3,6),[6,8),[8,10)塞进去让这个结束，返回一个不放10的分支，走不断掉的分支
            parts.remove(parts.size() - 1); //深度优先遍历，清除现场
        } else {//如果没到最后
            int p1 = process(eor, index + 1, parts);//情况1：前一部分不结束，index继续
            //情况2：前一部分结束到index这里断开！！！
            parts.add(index);
            int p2 = process(eor, index + 1, parts);
            parts.remove(parts.size() - 1);
            ans = Math.max(p1, p2);
        }
        return ans;
    }

    public static int eorZeroParts(int[] eor, ArrayList<Integer> parts){
        int L = 0;
        int ans = 0;
        for(int end : parts){
            //根据异或前缀和求L到end-1的异或和是否为0，eor[L-1]表示从0到L-1的异或和。如果L是从0开始，那么就是开始的第一段从0到end-1的异或和再异或上0就是结果
            if((eor[end - 1] ^ (L == 0 ? 0 : eor[L - 1])) == 0) { //判断L到end-1是否异或和为0,前面位置是否要加入到parts中进行断开
                ans++;
            }
            L = end; //L等于上一个end
        }
        return ans;
    }


    //dp解，假设答案的解法，dp[i]的含义数组到arr[i]最多可以分出几段异或和为0的子数组
    // 分别是两种情况：（1）arr[i]不能分出异或和为0的子数组 dp[i] = dp[i-1]
    // (2)dp[i]可以分出异或和为0的子数组，根据异或和的性质xor[j+1..i]如果等于0，那么xor[0...i] = xor[0..j] ^ xor[j+1...i],上一个异或和等xor[0..i]的位置在j
    public static int mostXOR(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }
        int N = arr.length;
        int[] dp = new int[N];

        //key某一个前缀异或和
        // value这个前缀异或和上次出现的最晚位置
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);//异或和0出现的最晚位置为-1

        int sum = 0;
        for(int i = 0; i < N; i++){
            sum ^= arr[i];
            if(map.containsKey(sum)){
                int pre = map.get(sum);
                dp[i] = pre == -1 ? 1 : (dp[pre] + 1);//情况2：获得前一个异或和为sum的最后位置，如果pre=-1，那么这里dp[i]就是第一段！
            }
            //剩下就是dp[i-1]不用当前这个位置组成的xor和为0最多的个数
            if(i > 0){
                dp[i] = Math.max(dp[i - 1], dp[i]);
            }
            map.put(sum, i);
        }
        return dp[N - 1];
    }

}
