package com.point2offerspecial.fifteen_graph.shortest_path;

import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-20 17:52
 * @Description:
 */
public class PTOS108_WordLadder {
  //技巧：摒弃左神的DFS解法（因为看到最短路径的第一反应是使用队列求解）,Problem_0127_WordLadder
  //太难了~直接记住例子吧： 这个是求一个无向图的最短路径问题，所以使用BFS来求
  //技巧：在求图中两个节点之间的最短距离的时候，常用的解法是用两个队列来实现广度优先遍历
  // Queue1存放离起始节点距离为d的节点,当从这个队列中取出结点并访问的时候，与队列Queue1中结点相邻
  // 的节点距离开始节点的距离都是 d + 1，将这些相邻的结点存放到另一队列Queue2中。
  // 当队列1中所有节点访问完，再访问Queue2中的结点，并将相邻结点放入Queue1中，这样循环下去。
  public int ladderLength(String beginWord, String endWord, List<String> wordList){
    Queue<String> queue1 = new LinkedList<>();
    Queue<String> queue2 = new LinkedList<>();
    Set<String> notVisited = new HashSet<>(wordList);
    int length = 1;
    queue1.add(beginWord);
    while(!queue1.isEmpty()){
      String word = queue1.remove();
      if(word.equals(endWord)){
        return length;
      }

      List<String> neighbors = getNeighbors(word);
      for(String neightbor : neighbors){
        if(notVisited.contains(neightbor)){
          queue2.add(neightbor);
          notVisited.remove(neightbor);
        }
      }
      if(queue1.isEmpty()){
        length++;//技巧：什么时候边长每遍历到一次有转换字符的时候进行长度+1
        queue1 = queue2;
        queue2 = new LinkedList<>();
      }
    }
    return 0;
  }

  //把word中的字母变一个字符变成另外一个单词
  private List<String> getNeighbors(String word){
    List<String> neighbors = new LinkedList<>();
    char[] chars = word.toCharArray();
    for(int i = 0; i < chars.length; i++){
      char old = chars[i];
      for(char ch = 'a'; ch <= 'z'; ch++){
        if(old != ch){
          chars[i] = ch;
          neighbors.add(new String(chars));
        }
      }
      chars[i] = old;
    }
    return neighbors;
  }
}
