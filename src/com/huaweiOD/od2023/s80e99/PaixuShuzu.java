package com.huaweiOD.od2023.s80e99;

import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-18 20:09
 * @Description:
 */
public class PaixuShuzu {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str = in.nextLine();
      String[] arr = str.split(",");
      HashMap<Integer, Node> dict = new HashMap<>();
      for(int i = 0; i < arr.length; i++){
        if(dict.containsKey(Integer.valueOf(arr[i]))){
         Node node = dict.get(Integer.valueOf(arr[i]));
         node.cnt++;
        } else {
          dict.put(Integer.valueOf(arr[i]), new Node(Integer.valueOf(arr[i]), 1, i));
        }
      }
      PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> o1.cnt == o2.cnt ? o1.index - o2.index : o2.cnt - o1.cnt);
      for(int key : dict.keySet()){
        q.offer(dict.get(key));
      }
      while(!q.isEmpty()){
        System.out.print(q.poll().num + " ");
      }
      System.out.println();

    }
  }


}

class Node{
  int num;
  int cnt;
  int index;
  public Node(int num, int cnt, int index){
    this.num = num;
    this.cnt = cnt;
    this.index = index;
  }
}
