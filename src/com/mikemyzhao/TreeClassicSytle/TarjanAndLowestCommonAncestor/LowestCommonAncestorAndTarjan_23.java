package com.mikemyzhao.TreeClassicSytle.TarjanAndLowestCommonAncestor;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Union;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-06 14:33
 * @Description:// 给定数组tree大小为N，表示一共有N个节点
 * 	// tree[i] = j 表示点i的父亲是点j，tree一定是一棵树而不是森林
 * 	// queries是二维数组，大小为M*2，每一个长度为2的数组都表示一条查询
 * 	// [4,9], 表示想查询4和9之间的最低公共祖先
 * 	// [3,7], 表示想查询3和7之间的最低公共祖先
 * 	// tree和queries里面的所有值，都一定在0~N-1之间
 * 	// 返回一个数组ans，大小为M，ans[i]表示第i条查询的答案
 */
public class LowestCommonAncestorAndTarjan_23 {
    //技巧：1使用离线查询的方式：Tarjan算法+并查集.M条查询记录，时间复杂度是O(N+M),把M条查询一次给全，不支持在线查询
  public static int[] query2(int[] father,int[][] queries){
    int N = father.length;
    int M = queries.length;
    int[] help = new int[N];//技巧：存储子节点的个数
    int h = 0;//记录当前树的头结点

    //技巧：1使用help数组统计每个节点有几个子节点
    for(int i = 0; i < N; i++){
      if(father[i] == i){
        h = i;
      } else {
        help[father[i]]++;//统计每个点子节点的个数
      }
    }
    int[][] mt = new int[N][];//技巧：存储当前节点有哪些孩子
    for(int i = 0; i < N; i++){
      mt[i] = new int[help[i]];//先开辟子数组的大小
    }
    //给mt数组添值
    for (int i = 0; i < N; i++){
      if(i != h){//当前节点不是树的根节点，找到其父亲节点填入
        mt[father[i]][--help[father[i]]] = i;
      }
    }

    //技巧：2使用help数组统计query，每个query问题节点的数量
    for (int i = 0; i < M; i++){
      if(queries[i][0] != queries[i][1]){
        help[queries[i][0]]++;
        help[queries[i][1]]++;
      }
    }

    //技巧：mq存储每个问题的开始，对应哪些节点。
    // mi存储得到问题的答案填写在对应query的哪些地方
    int[][] mq = new int[N][];
    int[][] mi = new int[N][];
    for(int i = 0; i < N; i++){
      mq[i] = new int[help[i]];
      mi[i] = new int[help[i]];
    }
    //技巧：往mq和mi中填入适当内容
    for (int i = 0; i < M; i++){
      if(queries[i][0] != queries[i][1]){
        mq[queries[i][0]][--help[queries[i][0]]] = queries[i][1];
        mi[queries[i][0]][help[queries[i][0]]] = i;
        mq[queries[i][1]][--help[queries[i][1]]] = queries[i][0];
        mi[queries[i][1]][help[queries[i][1]]] = i;
      }
    }
    int[] ans = new int[M];
    UnionFind uf = new UnionFind(N);
    process(h, mt, mq, mi, uf, ans);
    for(int i = 0; i < M; i++){
      if(queries[i][0] == queries[i][1]){
        ans[i] = queries[i][0];
      }
    }
    return ans;

  }

  //当前来到head节点
  //mt是整棵树，head下方节点mt[head] mt[head] = {x,y}
  //mq问题列表：当前head有哪些问题 mq[head]= {a,b,c}
  //mi得到问题的答案，填在什么地方
  //技巧：这个就是Tarjan算法
  public static void process(int head, int[][] mt, int[][] mq, int[][] mi, UnionFind uf, int[] ans){
    //技巧：这个for循环就是一个tarjan算法
    for(int next : mt[head]){
      //技巧：不同的head会有遍历到，还没遍历到的情况，所以要用tag标记一下当前遍历到的head的head，如果没有的话就是还没有遍历到，不要用find去找
      process(next, mt, mq, mi, uf, ans);
      uf.union(head, next);
      uf.setTag(head, head);
    }
    //技巧：解决head的问题
    int[] q = mq[head];
    int[] i = mi[head];//答案要填写的地方
    for(int k = 0; k < q.length; k++){
      int tag = uf.getTag(q[k]);
      if(tag != -1){//技巧：如果没解决过就是-1，哎？如果解决过就是当时打的tag！
        ans[i[k]] = tag;
      }
    }
  }

  public static class UnionFind{
    private int[] fa;//father -> 并查集里面的father信息
    private int[] siz;
    private int[] h;//辅助记录数组
    private int[] t;//这道题多出来的一个玩意，技巧：深度遍历树，把一侧的树都在遍历的时候打上tag，
    // 技巧：然后如果这个问题是在集合中，但是没有对应第二个节点进入tag集合删除掉，如果第二个节点在tag集合中，那么它们的公共节点就是当期的tag

    public UnionFind(int N){
      fa =new int[N];
      siz = new int[N];
      h = new int[N];
      t = new int[N];
      for(int i = 0; i < N; i++){
        fa[i] = i;
        siz[i] = 1;
        t[i] = -1;//深度遍历打tag
      }
    }

    private int find(int i){
      int j = 0;
      while(i != fa[i]){
        h[j++] = i;
        i = fa[i];
      }
      while(j > 0){
        h[--j] = i;
      }
      return i;
    }
    public void union(int i, int j){
      int fi = find(i);
      int fj = find(j);
      if(fi != fj){
        int si = siz[fi];
        int sj = siz[fj];
        int more = si > sj ? fi : fj;
        int less = more == fi ? fj : fi;
        fa[less] = more;
        siz[more] += siz[less];
      }
    }
    //技巧：这道题特殊打tage的标签,todo:个人觉得这个tag设置的优点多余，既然并查集已经出来了，就用并查集的头结点做tag不就行了？
    //todo:这道题一定要设置tag，因为在询问答案的时候可能遍历过这个节点可能也没有遍历过
    public void setTag(int i, int tag){
      t[find(i)] = tag;
    }

    public int getTag(int i){
      return t[find(i)];
    }
  }


}
