package com.mikemyzhao.SortList_ALL.StrongHeap.bigshua;

import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-21 10:38
 * @Description:给出当前实时的topk词表https://www.lintcode.com/problem/top-k-frequent-words-ii/
 */
public class CurrentTopK_10 {
  //思路使用加强堆的小根堆，小根堆堆顶维度当前topk的最小值，使用treeset保证topn集合有序,使用字典树treeset保证结果一直有序
  //使用Node表示当前字符串的出现次数，包装一下
  public static class Node{
    public String str;
    public int times;
    public Node(String s,int t){
      str = s;
      times = t;
    }
  }
  //小根堆从小到大比较
  public static class NodeHeapComp implements Comparator<Node>{

    @Override
    public int compare(Node o1, Node o2) {
      return o1.times != o2.times ? (o1.times - o2.times) : (o2.str.compareTo(o1.str));
    }
  }

  public static class TreeSetComp implements Comparator<Node>{

    @Override
    public int compare(Node o1, Node o2) {
      return o1.times != o2.times ? (o2.times - o1.times) : (o1.str.compareTo(o2.str));
    }
  }

  private Node[] heap;
  private int heapSize;
  private HashMap<String, Node> strNodeMap;//存当前词的词频
  private HashMap<Node,Integer> nodeIndexMap;//存当前词的位置
  private NodeHeapComp comp;//比较器
  private TreeSet<Node> treeSet;//最后输出结果

  public CurrentTopK_10(int k){
    heap = new Node[k];
    heapSize = 0;
    strNodeMap = new HashMap<String, Node>();
    nodeIndexMap = new HashMap<Node, Integer>();
    comp = new NodeHeapComp();
    treeSet = new TreeSet<>(new TreeSetComp());
  }

  public void add(String str){
    if(heap.length == 0){
      return;
    }
    //找到对应的节点
    Node curNode = null;
    int preIndex = -1;//对应节点curNode在堆上的位置
    if (!strNodeMap.containsKey(str)){
      //没有这个节点
      curNode = new Node(str,1);
      strNodeMap.put(str, curNode);
      nodeIndexMap.put(curNode,-1);//先别放堆中
    }else{
      //strNodeMap存在当前节点
      curNode = strNodeMap.get(str);
      //在treeset中先删除，好让后续次数变的时候加进去有效
      if(treeSet.contains(curNode)){
        treeSet.remove(curNode);
      }
      curNode.times++;
      preIndex = nodeIndexMap.get(curNode);//该节点之前的位置
    }

    if(preIndex == -1){//说明没有入围
      //不在堆中
      if(heapSize == heap.length){
        if(comp.compare(heap[0],curNode) < 0){//当前的节点大要进堆，并且已经满了
          //此时小根堆堆顶出去
          treeSet.remove(heap[0]);
          treeSet.add(curNode);
          nodeIndexMap.put(heap[0],-1);
          nodeIndexMap.put(curNode, 0);
          heap[0] = curNode;
          heapify(0,heapSize);
        }
      } else {//此时堆没有满，往里加
        treeSet.add(curNode);
        nodeIndexMap.put(curNode, heapSize);
        heap[heapSize] = curNode;
        heapInsert(heapSize++);
      }
    } else {
      //curNode入围了
      treeSet.add(curNode);
      heapify(preIndex, heapSize);
    }
  }

  public List<String> topk() {
    ArrayList<String> ans = new ArrayList<>();
    for (Node node : treeSet) {
      ans.add(node.str);
    }
    return ans;
  }

  //heapInsert
  private void heapInsert(int index){
    while(index != 0){
      int parent = (index - 1) / 2;
      if(comp.compare(heap[index],heap[parent]) < 0){
        swap(parent,index);
        index = parent;
      } else {
        break;
      }
    }
  }
  //当前index看看要不要下沉
  private void heapify(int index, int heapSize){
    int l  = index * 2 + 1;
    int r = index * 2 + 2;
    int smallest = index;
    while(l < heapSize){
      if(comp.compare(heap[l],heap[index]) < 0) {
        smallest = l;
      }
      if(r < heapSize && comp.compare(heap[r], heap[smallest]) < 0){
        smallest = r;
      }
      if(smallest != index){
        swap(smallest, index);
      }else{
        break;//index是smallest直接跳出
      }
      index = smallest;
      l = index * 2 + 1;
      r = index * 2 +2;
    }
  }


  private void swap(int i1,int i2){
    nodeIndexMap.put(heap[i1],i2);
    nodeIndexMap.put(heap[i2],i1);
    Node tmp = heap[i1];
    heap[i1] = heap[i2];
    heap[i2] = tmp;
  }

}

