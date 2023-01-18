package com.hotinterview.Nanti;


import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2023-01-10 2:51 下午
 * @Description:
 */
public class Code0218_BuildingSkyline {
  //儿童趣味益智：如何描述出大楼的轮廓:使用Node数组，长度为2倍长的x点位个数，2i记录长高，2i+1记录降低
  // 借助两个TreeMap，一个记录高度出现的次数（height,cnt）[根据height默认从小到大排序]，另一个记录当前位置看到的最高天际线高度(x,height)【根据高度-次数map更新结果进行更新】
  public class Node{
    public int x;//位置
    public boolean isAdd;
    public int h;

    public Node(int x, boolean isAdd, int h){
      this.x = x;
      this.isAdd = isAdd;
      this.h = h;
    }
  }

  public class NodeComparator implements Comparator<Node>{

    @Override
    public int compare(Node o1, Node o2) {
      //让x坐标小的在前面
      return o1.x - o2.x;
    }
  }
  //思路：摒弃给的大楼的数据结构，让这个数据结构回到Nodes数组 和 两个Map中，就是转成儿童趣味益智题：类似于图转成普通表达
  public List<List<Integer>> getSkyline(int[][] matrix){
    Node[] nodes = new Node[matrix.length * 2];
    for(int i = 0; i < matrix.length; i++){
      //思路：下面两个就是把matrix转换成node数组信息
      nodes[i * 2] = new Node(matrix[i][0], true, matrix[i][2]);//给坐标和
      nodes[i * 2 + 1] = new Node(matrix[i][1], false, matrix[i][2]);
    }
    //让node数组根据x排序
    Arrays.sort(nodes, new NodeComparator());

    //key 高度 ： value 次数
    TreeMap<Integer, Integer> mapHeightTime = new TreeMap<>();
    //记录当前x点 能看到的最大天际线高度，利用这个生成最后天际线轮廓
    TreeMap<Integer, Integer> mapXHeight = new TreeMap<>();
    for(int i = 0; i < nodes.length; i++){
      if(nodes[i].isAdd){//该点是往上建造大楼
        if(!mapHeightTime.containsKey(nodes[i].h)){
          mapHeightTime.put(nodes[i].h, 1);
        } else {
          mapHeightTime.put(nodes[i].h, mapHeightTime.get(nodes[i].h) + 1);
        }
      } else {//该点是大楼的结束位置
        if(mapHeightTime.get(nodes[i].h) == 1){
          mapHeightTime.remove(nodes[i].h);//思路：这里一定要移除掉，因为另外一个map统计最大高度，不能被影响
        } else {
          mapHeightTime.put(nodes[i].h, mapHeightTime.get(nodes[i].h) - 1);
        }
      }
      //这里要开始更新mapXHeight
      if(mapHeightTime.isEmpty()){
        //说明这个区域没有大楼了
        mapXHeight.put(nodes[i].x, 0);
      } else {
        mapXHeight.put(nodes[i].x, mapHeightTime.lastKey());//因为是treeMap本身就根据key从大到小
      }
    }
    List<List<Integer>> ans = new ArrayList<>();
    // x ： maxHeight
    for(Map.Entry<Integer, Integer> entry : mapXHeight.entrySet()){
      int curX = entry.getKey();//当前x位置
      int curMaxHeight = entry.getValue();//当前位置的最大高度
      if(ans.isEmpty() || ans.get(ans.size() - 1).get(1) != curMaxHeight){
        ans.add(new ArrayList<>(Arrays.asList(curX,curMaxHeight)));
      }
    }
    return ans;
  }
}
