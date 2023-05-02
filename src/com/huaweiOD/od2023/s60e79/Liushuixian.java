package com.huaweiOD.od2023.s60e79;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-20 20:49
 * @Description:
 */
public class Liushuixian {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String[] strs = in.nextLine().split(" ");
      String[] jobs = in.nextLine().split(" ");
      solve(strs, jobs);
    }

  }
  public static void solve(String[] strs, String[] jobs){
    int medValue = Integer.parseInt(strs[0]); //流水线个数
    int[] arr = new int[jobs.length];
    for(int i = 0; i < jobs.length; i++){
      arr[i] = Integer.parseInt(jobs[i]);
    }

    Arrays.sort(arr);//对使用时间排序
    PriorityQueue<MedicBean> q = new PriorityQueue<>((o1, o2) -> o1.sum - o2.sum);
    for(int i = 0; i < arr.length; i++){
      if(q.size() < medValue){
        q.offer(new MedicBean(arr[i], arr[i]));
      } else {
        MedicBean m = q.poll();
        m.sum += arr[i];
        q.offer(m);
      }
    }
    for(int i = 0; i < medValue; i++){
      if(i == medValue - 1){
        System.out.println(q.poll().sum);
      } else {
        q.poll();
      }
    }
  }


}

class MedicBean {
  int end;
  int sum;

  public MedicBean(int end, int sum){
    this.end = end;
    this.sum = sum;
  }
}
