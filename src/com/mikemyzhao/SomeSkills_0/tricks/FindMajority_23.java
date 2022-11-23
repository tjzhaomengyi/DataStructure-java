package com.mikemyzhao.SomeSkills_0.bigshua.tricks;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-06 9:27
 * @Description:超级水王问题，选出超过一半以上的元素
 */
public class FindMajority_23 {
  public static void printHalfMajor(int[] arr){
    //技巧：如果没有候选，设置候选，把HP = 1.如果和候选不同HP-1，如果和候选相同HP+1，当HP=0的时候删掉这个候选，
    //技巧：最后的候选必须遍历一遍才能确定是否是最终的水王
    int cand = 0;//
    int HP = 0;
    int N = arr.length;
    for(int i = 0; i < N; i++){
      if(HP == 0){
        cand = arr[i];
        HP = 1;
      } else if(arr[i] == cand){
        HP++;
      }else{
        HP--;
      }
    }
    if(HP == 0){
      System.out.println("no such number");
      return;
    }
    //技巧：从前遍历看看这个元素是否是水王
    HP = 0;
    for(int i = 0; i < arr.length; i++){
      if(arr[i] == cand){
        HP++;
      }
    }
    if(HP > arr.length/2){
      System.out.println(cand);
    }else{
      System.out.println("No such number");
    }
  }

  //找出多于n/k个的元素,数学结论：如果大于n/4那么最多三条
  public static void printKMajor(int[] arr, int K){
    if(K < 2){
      System.out.println("not valid k");
      return ;
    }

    HashMap<Integer, Integer> cands = new HashMap<>();
    for(int i = 0; i < arr.length; i++){
      if(cands.containsKey(arr[i])){
        cands.put(arr[i],cands.get(arr[i] + 1));
      } else{
        //如果当前不是候选
        if(cands.size() == K - 1){
          //当前的候选满了,并且当前值不等于候选，每个值血量-1，
          //技巧：如果当前最小的只有一个打掉，新的也不进来，相当于两个之间互相抵消
          allCandsMinusOne(cands);
        } else{
          cands.put(arr[i] ,1);
        }
      }
    }
    //所有可能的候选都要遍历一遍
    HashMap<Integer, Integer> reals = getReals(arr, cands);
    boolean hasPrint = false;
    for(Map.Entry<Integer, Integer> set : cands.entrySet()){
      Integer key = set.getKey();
      if(reals.get(key) > arr.length / K){
        hasPrint = true;
        System.out.print(key + " ");
      }
    }
    System.out.println(hasPrint ? "" : "no such number");
  }

  public static void allCandsMinusOne(HashMap<Integer, Integer> map){
    List<Integer> removeList = new LinkedList<>();//技巧：map中的元素最后一起删除
    for(Map.Entry set : map.entrySet()){
      Integer key = (Integer) set.getKey();
      Integer value = (Integer) set.getValue();
      if(value == 1){
        removeList.add(key);
      }
      map.put(key, value - 1);
    }
    for(Integer key : removeList){
      map.remove(key);
    }
  }

  public static HashMap<Integer, Integer> getReals(int[] arr, HashMap<Integer, Integer> cands){
    HashMap<Integer, Integer> reals = new HashMap<>();
    for(int i = 0; i != arr.length; i++){
      int curNum = arr[i];
      if(cands.containsKey(curNum)){
        reals.put(curNum, reals.get(curNum) + 1);
      }else {
        reals.put(curNum , 1);
      }
    }
    return reals;
  }
}
