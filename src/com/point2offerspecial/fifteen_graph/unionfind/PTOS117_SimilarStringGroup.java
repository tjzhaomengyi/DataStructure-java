package com.point2offerspecial.fifteen_graph.unionfind;

import com.sun.deploy.util.StringUtils;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-21 20:10
 * @Description:
 */
public class PTOS117_SimilarStringGroup {
  public class UnionFind{
    private int[] parent;
    private int[] size;
    private int[] help;
    public int sets;

    public UnionFind(int N){
      parent = new int[N];
      size = new int[N];
      help = new int[N];
      sets = N;
      for(int i = 0; i < N; i++){
        parent[i] = i;
        size[i] = 1;
      }
    }

    public void union(int i, int j){
      int f1 = find(i);
      int f2 = find(j);
      if(f1 != f2){
        if(size[f1] >= size[f2]){
          size[f1] += size[f2];
          parent[f2] = f1;
        } else{
          size[f2] += size[f1];
          parent[f1] = f2;
        }
        sets--;
      }
    }



    private int find(int i){
      String.valueOf(i).toCharArray();
      int hi = 0;
      while(i != parent[i]){
        help[hi++] = i;//如果从0开始到1再在i=2的时候发现相等了，此时hi已经到3了
        i = parent[i];
      }
      //所以，因为hi的最后一位没有记录~没有记录，所以他要减到上一层
      for(hi--; hi >= 0; hi--){//hi技巧一定要先减！
        parent[help[hi]] = i;
      }
      return i;
    }

  }

  public boolean similar(String a, String b){
    int diff = 0;
    for(int i = 0; i < a.length(); i++){
      if(a.charAt(i) != b.charAt(i)){
        diff++;
      }
    }
    return diff == 2;
  }

  public int numSimilarGroups(String[] A){
    UnionFind uf = new UnionFind(A.length);

    for(int i = 0; i < A.length - 1; i++){
      for(int j = i + 1; j < A.length; j++){
        if(similar(A[i], A[j])){
          uf.union(i, j);
        }
      }
    }
    return uf.sets;
  }

}
