package com.book.point2offerspecial.fifteen_graph.unionfind;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-22 10:45
 * @Description:这道题是给出边和点，然后问怎么添加边到最后不成环
 */
public class PTOS118_FindRedundantConnection {

  //思路：就是先把点都打散成并查集的初始情况，然后在edge边连接的时候遍历这些点，如果出现
  // 有边连接着两个点，就查看是否能减少子集的个数，如果能减少说明在不同的子集，
  // 一旦并查集union后sets数量不变那么表示这两个点已经处在并查集中的同一集合了，返回！！
  //太难了~直接记住例子吧：如果两个节点分别属于不同的子图，添加一条边
  // 连接两个节点会将它们所在的子图连接在一起，但是不会形成环！！！！
  // 如果连个节点属于同一个子图，添加一条边连接两个节点就会形成环.

  public class UnionFind{
    private int[] parents;
    private int[] size;
    private int[] help;
    private int sets;

    public UnionFind(int N){
      parents = new int[N];
      size = new int[N];
      help = new int[N];
      sets = N;
      for(int i = 0; i < N; i++){
        parents[i] = i;
        size[i] = 1;
      }
    }

    public void union(int i, int j){
      int f1 = find(i);
      int f2 = find(j);
      if(f1 != f2){
        if(size[f1] >= size[f2]){
          size[f1] += size[f2];
          parents[f2] = f1;
        } else {
          size[f2] += size[f1];
          parents[f1] = f2;
        }
        sets--;
      }
    }

    public int find(int i){
      int hi = 0;
      while(parents[i] != i){
        help[hi] = i;
        hi++;
        i = parents[i];
      }
      for(hi--; hi >= 0; hi--){//todd:这里稍作调整
        parents[help[hi]] = i;
      }
      return i;
    }
  }


  public int[] findRedundantConnection(int[][] edges){
    //找到edges中最大编号
    int maxPoint = Integer.MIN_VALUE;
    for(int[] edge : edges){
      maxPoint = Math.max(maxPoint, edge[0]);
      maxPoint = Math.max(maxPoint, edge[1]);
    }
    UnionFind uf = new UnionFind(maxPoint);
    for(int[] edge :edges){
      int p = edge[0] - 1;
      int q = edge[1] - 1;
      int old_sets = uf.sets;
      uf.union(p, q); //因为unionFind是从0开始的
      int new_sets = uf.sets;
      if(old_sets == new_sets){
        return edge;
      }
    }
    return new int[2];
  }

  public static void main(String[] args) {
    int[][] problem = {{1,2},{1,3},{2,3}};
    int[] ans = new PTOS118_FindRedundantConnection().findRedundantConnection(problem);
    System.out.println(ans);
  }
}
