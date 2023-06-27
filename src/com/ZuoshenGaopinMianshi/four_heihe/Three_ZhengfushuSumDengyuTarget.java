package com.ZuoshenGaopinMianshi.four_heihe;

/**
 * @Author: zhaomengyi
 * @Date: 2023-06-06 12:19
 * @Description:子数组有正数和负数
 */
public class Three_ZhengfushuSumDengyuTarget {
  // 繁衍题:前缀和，利用前缀和 配和 前缀和最早出现位置的map即可
  //技巧:求每个位置结果到不到Target，如果是子数组问题，变成其他的繁衍题，都这么想。
  // 利用前缀和，利用一个哈希表记录前缀和:最早出现的位置。
  // findPreSum = sum - target, if(preSumMap.containsKey(findPresum)
  // 细节:要把(0，-1)加在map中，一个数也没有前缀和是0 ，表示从0开始有这个前缀和，这一句非常关键。
}
