package com.mikemyzhao.DPAndRecursion_5.DP.bigshua.complexDP;

import java.util.HashMap;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-05 15:29
 * @Description:拼窗户问题：给定一个数组，找出两个不相容的子集，两个子集的累加和相等且最大
 * [1,4,6,5] ==>【1,4】和【5】
 */
public class WindowBillboard_22 {
  //技巧：用一个map记录，key是任意两个集合求和的差值，value是这两个集合中基数最大的那个。
  // 例子：集合{2,4}和{2,2},差值=2; 6-4
  //      集合 {6} 和 {8}, 差值=2; 8-6 ==》map(2,6),6是被减较大的，选6、8这一组，所以map为{2,6}

  // 不断进来数，然后不断更新每个差值对应的base

  //技巧：过程不太好想，当前进来就是两种情况，当前进来的数为num,遍历到的curDelta为map中存储的各个节点
  // (1) 上游{。。。。} + num || 下游{} curDelta = curDelta + num，所以map更新就是【curDelta，max(dp.get(num + d),当前这组新变为curDelta+num的base【其实就是dp.get(curDelata)】)
  // (2) 上游{。。。。}  || 下游{} + num curDelta = abs(curDelta - num),所以map更新有两种情况
  //      a.curDelta > num , map.put(curDelta - num,max(之前curDelta - num的值【dp.get(curDelta-num)】,当前要变的【dp.get(curDelta)+num】))
  //      b.curDelta <= num, map.put(num-curDelta,max(dp.get(num-curDelta),dp.get(curDelta) + d))#因为这个时候已经从小的变成大的了，另外那半拉所以就是相应变小了，变小了curDelta

  public int tallestBillboard(int[] arr){
    HashMap<Integer, Integer> dp = new HashMap<>();//老表
    dp.put(0,0);
    for(int num : arr){
      if(num != 0){
        //技巧：把原来的表复制过来
        HashMap<Integer, Integer> curMap = new HashMap<Integer, Integer>(dp);//把老表复制过来，然后在一直存结果的dp上操作
        for(int d : curMap.keySet()){
          int base = curMap.get(d);
          //技巧：把num放入当前(d，base)，{大} - {小} = d,放入{大}中
          dp.put(d + num, Math.max(dp.getOrDefault(d + num, 0), base));//现在这个是当前base变的，但是当前的base不变
          //技巧：把num放入当前(d，base)，{大} - {小} = d,放入{小}中
          if(d - num > 0){
            dp.put(d - num, Math.max(dp.getOrDefault(d - num, 0 ),base + num));
          } else {//技巧：当前的num干掉了d，把小的变成了大的
            dp.put(num - d, Math.max(dp.getOrDefault(num - d, 0), base + d));
          }
        }
      }
    }
    return dp.get(0);//技巧：找到差值为0的，就是他妈的最大的
  }

}
