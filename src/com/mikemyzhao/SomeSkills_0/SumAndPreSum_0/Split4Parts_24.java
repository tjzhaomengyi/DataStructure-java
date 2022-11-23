package com.mikemyzhao.SomeSkills_0.SumAndPreSum_0;

import java.util.HashMap;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-07 8:26
 * @Description:给定一个长度大于7的数组，求是否能切三刀，切在数字上把数字删掉，得到四个数组累加和一样
 */
public class Split4Parts_24 {
  public static boolean canSplit(int[] arr){
    if(arr == null || arr.length < 7){
      return false;
    }
    //Key某一个累加和 ，value出现的位置
    HashMap<Integer, Integer> map = new HashMap<>();
    int sum = arr[0];
    for(int i = 1; i < arr.length; i++){
      map.put(sum, i);//反置的累加和，把结果放在前面，位置放在后面
      sum += arr[i];
    }
    int lsum = arr[0];//第一刀左侧累加和
    for(int s1 = 1; s1 < arr.length - 5; s1++) {//数学结论：最远在哪刀，肯定不在最终位置，就是两道最少要在哪刀
      //技巧：如果这么刀正确得到第一段和刀掉部分为：part1 + s1 ，那么在第二刀的前面肯定是 2 * part1 + s1,
      // 这样就可以找到，然后异步一步往下找后面2到3刀即可，都不用循环
      int checkSum = lsum * 2 + arr[s1];
      if(map.containsKey(checkSum)){//技巧：因为前缀和本身自己的求法的性质，找到这个前缀和的位置对应的前缀和正好不包含这个位置
        int s2 = map.get(checkSum);//这一刀前面的累加和正好是 lsum * 2 + arr[s1];
        checkSum += lsum + arr[s2];//第三刀的checksum
        if (map.containsKey(checkSum)){
          int s3 = map.get(checkSum);
          if(checkSum + arr[s3] + lsum == sum){
            return true;
          }
        }
      }
      //如果检查完都不行，往右走的同时，lsum把这个[s1]的值囊括到自己中
      lsum += arr[s1];
    }
    return false;
  }
}
