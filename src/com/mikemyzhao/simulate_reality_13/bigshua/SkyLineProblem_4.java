package com.mikemyzhao.simulate_reality_13.bigshua;

import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-17 18:20
 * @Description:https://leetcode.com/problems/the-skyline-problem/
 * 大楼轮廓问题
 */
public class SkyLineProblem_4 {
  //这里默认没有纸片楼
  //把每个楼的开始和结束按照Node节点生成，然后按照开始从小到大排序
  //统计每个楼h的出现次数，是isAdd就+1，否则减1，当剪到0的时候直接删除
  public static class Node {
    public int x;
    public boolean isAdd;
    public int h;

    public Node(int x,boolean isAdd, int h){
      this.x = x;
      this.isAdd = isAdd;
      this.h = h;
    }
  }

  public static class NodeComparator implements Comparator<Node>{

    @Override
    public int compare(Node o1, Node o2) {
      return o1.x - o2.x;
    }
  }

  public static List<List<Integer>> getSkyLine(int[][] matrix){
    Node[] nodes = new Node[matrix.length * 2];
    for(int i = 0; i < matrix.length; i++){
      nodes[i * 2] = new Node(matrix[i][0],true,matrix[i][2]);
      nodes[i * 2 + 1] = new Node(matrix[i][1], false, matrix[i][2]);
    }
    Arrays.sort(nodes, new NodeComparator());
    //key 高度， Value 次数
    TreeMap<Integer,Integer> mapHeightTimes = new TreeMap<>();//高度出现次数
    TreeMap<Integer, Integer> mapXHeight = new TreeMap<>();//每个位置的最大轮廓线
    for(int i = 0; i < nodes.length; i++){
      if(nodes[i].isAdd){
        if(!mapHeightTimes.containsKey(nodes[i].h)){
          mapHeightTimes.put(nodes[i].h, 1);
        }else{
          mapHeightTimes.put(nodes[i].h,mapHeightTimes.get(nodes[i].h) + 1);
        }
      }else{
        if(mapHeightTimes.get(nodes[i].h) == 1){
          mapHeightTimes.remove(nodes[i].h);
        } else {
          mapHeightTimes.put(nodes[i].h,mapHeightTimes.get(nodes[i].h) - 1);
        }
      }
      //如果当前的mapHeightTimes是空，把开始节点添加进来
      if(mapHeightTimes.isEmpty()){
        mapXHeight.put(nodes[i].x,0);
      } else { //如果不是空，那么当前点的最大高度就是mapHeightTimes的lastKey
        mapXHeight.put(nodes[i].x, mapHeightTimes.lastKey());//todo:这里是关键！！！！【这个太巧了】这个就是相同的高度put一遍
      }
    }
    List<List<Integer>> ans = new ArrayList<>();
    for(Map.Entry<Integer,Integer> entry : mapXHeight.entrySet()){
      int curX = entry.getKey();
      int curMaxHeight = entry.getValue();
      if(ans.isEmpty() || ans.get(ans.size() - 1).get(1) != curMaxHeight){
        ans.add(new ArrayList<>(Arrays.asList(curX,curMaxHeight)));
      }
    }
    return ans;

  }
}
