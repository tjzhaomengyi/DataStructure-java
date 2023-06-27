package com.ZuoshenGaopinMianshi.four_heihe;

/**
 * @Author: zhaomengyi
 * @Date: 2023-06-06 11:34
 * @Description:相邻小朋友分数一样，糖果数可以一样，如果不一样分数大的要给的多.【繁衍题】网易
 */
public class One_XiaopengyouFenTangguo {
  //没有环 思路: 找爬坡，然后以较大的破为准来发糖。利用两个左坡数组和右坡数组，完成坡度统计，然后两个同位置数组，取最大值即可
  //有环  思路:先找到谷底，然后把谷底的小朋友放在最左边，再接上剩下的小朋友，然后再接上谷底小朋友。谷底一定分一块糖。【重要的是用谷底把成环的小朋友分开】，然后做左右数组即可。
}
