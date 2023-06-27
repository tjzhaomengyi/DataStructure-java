package com.ZuoshenGaopinMianshi.three_yidianyaojiandeti;

/**
 * @Author: zhaomengyi
 * @Date: 2023-06-06 10:09
 * @Description: 大厂刷题班No.7 一周三节 Maxium-Gap
 */
public class Three_HashSetDengGailvFanhui {
  //等概率返回，getRandom()，让所有样本等概率返回
  //技巧:两个HashMap分别记录元素和序号keyIndexMap，indexKeyMap。如果有删除，Random到空洞的时候，时间复杂度就增高，效率变差。
  // 解决办法: 把删除元素的位置，用最后一个位置的元素塞上，把最后一个元素删除
  // 随机: randomIndex = (int)Math.random() * size;
}
