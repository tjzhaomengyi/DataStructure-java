package com.book.zuoshen.InterviewGuide.chpt8.array;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/22 11:23
 * @Description:BFPRT在和老方法选择随机值的时候是非常讲究的
 */
public class MostLessKinArray {
    //BFPRT选分割数的方法：（1）每五个数一组，如果最后一组不满五个数，全部一组
    //(2)每个小组排好序 （3）拿出每组的中位数，如果不够拿上中位数，利用中位数组成m数组
    //（4）在m数组中再找出m数组的中位数 p=bfprt(m,m.length/2)【定义bfprt函数的意义在m中找到第m.length/2小的数】,这个中位数就是要找的划分值
    // (5)使用p值完成荷兰国旗
    // 推理也是这个算法的结论：因为m数组是N/5的长度，那么大于h这个数至少有N/10个，进而说明原数组中有3*N/10个数大于h，然后每个代表回到原始数组又有2*N/10大于这个数
    // 小于这个数h有7/10N [上述推理不考虑o(1)的空间]
}
