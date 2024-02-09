package com.mikemyzhao.SomeSkills_0.duitui_bigshua.bigshua;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-21 8:44
 * @Description:跳跃游戏，这个是动态规划https://leetcode.com/problems/jump-game-ii/
 */
public class JumpGame_10 {

  public static int jump(int[] arr){
    if(arr == null || arr.length == 0){
      return 0;
    }
    int step = 0;//当前跳了多少步
    int cur = 0;//只跳当前step可以cover住多远，比如step=3，cur=100，当前走3步最远到100
    int next = 0; //记录每一个位置到达后能够到达的最远距离（如果多跳一步最远到哪）
    for(int i = 0; i < arr.length; i++){
      if(cur < i){//当前cover的距离不能满足了
        step++;//往前跳一下
        cur = next;//既然在内部跳了一下，那么它就能达到前面计算好的最远距离
      }
      next = Math.max(next, i + arr[i]);//多跳一步到i，找一个更远的距离拉扯住当前的步数
    }
    return step;//遍历完一遍，O(N)解决
  }
}
