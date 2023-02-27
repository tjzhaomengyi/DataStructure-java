package com.point2offerspecial.fifteen_graph.shortest_path;

import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-20 18:59
 * @Description:
 */
public class PTOS109_OpenLock {
  public int openLock(String[] deadends, String target) {
    Set<String> dead = new HashSet<>(Arrays.asList(deadends));
    Set<String> visited = new HashSet<>();
    String init = "0000";
    if(dead.contains(init) || dead.contains(target)){
      return -1;
    }

    Queue<String> queue1 = new LinkedList<>();
    Queue<String> queue2 = new LinkedList<>();
    int steps = 0;
    queue1.offer(init);
    visited.add(init);
    while(!queue1.isEmpty()){
      String cur= queue1.poll();
      if(cur.equals(target)){
        return steps;
      }

      List<String> nexts = getNeighbors(cur);
      for(String next : nexts){
        if(!dead.contains(next) && !visited.contains(next)){
          queue2.add(next);
          visited.add(next);
        }
      }

      if(queue1.isEmpty()){
        steps++;
        queue1 = queue2;
        queue2 = new LinkedList<>();
      }
    }
    return  -1;
  }

  private List<String> getNeighbors(String cur){
    List<String> nexts = new LinkedList<>();
    for(int i = 0; i < cur.length(); i++){
      char ch = cur.charAt(i);

      //技巧：两侧旋转都加进去
      char newCh = ch == '0' ? '9' :(char)(ch - 1);
      StringBuilder builder = new StringBuilder(cur);
      builder.setCharAt(i, newCh);
      nexts.add(builder.toString());

      newCh = ch == '9' ? '0' : (char)(ch + 1);
      builder.setCharAt(i, newCh);
      nexts.add(builder.toString());
    }
    return nexts;
  }
}
