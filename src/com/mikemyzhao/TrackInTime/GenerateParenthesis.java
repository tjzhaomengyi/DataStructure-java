package com.mikemyzhao.TrackInTime;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-13 10:44
 * @Description:括号生成问题用回溯处理，判断括号对用栈处理
 *
 * 回溯模板：
 * result=[]
 * def backtrack(路径，选择列表){
 *   track=[]//选择路径
 *   if 满足结束条件:
 *    result.add(路径)
 *    return
 *   for 选择 in 选择列表:
 *    做选择
 *    backtrack(路径，选择列表)
 *    撤销选择
 * }
 * */
public class GenerateParenthesis {
    /**伪代码
     * backtrack(int n,int i,String track){
     *   if(i==2*n){
     *     print track;
     *     return;
     *   }
     *
     *  //对于每个位置可以是左括号，也可以是右括号两种选择
     *  for choice in ['(',')']{
     *    //做选择
     *    track.add(choice)
     *    //回溯
     *    backtrack(n,i+1,track)
     *    //撤销选择
     *    track.pop(choice)
     *  }
     * }
     * **/
    List<String> res = new ArrayList<>();
    List<String> generateParenthesis(int n){
      if(n==0) return null;
      StringBuilder track = new StringBuilder();
      //可用的左括号和右括号初始化为n
      backtrack(n,n,track,res);
      return res;
    }

  /**
   * 可以使用的左括号数量为left，可使用的右括号数量为right
   * @param left
   * @param right
   * @param track
   * @param res
   */
    void backtrack(int left,int right,StringBuilder track,List<String> res){
      if(left<0||right<0) return;//都没有了不合法
      if(left>right) return;//左括号多了，肯定也不合法，这怎么生成
      /**先写结果**/
      //当所有括号都恰好用完时，得到一个合法的括号组合
      if (left==0 && right==0){
        res.add(track.toString());
        return;
      }
      /**后回溯，这是套路**/
      //for choice in ‘(‘ and')'
      //尝试添加一个左括号
      track.append('(');
      backtrack(left-1,right,track,res);
      track.deleteCharAt(track.length()-1);

      //尝试添加一个有括号
      track.append(')');
      backtrack(left,right-1,track,res);
      track.deleteCharAt(track.length()-1);
    }
}
