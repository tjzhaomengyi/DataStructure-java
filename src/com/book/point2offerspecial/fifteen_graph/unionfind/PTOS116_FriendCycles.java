package com.book.point2offerspecial.fifteen_graph.unionfind;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-21 19:25
 * @Description:省份连接、朋友圈问题，都是在看节点属于几个集合，或者在不在同一个集合中
 */
public class PTOS116_FriendCycles {

  //思路：并查集用下标代替题目中的元素来保持语义的完整性，不需要把题目中原始内容带入到并查集的代码
  // 并查集的代码真正能实现一个结构多处通用
  public class UnionFind{
    private int[] parent;//记录i节点的根节点
    private int[] size; //记录所在集合的大小
    private int[] help; //辅助
    public int sets; //一共多少集合

    public UnionFind(int N){
      parent = new int[N];//parent[i] = k i的父亲是k
      //只有在parent[i] = k，size[i]才有意义，表示当前集合大小
      size = new int[N];
      help = new int[N];
      sets = N;
      for(int i = 0; i < N; i++){
        parent[i] = i;//初始化自己指向自己；
        size[i] = 1;
      }
    }

    //太难了~直接记住例子吧：union，先union再find,只有在合并的时候才调整size
    public void union(int i, int j){
      int f1 = find(i);//
      int f2 = find(j);
      if(f1 != f2){
        if(size[f1] >= size[f2]){
          size[f1] += size[f2]; //把小的集合挂在大的集合上
          parent[f2] = f1; //让大集合的根节点作为小集合的根节点
        } else {
          size[f2] += size[f1];
          parent[f1] = f2;
        }
        sets--;
      }
    }


    //太难了~直接记住例子吧：（find）
    // 存在路径压缩的优化
    private int find(int i){
      int helpi = 0;
      //太难了~直接记住例子吧：思路：每次从i往上去找的时候都要走过一些节点，
      // 用help节点来记录这些走过的节点，就是在返回父节点之前把这些节点的父节点都标记
      //  为最终找到的根节点
      while(i != parent[i]){ //技巧：只要i不等于自己说明就没到根节点；
        help[helpi] = i;//技巧这里才重要，每次在help数组中记录走过的路
        helpi++;
        i = parent[i];//技巧：往上找，这个都不重要
      }
      //思路：此时i已经是父节点了！！！！用help往回标记
      //太难了~直接记住例子吧：【非常重要】标记自己走过的路的根节点为i就行
      for(helpi--; helpi >= 0; helpi--){
        parent[help[helpi]] = i;
      }
      return i;
    }

  }



  public int findCircleNum(int[][] M){
    int n = M.length;
    int m = M[0].length;
    UnionFind uf = new UnionFind(n);
    for(int i = 0; i < n; i++){
      for(int j = i + 1; j < m; j++){
        if(M[i][j] == 1){
          uf.union(i, j);
        }
      }
    }
    return uf.sets;
  }

}
