package com.book.point2offerspecial.fifteen_graph.all_path;



import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-20 20:11
 * @Description:这道题终于知道怎么回事了，就是用图联通的方法
 */
public class PTOS111_CalcEquation {
  public class Node{
    public String value;
    public int in;
    public int out;
    public ArrayList<Node> nexts;
    public ArrayList<Edge> edges;
    public HashMap<Node, Edge> nextEdgeMap;

    public Node(String a) {
      this.value = a;
      this.nexts = new ArrayList<>();
      this.edges = new ArrayList<>();
      this.nextEdgeMap = new HashMap<>();
    }
  }

  public class Edge{
    public double weight;
    public Node from;
    public Node to;

    public Edge(double weight, Node from, Node to){
      this.weight = weight;
      this.from = from;
      this.to = to;
    }
  }

  public class Graph {
    public HashMap<String, Node> nodes;
    public HashSet<Edge> edges;

    public Graph() {
      nodes = new HashMap<>();//HashMap保存结点和连接的节点
      edges = new HashSet<>();//图中只保存变
    }
  }

  //这个构造还比较特殊
  public Graph createGraph(List<List<String>> equations, double[] values){
    Graph graph = new Graph();
    for(int i = 0; i < equations.size(); i++){
      //思路：a、b组成有向图的两个端点，对应取出来的weight也要双向添加，不能只添加一个方向
      List<String> equa = equations.get(i);
      String a = equa.get(0);
      String b = equa.get(1);
      double weight = values[i];

      if(!graph.nodes.containsKey(a)){
        graph.nodes.put(a, new Node(a));
      }

      if(!graph.nodes.containsKey(b)){
        graph.nodes.put(b, new Node(b));
      }

      //技巧：双向添加！！！一定要双向
      Node fromNode = graph.nodes.get(a);
      Node toNode = graph.nodes.get(b);
      Edge newEdgeFromTo = new Edge(weight, fromNode, toNode);
      Edge newEdgeToFrom = new Edge(1 / weight, toNode, fromNode);

      fromNode.nexts.add(toNode);
      fromNode.out++;
      fromNode.nextEdgeMap.put(toNode, newEdgeFromTo);
      toNode.in++;
      fromNode.edges.add(newEdgeFromTo);
      graph.edges.add(newEdgeFromTo);

      toNode.nexts.add(fromNode);
      toNode.out++;
      toNode.nextEdgeMap.put(fromNode, newEdgeToFrom);
      fromNode.in++;
      toNode.edges.add(newEdgeToFrom);
      graph.edges.add(newEdgeToFrom);
    }
    return graph;
  }


  public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries){
    double[] ans = new double[queries.size()];
    Graph graph = createGraph(equations, values);
    for(int i = 0; i < queries.size(); i++){
      String from = queries.get(i).get(0);
      String to = queries.get(i).get(1);

      if(!graph.nodes.containsKey(from) || !graph.nodes.containsKey(to)){
        ans[i] = -1;
      } else {
        Set<Node> visited = new HashSet<>();
        ans[i] = dfs(graph, from, to, visited);
      }
    }
    return ans;
  }


  private double dfs(Graph graph, String from, String to, Set<Node> visited){

    if(from.equals(to)){
      return 1.0;
    }
    Node fromNode = graph.nodes.get(from);
    visited.add(fromNode);
    for(Node next : fromNode.nexts){
      if(!visited.contains(next)){
        double nextValue = dfs(graph, String.valueOf(next.value), to, visited);
        if(nextValue > 0){
          return fromNode.nextEdgeMap.get(next).weight * nextValue;
        }
      }
    }
    visited.remove(from);
    return -1;
  }

  public static void main(String[] args){
    List<String> ab = new ArrayList<>();
    ab.add("a");
    ab.add("b");
    List<String> bc = new ArrayList<>();
    bc.add("b");
    bc.add("c");
    List<List<String>> equations = new ArrayList<>();
    equations.add(ab);
    equations.add(bc);
    double[] values = new double[]{2.0, 3.0};
    List<List<String>> queries = new ArrayList<>();
    List<String> q1 = Arrays.asList(new String[]{"a","c"});
    List<String> q2 = Arrays.asList(new String[]{"b","a"});
    queries.add(q1);
    queries.add(q2);

    double[] ans = new double[queries.size()];
    ans = new PTOS111_CalcEquation().calcEquation(equations, values, queries);
    for(double a : ans) {
      System.out.println(a);
    }

  }

}
