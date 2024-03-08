package com.weeklyproblems.week2021_12_3;

/**
 * @Author: zhaomengyi
 * @Date: 2024/3/7 13:39
 * @Description:aa??bbb,让连续最短aababbb
 */
public class Code01_ReplaceSymbolToShortContinueChar {
    //结论：[原则：把两头卡死]（1）问号左边长度和问号右边的长度相等，并且中间问号是奇数个a???a,固定答案ababa，一定让左右断开，自己也断开
    //（2）左边等于右边，中间问号长度是偶数，a????a，固定答案abaaba
    // (3)左边不等于右边，问号长度是偶数a????b,ababab
    // (4)左边不等于右边，问号长度是一个大于1的奇数，a???b,abaab
    // （5）左不等于右，问号长度为1，aaa?bb?a,左a3右b2，谁小用谁
}
