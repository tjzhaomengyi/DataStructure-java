package com.mikemyzhao.SortList_ALL.SortedListBasedBalancedTree.SortedList_11.bigshua.use;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-03 12:35
 * @Description:给定几个数组，要求找到一个范围能够至少覆盖每个数组中的1个数字
 * @思路：这个就是一个整体流程K问题用堆中的有序表
 */
public class SmallestRangeCoverOneEleInEveryArray_19 {
  //思路：使用一个小根堆，每次弹出最小的，然后把下一个压入，直到某个数组遍历完成，结束
  public static class Node{
    public int value;
    public int arrid;
    public int index;

    public Node(int v, int ai, int i){
      value = v;
      arrid = ai;
      index = i;
    }
    public static class Nodecomparator implements Comparator<Node> {

      @Override
      public int compare(Node o1, Node o2) {
        return o1.value != o2.value ? o1.value - o2.value : o1.arrid - o2.arrid;
      }
    }
  }

  public static int[] smallestRange(List<List<Integer>> nums){
    int N = nums.size();
    TreeSet<Node> orderSet = new TreeSet<>(new Node.Nodecomparator());
    for(int i = 0; i < N; i++){
      orderSet.add(new Node(nums.get(i).get(0), i, 0));//把每个list的第一个元素添加到orderset中
    }
    boolean set = false;
    int a = 0;
    int b = 0;
    while(orderSet.size() == N){//结果中保证是这几个数组中的代表
      Node min = orderSet.first();
      Node max = orderSet.last();
      if(!set || (max.value - min.value < b - a)){
        set = true;
        a = min.value;
        b = max.value;
      }
      min = orderSet.pollFirst();//拿出最小的
      int arrid = min.arrid;
      int index = min.index + 1;//拿当前set中最小的下一个
      if(index != nums.get(arrid).size()){
        orderSet.add(new Node(nums.get(arrid).get(index), arrid, index));
      }
    }
    return new int[]{a, b};
  }
}
