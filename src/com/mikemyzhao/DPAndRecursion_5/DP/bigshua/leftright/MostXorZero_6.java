package com.mikemyzhao.DPAndRecursion_5.DP.bigshua.leftright;

import java.util.HashMap;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-18 18:50
 * @Description:对数组进行划分，找出能划出子数组异或和为0最多的划分方法
 */
public class MostXorZero_6 {
  //这题比较难，用从左到右的动态规划，然后还要使用假设答案的方法
  public static int mostXor(int[] arr){
    if(arr == null || arr.length == 0){
      return 0;
    }
    int N = arr.length;
    int[] dp = new int[N];//表示到当前i位置能划分出子数组异或和为0的数组数量最多是多少

    //key某一个前缀异或和
    //value这个前缀异或和上次出现的位置(最晚！)
    HashMap<Integer,Integer> map = new HashMap<>();
    map.put(0,-1);
    int xor = 0;
    for(int i = 0; i < N; i++){
      xor ^= arr[i];
      //此时i前面的那一块的异或和是0！这样才能进来一个
      if(map.containsKey(xor)){//表明之前出现过xor这个前缀和，所以说从最晚出现xor的时候到现在这部分为0！
        int pre = map.get(xor);
        dp[i] = pre == -1 ? 1: (dp[pre] + 1);//当前这个位置前面最后一个划分的位置
      }
      //xor之前没有出现过
      if(i > 0){
        dp[i] = Math.max(dp[i - 1],dp[i]);//当前这个位置的字符有没有
      }
      map.put(xor,i);//到当前的位置i，异或和还是为xor
    }
    return dp[N-1];
  }

}
