package com.mikemyzhao.BFSDFS_ForTreeAndGraph_1_4.bigshua.DFS.WordLadder;

import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-09 16:11
 * @Description: 字符串梯子：给定一个起始字符串和一个终结字符串，还有一个转换列表，如果start->end可以通过转换列表得到，求转换次数最少的所有过程
 * // 本题测试链接 : https://leetcode.com/problems/word-ladder-ii/
 */
public class WordLadderII_26 {
  //数学结论：【图的题BFS求最短距离，DFS求路径】这个题用BFS查一个点出发到字典有效值的所有距离，用DFS求一个转换过程结果路径！
  public static List<List<String>> findLadders(String start, String end, List<String> list){
    //技巧：生成两个重要的表：(1)连接表 (2)start到每个点的距离表
    if(!list.contains(start)) {
      list.add(start);//技巧：因为题目中说start可以不必是list字典中的一员，这他妈比不是坑么，你不在里面怎么找起点的连接点
    }
    HashMap<String, List<String>> nexts = getNexts(list);
    HashMap<String, Integer> distances = getDistances(start, nexts);
    //思路：3、使用DFS遍历出有效的路径
    LinkedList<String> pathList = new LinkedList<>();
    List<List<String>> res = new ArrayList<>();
    getShortestPaths(start, end, nexts, distances, pathList, res);
    return res;

  }

  //思路：1、找到每个字符串的邻接表,转换一个字符可以转换到的另一个字符串
  public static HashMap<String,List<String>> getNexts(List<String> words){
    HashSet<String> dict = new HashSet<>(words);
    HashMap<String, List<String>> nexts = new HashMap<>();
    for(int i = 0; i < words.size(); i++){
      nexts.put(words.get(i),getNext(words.get(i), dict));
    }
    return nexts;
  }

  //思路：2、获取每个字符串转换一个字符，并且在dict中出现的
  public static List<String> getNext(String str, HashSet<String> dict){
    ArrayList<String> res = new ArrayList<>();
    char[] chs = str.toCharArray();
    for(int i = 0; i < chs.length; i++){
      for(char cur = 'a'; cur < 'z'; cur++){
        if(chs[i] != cur){
          char tmp = chs[i];
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

  //思路：3、距离表：从start开始根据邻居表，进行宽度优先遍历，生成距离表
  public static HashMap<String, Integer> getDistances(String start, HashMap<String,List<String>> nexts){
    HashMap<String, Integer> distances = new HashMap<>();//技巧：start到每个字符串的距离
    distances.put(start,0);
    //技巧：BFS两个辅助，一个Queue一个Set
    Queue<String> queue = new LinkedList<>();
    queue.add(start);
    HashSet<String> set = new HashSet<>();
    set.add(start);
    while(!queue.isEmpty()){
      String cur = queue.poll();
      for(String next : nexts.get(cur)){
        if(!set.contains(next)){
          distances.put(next, distances.get(cur) + 1);
          queue.add(next);
          set.add(next);
        }
      }
    }
    return distances;
  }

  //思路：4、使用DFS + 辅助距离表！辅助距离表！距离表辅助~ 距离表辅助~ 找到合适的路径
  //数学结论：只要每次距离只加1，最后到最后end目标值的距离肯定最短，这个路径肯定是我们要的结果中的一个
  //cur:当前来到的字符串，可变;to目标，固定参数; nexts每个字符串的邻居们，cur当前节点到开头节点的距离，path：深度优先的path逼事多么不是
  //res 存结果
  public static void getShortestPaths(String cur, String to, HashMap<String, List<String>> nexts,
                                      HashMap<String, Integer> distances ,LinkedList<String> path, List<List<String>> res){
    path.add(cur);//技巧：DFS假么假事的先给人家放里，毕竟得从这遍历么，一会还得他妈的拿出来
    if(to.equals(cur)){
      res.add(new LinkedList<>(path));
    } else {
      for(String next : nexts.get(cur)){
        if(distances.get(next) == distances.get(cur) + 1){//这里距离就对要求的最短长度进行了有效的限制！！！
          getShortestPaths(next, to, nexts, distances, path, res);
        }
      }
    }
    path.pollLast();//技巧：对应开头扔进来完事还得扔出去
  }


}
