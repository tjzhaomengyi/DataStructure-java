package com.weeklyproblems.weekly_2022_2_2;

/**
 * @Author: zhaomengyi
 * @Date: 2024/3/12 12:02
 * @Description:
 */
public class Code02_BitSet {
    class BitSet {
        private int[] bits; //一个整数有32位， size / 32 向上取整
        private final int size;
        private int zeros; //所有位置0有几个
        private int ones;
        private boolean reverse; //是否翻转

        BitSet(int n) {
            bits = new int[(n + 31) / 32];
            size = n;
            zeros = n;

        }
    }
}
