package com.mikemyzhao.TrackInTime;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-12 18:17
 * @Description:回溯算法，一般解决几个问题：求子集subset、求排列permutation和求组合combination问题
 * @NO:LC78
 */
public class BackTracking {
  /***
   * 第一类问题：求子集,输入一个不包含重复数字的数组，求出这些数字的所有子集，包含空集和本身
   * 回溯算法的基础模板就是一颗决策树
   *  result = []
   *  def backtrack(路径,选择列表){
   *    if 满足结束条件：
   *      result.add(路径)
   *      return
   *    for 选择 in 选择列表：
   *      做选择
   *      backTrack(路径,选择列表)
   *      撤销选择
   *  }
   *  从[1,2,3]找出所有子集，就可以构造下面一颗决策树
   *                        【】
   *      【1】             【2】        【3】
   *【1,2】   【1,3】      【2,3】
   *【1,2,3】
   *
   *回溯算法的核心:用一个start参数控制递归，生成这样一棵树
   */
  List<List<Integer>> res = new ArrayList<>();
  public List<List<Integer>> subsets(int[] nums){
      ArrayList<Integer> track = new ArrayList<>();//记录走过的路径，就是记住选择
      backtrackForSubsets(nums,0,track);
      return res;
  }
  //使用回溯算法实现subsets
  void backtrackForSubsets(int[] nums,int start,ArrayList<Integer> track){
    //前序遍历位置
    res.add(new ArrayList<>(track));//这里防止引用，要复制一份到res中
    //从start开始，防止产生重复子集
    for(int i=start;i<nums.length;i++){
      //做选择
      track.add(nums[i]);
      //递归回溯
      backtrackForSubsets(nums,i+1,track);//对当前track进行递归
      track.remove(track.size()-1);//重点！！！撤销选择，把刚放进来的再拿出去进行后面的迭代，回溯就是这样，要在递归后撤销选择
    }

  }
  /**
   * 问题2，组合问题，输入两个数字n和k，输出[1..n]中k个数字的所有组合
   * 这个问题组合的个数是有k个数字限制的
   * **/
   List<List<Integer>> res1 = new ArrayList<>();
   List<List<Integer>> combinations(int n,int k){
    if(k<=0||n<=0) return res;
    return res1;
   }

   void backTrackforCombinations(int n,int k,int start,ArrayList<Integer> track){
     //到达叶子节点才更新res
     if(k==track.size()){
       res.add(new ArrayList<>(track));
       return;
     }
     for(int i=start;i<=n;i++){
       //做选择
       track.add(i);
       backTrackforCombinations(n,k,i+1,track);
       track.remove(track.size()-1);
     }
   }

   /**
    * 排列,给出[1,2,3]得到其全排列
    * **/
   List<List<Integer>> res2 = new LinkedList<>();

   List<List<Integer>> permute(int[] nums){
     //记录路径
     List<Integer> track = new LinkedList<>();
     backTrackForPermute(nums,track);
     return res2;
   }

   void backTrackForPermute(int[] nums,List<Integer> track){
     if(track.size() == nums.length){
       res.add(new ArrayList<>(track));
       return;
     }
     for(int i=0;i<nums.length;i++){
       //排除已经有的
       if(track.contains(nums[i])){
         continue;
       }
       track.add(nums[i]);
       backTrackForPermute(nums,track);
       track.remove(track.size()-1);
     }
   }


  public static void main(String[] args) {
    System.out.println(new BackTracking().subsets(new int[]{1,2,3}));
  }



}
