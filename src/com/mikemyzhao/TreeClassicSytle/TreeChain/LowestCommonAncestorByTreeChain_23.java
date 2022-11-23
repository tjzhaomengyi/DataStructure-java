package com.mikemyzhao.TreeClassicSytle.TreeChain;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-06 17:19
 * @Description:
 */
public class LowestCommonAncestorByTreeChain_23 {
  //技巧：使用树链刨分的方法，支持在线查询公共最低父节点
  //空间复杂度O(N),单次查询时间O(logN)
  public static int[] query(int[] father, int[][] queries){
    TreeChain tc = new TreeChain(father);
    int M = queries.length;
    int[] ans = new int[M];
    for(int i = 0; i < M; i++){
      if(queries[i][0] == queries[i][1]){
        ans[i] = queries[i][0];
      } else {
        ans[i] = tc.lca(queries[i][0],queries[i][1]);
      }
    }
    return ans;
  }

  public static class TreeChain{
    private int n;
    private int h;
    private int[][] tree;
    private int[] fa;
    private int[] dep;
    private int[] son;
    private int[] siz;
    private int[] top;

    public TreeChain(int[] father){
      initTree(father);
      dfs1(h, 0);
      dfs2(h, h);
    }

    private void initTree(int[] father){
      //技巧：树链刨分从下标1开始
      n = father.length + 1;
      tree = new int[n][];
      fa = new int[n];
      dep = new int[n];
      son = new int[n];
      siz = new int[n];
      top = new int[n];
      int[] cnum = new int[n];
      for(int i = 0; i < n; i++){
        if(father[i] == i){
          h = i + 1;
        } else{
          cnum[father[i]]++;//统计i节点的父节点数量
        }
      }
      tree[0] = new int[0];
      for(int i = 0; i < n; i++){
        tree[i + 1] = new int[cnum[i]];//
      }
      for(int i = 0; i < n; i++){
        if(i + 1 != h){
          tree[father[i] + 1][--cnum[father[i]]] = i + 1;
        }
      }
    }

    //当前u的父节点为f
    public void dfs1(int u, int f){
      fa[u] = f;
      dep[u] =dep[f] +1;
      siz[u] = 1;
      int maxSize = -1;
      for(int v : tree[u]){
        dfs1(v, u);
        siz[u] += siz[v];
        if(siz[v] > maxSize){
          maxSize = siz[v];
          son[u] = v;
        }
      }
    }

    //当前u的重链头结点是t
    public  void dfs2 (int u, int t){
      top[u] = t;
      if(son[u] != 0){
        dfs2(son[u], t);
        for(int v : tree[u]){
          if(v != son[u]){
            dfs2(v,v);
          }
        }
      }
    }

    public int lca(int a, int b){
      a++;
      b++;
      while(top[a] != top[b]){
        if(dep[top[a]] > dep[top[b]]){
          a = fa[top[a]];
        } else {
          b = fa[top[b]];
        }
      }
      return (dep[a] < dep[b] ? a : b) - 1;
    }
  }
}
