package com.weeklyproblems.week2021_11_4;

/**
 * @Author: zhaomengyi
 * @Date: 2024/3/6 14:04
 * @Description: 4到9这些数中，哪些二进制数有3个1，只有7是，返回1。思路：位运算递归
 */
public class BinaryOneEqualTargetCount {
    //long类型最多64，target不超过64，可以做到O(1)的时间复杂度，0≤start≤end，target≤64
    //思路：如果求170到3017，求0-3017减去0到169的个数，问题求0到x达标的有多少个
    //0到num上满足1的个数有多少个,num[h..index - 1]表示从h到index-1决定完了，需要保证之前做的决定不比num大
    //1)在index之前做的决定，已经小于num所对应的前缀状态了，2）之前做的决定等于num所对应的状态,index去做决定
    //上面这个两个条件如何理解，加入num的第三位是1，此时index位置如果是0那么后面随便做决定，如果此时index位是1，那么就要讨论一下了
    //less == 1,之前做的决定已经小于num所对应的前缀状态了，less =0之前做的决定等于num所对应的前缀状态
    //rest表示剩下还需要几个1来凑这个数
    //返回[index....]到最后有多少满足rest个1的决定个数
    public static long process2(int index, int less, int rest, long num){
        if(rest > index + 1){
            return 0; //剩下需要的1个个数大于剩下位数
        }
        if(rest == 0){
            return 1L; //找到一个决定
        }
        // 0 < rest <= index + 1,还有1需要消耗，并且位数够用
        if(less == 1){ // 之前做的决定已经小于num所对应的前缀状态了,0或者1随便填写
            if(rest == index + 1){
                return 1;
            } else {
                //后面剩余的位数 大于 需要消耗1的数量,某些位置填1, index做0的决定或者做1的决定，并且num必保证小
                return process2(index - 1, 1, rest - 1, num) +
                        process2(index - 1, 1, rest, num);
            }
        } else { //less=0,之前做的决定等于num所对应的前缀状态
            if(rest == index + 1) {//必须都填1才能消耗完
                // index
                //  1  11111
                //如果index位置是0，后面只要填1就傻逼，生成这个数比num大了，不符合条件，返回0个决定，废了！
                //num
                // 1
                //index位置也是1
                return (num & (1L << index)) == 0 ? 0 : process2(index - 1, 0,  rest - 1, num);
            } else {
                // 之前做的决定等于num对应的前缀状态
                //后面剩余的位数 大于消耗掉1的数量
                if( (num & (1L << index)) == 0){ //index位置是0，这一位就不要用1了，走吧
                    return process2(index - 1, 0, rest, num);
                } else { //num当前index是1,如果用1剩下的还是相等的，如果用0后续lees=1，比当前的num要小了
                    return process2(index - 1, 0, rest - 1, num) + process2(index - 1, 1, rest, num);

                }
            }
        }
    }

    public static long nums2(long start, long end, int target){

        //寻找end这数，最高位的1在哪
        int ehigh = 62;
        while((end &(1L << ehigh)) == 0){
            ehigh--;
        }
        if(start == 0){
            return process2(ehigh, 0, target, end);
        } else { //170~3657 0~169 0~3657
            start--;
            int shigh = 62;
            while(shigh >= 0 && (start & (1L << shigh)) == 0){
                shigh--;
            }
            return process2(ehigh, 0, target, end) - process2(shigh, 0, target, start);
        }
    }

    //index 63种变化，62-0； less：两种变化，rest64种变化,表大小63 * 2 * 64
    //使用缓存的方法dp[index][less][rest],如果dp=-1表示没有算过
    public static long process3(int index, int less, int rest, long num, long[][][] dp){
        if(rest > index + 1){
            return 0;
        }
        if(rest == 0){
            return 1L;
        }
        if(dp[index][less][rest] != -1){
            return dp[index][less][rest];
        }
        long ans = 0;
        if(less == 1){
            if(rest == index + 1){
                ans = 1;
            } else {
                ans = process3(index - 1, 1 , rest - 1, num, dp) +
                        process3(index - 1, 1, rest, num, dp);
            }
        } else {
            if(rest == index + 1){
                ans = (num & (1L << index)) == 0 ? 0 : process3(index - 1,0 ,rest - 1, num, dp);
            } else {
                if((num & (1L << index)) == 0){
                    ans = process3(index - 1, 0, rest, num, dp);
                } else {
                    ans = process3(index - 1, 0, rest - 1, num, dp) +
                            process3(index - 1, 1, rest, num, dp);
                }
            }
        }
        dp[index][less][rest] = ans;
        return ans;
    }
}
