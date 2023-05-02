package com.huaweiOD.od2023.s1e19;

import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-26 17:32
 * @Description:
 */
public class Shejibisai {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      int len = Integer.valueOf(in.nextLine());
      String[] indexes = in.nextLine().split(",");
      String[] scores = in.nextLine().split(",");
      HashMap <Integer, Xuanshou> map = new HashMap<>();
      for(int i = 0; i < len; i++){
        int index = Integer.valueOf(indexes[i]);
        int score = Integer.valueOf(scores[i]);
        if(map.keySet().contains(index)){
          Xuanshou xuanshou = map.get(index);
          xuanshou.add(score);
          map.put(index, xuanshou);
        } else {
          Xuanshou xuanshou = new Xuanshou(index);
          xuanshou.add(score);
          map.put(index, xuanshou);
        }
      }
      ArrayList<Xuanshou> tmp = new ArrayList<>();
      for(int k : map.keySet()){
        if(map.get(k).cnt <3){
          continue;
        }
        tmp.add(map.get(k));
      }
      Collections.sort(tmp, (o1, o2) -> o1.sum != o2.sum ? o2.sum - o1.sum : o2.index - o1.index);
      for(int i = 0; i < tmp.size(); i++) {
        System.out.print(tmp.get(i).index + " ");
      }
      System.out.println();
    }
  }
}

class Xuanshou{
  int index;
  PriorityQueue<Integer> q;
  int sum;
  int cnt;

  public Xuanshou(int index){
    this.index = index;
    q = new PriorityQueue<>();
  }

  public void add(int score){
    cnt++;
    if(q.size() < 3){
      q.offer(score);
      sum = sum + score;
    } else if(q.size() == 3){
      if(score > q.peek()){
        int poll = q.poll();
        sum = sum - poll;
        q.offer(score);
        sum = sum + score;
      }
    }
  }
}
