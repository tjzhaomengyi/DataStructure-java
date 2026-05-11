package com.niuke.BM101.bit;

/**
 * 在一个数组中有两个数只出现了一次，其他数都出现了两次，找出这两个只出现一次的数字
 */
public class BM52_FindNumOnlyOnce {
    public int[] FindNumsAppearOnce(int[] nums){
        int xor = nums[0];
        int group_0 = 0;
        int group_1 = 0;
        // 1、遍历数组，把所有数字进行亦或，这样双数出现的数字都变成0，
        // 最终结果是出现一次的两个数字的亦或结果
        // 两个出现一次的数字，这两个数字至少有一位不一样！！！
        for(int i = 1; i < nums.length; i++){
            xor = xor ^ nums[i];
        }
        // 2、找到这个亦或结果最后一位为1的位
        // 二进制数找到最后一位为1的位：n & (-n)
        xor = xor & (-xor);
        // 3、通过单一的位把上面数组区分为两个组，通过与的方法可以找到，这个“不一样的位”为1，这样把这个位为0的分成一组，这个位为1的分成一组
        // 同样，双数个的数这个为变成0，0可以亦或出这个单独的数
        for (int n : nums){
            int res = n & xor;
            if(res == 0){
                group_0 ^= n;
            } else {
                group_1 ^= n;
            }
        }
        return group_0 < group_1 ? new int[]{group_0, group_1} : new int[]{group_1, group_0};
    }

}
