package com.weeklyproblems.weekly_2022_1_2;

/**
 * @Author: zhaomengyi
 * @Date: 2024/3/11 11:59
 * @Description:// 来自美团
 * // 小美有一个长度为n的数组，为了使得这个数组的和尽量大，她向会魔法的小团进行求助
 * // 小团可以选择数组中至多两个不相交的子数组，并将区间里的数全都变为原来的10倍
 * // 小团想知道他的魔法最多可以帮助小美将数组的和变大到多少?
 */
public class Code04_MultiTenTwoSubarray {
    //解法思路用了两个DP，一个是从右往左的DP递推，推导出right数组，一个是从左往右的DP递推，结合right数组组成最终的结果
    //dp[i]:(1)arr[0..i]原始累加和，（2）dp[i-1] + arr[i]
    // (3)magic[i],magic[i]分成两种，一种只有arr[i]自己10倍，0...i-1没有10倍；10*arr[i]+magic[j-1],0..j-1和j都是10倍
    //: arr[0..i]范围上，可以没有10倍区域、或者有10倍区域但是最多有一个的情况下，
    //    最大累加和是多少？
    // 可能性1：就是没有10倍区域，那就是arr[0..i]的累加和, 这个好弄！
    //
    // 可能性2：有一个10倍区域
    //         a : arr[i]不在10倍区域里，但是之前可能有，那么就是dp[i-1] + arr[i]
    //
    //         b : arr[i]在10倍区域里
    //             甲：arr[0..i-1]没有10倍区域，arr[i]自己10倍，arr[0..i-1] + 10 * arr[i]
    //             乙：arr[0..i-1]中i-1位置在10倍区域里，arr[i]也在10倍区域里
    // magic[i] : magic[i] ..i  i
    // 对于乙，要求知道magic[j]的信息
    // magic[j]：arr[0..j]范围上，j一定要在10倍区域里，并且只有一个10倍区域的情况下，最大累加和
    // 可能性1：只有arr[j]是10倍，arr[0..j-1]没有10倍
    // 可能性2：magic[j-1] + 10 * arr[j]
    public static int maxSum(int[] arr) {
        int n = arr.length;
        if(n == 0){
            return 0;
        }
        if(n == 1){
            return Math.max(arr[0], arr[0] * 10);
        }
        //从右侧开始计算右侧的
        int sum = arr[n - 1];
        int magic = sum * 10;
        int[] right = new int[n];
        right[n - 1] = Math.max(sum, sum * 10);
        for(int i = n -2; i >= 0; i--){
            magic = 10 * arr[i] + Math.max(sum, magic); //i位置的magic值等于arr[i]*10
            sum += arr[i]; //纯求和
            right[i] = Math.max(Math.max(sum, right[i + 1] + arr[i]), magic);//magic[i]、原始累加和还有right[i+1] + arr[i]的最大值
        }

        //左侧计算
        int ans = right[0];
        sum = arr[0];
        magic = sum * 10;
        int dp = Math.max(sum, sum * 10);
        ans = Math.max(ans, dp + right[1]);
        for(int i = 1; i < n - 1; i++){
            magic = 10 * arr[i] + Math.max(sum, magic);//这个位置的magic等于arr[i] * 10 + max(sum,magic)
            sum += arr[i];
            dp = Math.max(Math.max(sum, dp + arr[i]), magic);
            ans = Math.max(ans, dp + right[i + 1]); //从i分成两部分，左侧部分是dp，右侧部分是right[i+1]
        }
        return ans;
    }
}
