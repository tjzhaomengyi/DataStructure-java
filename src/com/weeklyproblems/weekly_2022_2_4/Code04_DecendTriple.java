package com.weeklyproblems.weekly_2022_2_4;

/**
 * @Author: zhaomengyi
 * @Date: 2024/3/13 09:20
 * @Description:IndexTree来解决降序三元组，比线段树轻量【思路！思路！思路！看懂思路这个思路就解了这题】
 */
public class Code04_DecendTriple {
    //实现indextree的单点更新add(i,u)，还有sum(i),0...i的累加和是多少
    //从降序二元组组成降序三元组的结果，二元indextree记录了每个数出现了几次，
    //5 6 5 4 2,二元indextree出现了几次,indextree从1开始，值做为记录
    // 二元indextree:0 1 0 1 0 0
    //              1 2 3 4 5 6
    //三元indextree记录以4（以4为例子）为降序开头能记录几个二元组
    //1)来到4的时候，看看前面构成降序二元组的数量1
    // 降序三元组:0 0 0 1 0 0
    //          1 2 3 4 5 6
    //2)来到5的时候，更新二元组
    // 0 1 0 1 1 0
    // 1 2 3 4 5 6
    //更新三元组,直接使用二元组小于5的累加和
    //0 0 0 1 2 0
    //1 2 3 4 5 6

    //另外技巧：如果数组中出现过大的数或者负数，把数值搞成rank，线段树就不那么大了，做一下离散化

}
