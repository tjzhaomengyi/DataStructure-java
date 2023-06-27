package com.huaweiOD.score200;

import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2023-05-29 11:23
 * @Description:
 */
public class KuaisuKaizuJianzhan {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      int n = Integer.valueOf(in.nextLine());
      int len = Integer.valueOf(in.nextLine());
      int[][] relations = new int[len][2];
      for(int i = 0; i < len; i++){
        String[] tmp = in.nextLine().split(" ");
        relations[i][0] = Integer.valueOf(tmp[0]);
        relations[i][1] = Integer.valueOf(tmp[1]);
      }
      System.out.println(solveMethod(relations, n));
    }
  }

  public static int solveMethod(int[][] relations, int taskNum){
    Map<Integer, List<Integer>> next = new HashMap<>();
    int[] inDegree = new int[taskNum];//保存每个任务的入度

    for (int[] relation : relations) {
      int a = relation[0];
      int b = relation[1];

      //添加后继任务
      if (next.get(a) == null) {
        List<Integer> tmp_lst = new ArrayList<>();
        tmp_lst.add(b);
        next.put(a, tmp_lst);
      } else {
        List<Integer> tmp_lst = next.get(a);
        tmp_lst.add(b);
        next.put(a, tmp_lst);
      }
      inDegree[b]++;
    }

      //使用队列解决问题
      Queue<int[]> queue = new LinkedList<>();
      int t = 1;
      for(int i = 0; i < taskNum; i++){
        if(inDegree[i] == 0){
          queue.offer(new int[]{i, t});
        }
      }
      while(!queue.isEmpty()){
        //弹出队首任务
        int[] taskAndTime = queue.poll();
        int task = taskAndTime[0];
        int time = taskAndTime[1];
        //处理后继任务
        if (next.containsKey(task)){//这个task肯定是next map中的key
          for(int nxt : next.get(task)){
            inDegree[nxt]--;
            if(inDegree[nxt] == 0){
              //后继任务入度为0， 加入队列
              t = time + 1;
              queue.offer(new int[]{nxt, t});
            }
          }
        }
      }
      return t;
    }

}
