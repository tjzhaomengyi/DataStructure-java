package com.mikemyzhao.BFSDFS_ForTreeAndGraph_1_4.bigshua.BFS.WordLadder;

import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-17 15:10
 * @Description:找到从begin到end最短转换序列的单词数目,每次只变一个词
 */
public class LC_0127_WordLadder {
  //技巧:为字典做临接词表
  public static HashMap<String, ArrayList<String>> getNexts(List<String> words){
    HashSet<String> dict = new HashSet<>(words);
    HashMap<String,ArrayList<String>> nexts = new HashMap<>();
    for(int i = 0; i < words.size(); i++){
      nexts.put(words.get(i), getNext(words.get(i), dict));
    }
    return nexts;
  }
  public static ArrayList<String> getNext(String word, HashSet<String> dict){
    ArrayList<String> res = new ArrayList<>();
    char[] chs = word.toCharArray();
    for(int i = 0; i < chs.length; i++){
      for(char cur = 'a'; cur <= 'z'; cur++){
        if(chs[i] != cur){
          char tmp = chs[i];//先记录，还得撤回
          chs[i] = cur;
          if(dict.contains(String.valueOf(chs))){
            res.add(String.valueOf(chs));
          }
          chs[i] = tmp;
        }
      }
    }
    return res;
  }

  public static int ladderLength(String start, String end, List<String> list){
    list.add(start);
    //技巧:1邻接表做好
    HashMap<String,ArrayList<String>> nexts = getNexts(list);
    //技巧:2做距离表，当前单词到每个单词的距离
    HashMap<String, Integer> distanceMap = new HashMap<>();
    distanceMap.put(start, 1);
    //技巧:3 set记录dfs宽度遍历的节点
    HashSet<String> set = new HashSet<>();
    set.add(start);
    Queue<String> q = new LinkedList<>();
    q.add(start);
    while(!q.isEmpty()){
      String cur = q.poll();
      //技巧:4收点距离长度，如果存在到末尾的节点直接返回，如果没有往字典里面添加
      Integer distance = distanceMap.get(cur);
      for(String next : nexts.get(cur)){//从里面拿临接的字符串，不存在距离字典中的放入字典距离中
        if(next.equals(end)){
          return distance + 1;//找到了目标
        }
        if(!set.contains(next)){
          set.add(next);//技巧:dfs套路
          q.add(next);//技巧:dfs套路
          distanceMap.put(next, distance + 1);
        }
      }
    }
    return 0;
  }
}
