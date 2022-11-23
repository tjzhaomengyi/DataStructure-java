package com.mikemyzhao.SomeSkills_0.duitui_bigshua.bigshua;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-21 8:44
 * @Description:跳跃游戏，递推问题https://leetcode.com/problems/jump-game-ii/
 */
public class JumpGame_10 {

  public static int jump(int[] arr){
    if(arr == null || arr.length == 0){
      return 0;
    }
    int step = 0;//当前跳了多少步
    int cur = 0;//只跳当前step可以cover住多远
    int next = 0;
    for(int i = 0; i < arr.length; i++){
      if(cur < i){//当前cover的距离不能满足了
        step++;//往前跳一下
        cur = next;
      }
      next = Math.max(next, i + arr[i]);//找一个更远的距离拉扯住当前的步数
    }
    return step;//遍历完一遍，O(N)解决
  }
}
